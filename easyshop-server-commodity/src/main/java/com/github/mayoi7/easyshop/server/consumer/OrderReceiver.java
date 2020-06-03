package com.github.mayoi7.easyshop.server.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.dto.order.OrderData;
import com.github.mayoi7.easyshop.dto.order.OrderParam;
import com.github.mayoi7.easyshop.po.Order;
import com.github.mayoi7.easyshop.server.mapper.OrderMapper;
import com.github.mayoi7.easyshop.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author LiuHaonan
 * @date 16:22 2020/5/23
 * @email acerola.orion@foxmail.com
 */
@Service
@Slf4j
public class OrderReceiver {

    @Resource
    private OrderMapper orderMapper;

    @Reference
    private RedisService redisService;

    /**
     * 下订单
     * @param orderData 订单数据
     */
    @StreamListener("order-topic")
    public void placeOrder(@Payload OrderData orderData) {
        // 下订单
        BigDecimal total = orderData.getPrice().multiply(
                BigDecimal.valueOf(orderData.getQuantity()));
        Order order = Order.builder()
                .userId(orderData.getUserId()).commodityId(orderData.getCommodityId())
                .price(orderData.getPrice()).quantity(orderData.getQuantity()).total(total)
                .build();
        orderMapper.insertOrder(order);
        redisService.setInList(RedisKeys.ORDER_USER, orderData.getUserId().toString(), order);
        log.info("[<MESSAGE>ORDER] place order success" +
                " <id={}, user={}, commodity={}, price={}, quantity={}>",
                order.getId(), order.getUserId(), order.getCommodityId(), order.getPrice(), order.getQuantity());
    }
}
