package com.github.mayoi7.easyshop.service;

import com.github.mayoi7.easyshop.dto.order.OrderData;
import com.github.mayoi7.easyshop.po.Order;

import java.math.BigDecimal;
import java.util.Date;
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
     * @param orderData 包含订单基本数据
     * @return 返回是否成功
     */
    boolean placeOrder(OrderData orderData);

    /**
     * 一次下多个订单
     * @param orderDataList 订单列表
     * @return 返回是否下单成功
     */
    boolean placeOrders(List<OrderData> orderDataList);

    /**
     * 根据下单商品和用户id查询对应所有订单
     * @param userId 用户id
     * @param timePoint 时间点
     * @param isAfter 表示查询时间点以后的还是以前的数据。true：查询时间点之后的订单
     * @return 返回订单列表，如果不存在则返回空链表（非null对象）
     */
    List<Order> findListByUserAndTime(Long userId, Date timePoint, boolean isAfter);
}
