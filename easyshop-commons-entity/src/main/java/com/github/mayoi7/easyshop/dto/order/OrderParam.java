package com.github.mayoi7.easyshop.dto.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 15:50 2020/5/20
 * @email acerola.orion@foxmail.com
 */
@Data
public class OrderParam {

    /** 下单商品id */
    private Long commodityId;

    /** 下单时商品价格 */
    private Double price;

    private int quantity;
}
