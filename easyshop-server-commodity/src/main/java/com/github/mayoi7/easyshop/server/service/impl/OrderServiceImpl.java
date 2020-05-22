package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.po.Order;
import com.github.mayoi7.easyshop.server.mapper.OrderMapper;
import com.github.mayoi7.easyshop.service.OrderService;
import com.github.mayoi7.easyshop.service.RedisService;
import com.github.mayoi7.easyshop.utils.KeyValueUtils;
import com.github.mayoi7.easyshop.utils.KeyValueUtils.PriceInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @Reference
    private RedisService redisService;

    @Override
    public boolean checkPrice(Long commodityId, BigDecimal price, long currentTimestamp) {
        List<Object> prices = redisService.getAllInList(RedisKeys.PRICE_LIST, commodityId.toString());

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
    public Order placeOrder(Long userId, Long commodityId, Double price, Integer quantity) {
        BigDecimal precisePrice = BigDecimal.valueOf(price);
        boolean isPass = checkPrice(commodityId, precisePrice, System.currentTimeMillis());
        if (!isPass) {
            log.error("[PRICE] order price illegal");
            return null;
        }
        BigDecimal total = precisePrice.multiply(BigDecimal.valueOf(quantity));
        Order order = Order.builder()
                .userId(userId).commodityId(commodityId)
                .price(precisePrice).quantity(quantity).total(total)
                .build();
        orderMapper.insertSelective(order);
        redisService.setInList(RedisKeys.ORDER_USER, userId.toString(), order);
        return order;
    }

    @Override
    public List<Order> findListByUserAndCommodity(Long commodityId, Long userId) {
        return null;
    }
}
