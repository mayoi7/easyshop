package com.github.mayoi7.easyshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 15:50 2020/5/20
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderParam {

    /** 随机唯一key，用于避免重复下单 */
    private String key;

    /** 下单商品id */
    private Long commodityId;

    /** 下单时商品价格（有可能和商品当前价格不一致） */
    private Double price;

    /** 购买商品数量 */
    private int quantity;
}
