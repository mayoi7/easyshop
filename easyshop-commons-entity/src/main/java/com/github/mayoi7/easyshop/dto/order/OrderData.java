package com.github.mayoi7.easyshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单数据
 * @author LiuHaonan
 * @date 16:36 2020/5/23
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData implements Serializable {

    private static final long serialVersionUID = 367545290265696259L;

    /** 随机唯一key，避免重复下单 */
    private String key;

    /** 下单人用户id */
    private Long userId;

    /**  下单商品id */
    private Long commodityId;

    /** 下单时商品价格 */
    private BigDecimal price;

    /** 下单商品数量 */
    private Integer quantity;

    public OrderData(OrderParam param, Long userId) {
        this.commodityId = param.getCommodityId();
        this.price = BigDecimal.valueOf(param.getPrice());
        this.quantity = param.getQuantity();

        this.userId = userId;
    }
}
