package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.po.Order;
import com.github.mayoi7.easyshop.server.mapper.OrderMapper;
import com.github.mayoi7.easyshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuHaonan
 * @date 9:52 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public Order placeOrder(Long userId, Long commodityId, Double price, Integer quantity) {

        return null;
    }

    @Override
    public List<Order> findListByUserAndCommodity(Long commodityId, Long userId) {
        return null;
    }
}
