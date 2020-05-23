package com.github.mayoi7.easyshop.service;

import com.github.mayoi7.easyshop.po.Order;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务（整合订单+库存服务），提供下订单以及订单查询的功能
 * @author LiuHaonan
 * @date 9:20 2020/5/17
 * @email acerola.orion@foxmail.com
 */
public interface OrderService {

    /**
     * 从缓存队列中检查商品价格是否合法
     * @param commodityId 商品id
     * @param price 商品价格
     * @param currentTimestamp 下单时间戳（该属性不可由用户传递）
     * @return true：合法
     */
    boolean checkPrice(Long commodityId, BigDecimal price, long currentTimestamp);

    /**
     * 下订单。如果订单当前价格和下单时间正确（在缓存中可以查看到对应数据），则添加，否则返回空
     * @param userId 下单用户id
     * @param commodityId 商品id
     * @param price 下单时的当前价格
     * @param quantity 下单数量
     * @return 返回插入的订单对象，如果插入失败或数据错误，则返回null
     */
    Order placeOrder(Long userId, Long commodityId, Double price, Integer quantity);

    /**
     * 根据下单商品和用户id查询对应所有订单
     * @param commodityId 商品id
     * @param userId 用户id
     * @return 返回订单列表，如果不存在则返回空链表（非null对象）
     */
    List<Order> findListByUserAndCommodity(Long commodityId, Long userId);
}
