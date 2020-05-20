package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.po.Order;
import com.github.mayoi7.easyshop.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuHaonan
 * @date 9:52 2020/5/17
 * @email acerola.orion@foxmail.com
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order placeOrder(Long commodityId, Long userId, Integer quantity) {
        return null;
    }

    @Override
    public List<Order> findListByUserAndCommodity(Long commodityId, Long userId) {
        return null;
    }
}
