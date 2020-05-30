package com.github.mayoi7.easyshop.service;

import com.github.mayoi7.easyshop.dto.cart.CartCommodity;

import java.util.List;

/**
 * 提供购物车操作
 * @author LiuHaonan
 * @date 19:34 2020/5/30
 * @email acerola.orion@foxmail.com
 */
public interface CartService {

    /**
     * 将商品添加购物车
     * @param username 用户名
     * @param commodityId 商品id
     * @return 返回是否添加成功
     */
    boolean addCart(String username, Long commodityId);

    /**
     * 查询用户购物车列表
     * @param username 用户名
     * @return 返回用户购物车商品列表信息
     */
    List<CartCommodity> loadCart(String username);
}
