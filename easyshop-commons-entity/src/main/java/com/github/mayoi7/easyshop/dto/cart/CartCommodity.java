package com.github.mayoi7.easyshop.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 20:11 2020/5/30
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCommodity implements Serializable {

    private static final long serialVersionUID = -2910357750211743312L;

    /** 商品id */
    private Long commodityId;

    /** 商品名称 */
    private String name;

    /** 商品描述 */
    private String description;

    /** 商品图地址 */
    private String image;

    /** 商品现价 */
    private BigDecimal price;

    /** 商品库存 */
    private Integer inventory;
}
