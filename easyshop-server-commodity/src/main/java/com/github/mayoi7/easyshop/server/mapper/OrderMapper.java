package com.github.mayoi7.easyshop.server.mapper;

import com.github.mayoi7.easyshop.po.Order;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
import java.util.List;

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

    /**
     * 查询某用户的所有订单
     * @param userId 用户id
     * @param timePoint 时间点
     * @param isAfter 表示查询时间点以后的还是以前的数据。true：查询时间点之后的订单
     * @return 返回订单列表，如果不存在则返回空链表（非null对象）
     */
    List<Order> selectOrderByUserAndTime(@Param("userId") Long userId,
                                         @Param("timePoint") Date timePoint,
                                         @Param("isAfter") boolean isAfter);
}