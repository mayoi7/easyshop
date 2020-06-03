package com.github.mayoi7.easyshop.server.mapper;

import com.github.mayoi7.easyshop.po.Order;
import tk.mybatis.mapper.MyMapper;

/**
 * 
 * @date 17:04 2020/5/15
 * @author LiuHaonan 
 * @email acerola.orion@foxmail.com
 */
public interface OrderMapper extends MyMapper<Order> {

    /**
     * 插入订单，插入后的主键会返回到Order数据的id属性中
     * @param order 订单数据
     */
    void insertOrder(Order order);
}