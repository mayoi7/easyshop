package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.dto.TransData;
import com.github.mayoi7.easyshop.dto.commodity.CommodityInfo;
import com.github.mayoi7.easyshop.dto.order.OrderData;
import com.github.mayoi7.easyshop.dto.order.OrderDetail;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.po.Inventory;
import com.github.mayoi7.easyshop.po.Order;
import com.github.mayoi7.easyshop.server.mapper.CommodityMapper;
import com.github.mayoi7.easyshop.server.mapper.InventoryMapper;
import com.github.mayoi7.easyshop.server.mapper.OrderMapper;
import com.github.mayoi7.easyshop.server.producer.MessageProducer;
import com.github.mayoi7.easyshop.service.CommodityService;
import com.github.mayoi7.easyshop.service.InventoryService;
import com.github.mayoi7.easyshop.service.OrderService;
import com.github.mayoi7.easyshop.service.RedisService;
import com.github.mayoi7.easyshop.utils.KeyValueUtils;
import com.github.mayoi7.easyshop.utils.KeyValueUtils.PriceInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LiuHaonan
 * @date 9:52 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CommodityService commodityService;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private MessageProducer producer;

    @Reference
    private RedisService redisService;

    @Override
    public boolean checkPrice(Long commodityId, BigDecimal price, long currentTimestamp) {
        List<Object> prices = redisService.getAllInList(RedisKeys.PRICE_LIST, commodityId.toString());

        if (prices.isEmpty()) {
            // 如果缓存中不存在，则采取现价作为标准
            log.info("[CACHE] price list is null, will reset new value <key(commodity_id)={}>", commodityId);
            Commodity commodity = (Commodity) redisService.get(RedisKeys.COMMODITY_ID, commodityId.toString());
            if (commodity == null) {
                if ((commodity = commodityService.findById(commodityId)) == null) {
                    log.error("[COMMODITY] commodity is not found with such id <commodity_id={}>", commodityId);
                    return false;
                }
            }
            if (price.equals(commodity.getCurrentPrice()) &&  currentTimestamp > commodity.getUpdateTime().getTime()) {
                redisService.setInList(RedisKeys.PRICE_LIST, commodityId.toString(),
                        KeyValueUtils.splicePriceValue(price, currentTimestamp));
                return true;
            } else {
                return false;
            }
        }

        // 寻找下单时间之前最新的价格值，即为正确的下单值
        for (Object item : prices) {
            PriceInfo info = KeyValueUtils.loadPriceInfo(item.toString());
            if (info != null) {
                if (currentTimestamp > info.timestamp) {
                    return info.price.equals(price);
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean placeOrder(OrderData orderData) {
        String userIdString = orderData.getUserId().toString();

        // 如果发现订单已提交，则直接返回
        if (redisService.checkExistenceInSet(RedisKeys.ORDER_LOCK, userIdString, orderData.getKey())) {
            return true;
        } else {
            redisService.setInSet(RedisKeys.ORDER_LOCK, userIdString, orderData.getKey());
        }

        Integer remain = inventoryService.reduceInventory(orderData.getCommodityId(), orderData.getQuantity());
        if (remain == null) {
            // 返回空说明库存不足（并不一定是0，只是下单数量超过当前库存）
            return false;
        }
        log.info("[MESSAGE] sending msg to place order <user_id={}, commodity_id={}, price={}, amount={}>",
                userIdString, orderData.getCommodityId(), orderData.getPrice(), orderData.getQuantity());
        producer.sendOrderRequest(orderData);

        // 发送交易数据统计消息
        producer.sendTransData(new TransData(orderData.getUserId(), orderData.getTotal()));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean placeOrders(List<OrderData> orderDataList) {
        if (orderDataList == null || orderDataList.isEmpty()) {
            return true;
        }
        for (OrderData orderData: orderDataList) {
            // placeOrder不会触发事务
            if (!placeOrder(orderData)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Order findById(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<Order> findListByUserAndTime(Long userId, Date timePoint, boolean isAfter) {
        if (userId == null || timePoint == null) {
            return null;
        }
        return orderMapper.selectOrderByUserAndTime(userId, timePoint, isAfter);
    }

    @Override
    public List<OrderDetail> findDetailListByUserAndTime(Long userId, Date timePoint, boolean isAfter) {
        List<Order> orders = findListByUserAndTime(userId, timePoint, isAfter);
        if (orders == null) {
            return null;
        } else if (orders.isEmpty()) {
            return new ArrayList<>();
        }

        List<OrderDetail> orderDetails = new ArrayList<>(orders.size());
        orders.forEach(order -> {
            Commodity commodity = commodityService.findById(order.getCommodityId());
            if (commodity == null) {
                log.error("[ORDER] commodity corresponding to this order does not exist " +
                        "<order_id={}, commodity_id={}>", order.getId(), order.getCommodityId());
            } else {
                CommodityInfo commodityInfo = new CommodityInfo(commodity);
                orderDetails.add(new OrderDetail(order, commodityInfo));
            }
        });
        return orderDetails;
    }
}
