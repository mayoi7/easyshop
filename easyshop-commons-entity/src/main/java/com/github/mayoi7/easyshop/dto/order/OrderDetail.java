package com.github.mayoi7.easyshop.dto.order;

import com.github.mayoi7.easyshop.dto.commodity.CommodityInfo;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.po.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详细信息，用于向用户返回信息
 * @author LiuHaonan
 * @date 12:37 2020/6/7
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 3547031643440632732L;

    /** 订单唯一编号 */
    private Long id;

    /** 订单创建用户id（使用者不关心） */
    private Long userId;

    /** 商品信息 */
    private CommodityInfo commodity;

    /** 下单时的价格 */
    private BigDecimal price;

    /** 下单数量 */
    private Integer quantity;

    /** 总价 */
    private BigDecimal total;

    /** 下单时间 */
    private Date createTime;

    public OrderDetail(Order order, CommodityInfo commodity) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.price = order.getPrice();
        this.quantity = order.getQuantity();
        this.total = order.getTotal();
        this.createTime = order.getCreateTime();

        this.commodity = commodity;
    }
}
