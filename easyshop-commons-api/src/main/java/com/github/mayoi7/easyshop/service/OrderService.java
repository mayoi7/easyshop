package com.github.mayoi7.easyshop.service;

import com.github.mayoi7.easyshop.po.Order;

import java.util.List;

/**
 * 订单服务（整合订单+库存服务），提供下订单以及订单查询的功能
 * @author LiuHaonan
 * @date 9:20 2020/5/17
 * @email acerola.orion@foxmail.com
 */
public interface OrderService {

    /**
     * 下订单
     * @param commodityId 商品id
     * @param userId 下单用户id
     * @param quantity 下单数量
     * @return 返回插入的订单对象
     */
    Order placeOrder(Long commodityId, Long userId, Integer quantity);

    /**
     * 根据下单商品和用户id查询对应所有订单
     * @param commodityId 商品id
     * @param userId 用户id
     * @return 返回订单列表，如果不存在则返回空链表（非null对象）
     */
    List<Order> findListByUserAndCommodity(Long commodityId, Long userId);
}
