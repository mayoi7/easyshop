package com.github.mayoi7.easyshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 订单列表
 * @author LiuHaonan
 * @date 8:35 2020/6/1
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderList implements Serializable {

    private static final long serialVersionUID = 1828313531292161357L;

    private List<OrderParam> orders;

}
