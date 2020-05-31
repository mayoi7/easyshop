package com.github.mayoi7.easyshop.server.controller;

import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.dto.cart.CartCommodity;
import com.github.mayoi7.easyshop.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiuHaonan
 * @date 9:54 2020/5/31
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/shop/cart")
@Slf4j
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 将商品添加到购物车
     * @param commodityId 要添加到购物车的商品id
     * @return 返回添加是否成功的响应
     */
    @PostMapping("/{commodityId}")
    public ResponseResult<Void> appendCart(@PathVariable("commodityId") Long commodityId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean result = cartService.addCart(name, commodityId);
        if (result) {
            return ResponseResult.SUCCESS;
        } else {
            return new ResponseResult<>(StateCode.FAIL, "添加购物车异常，请重试", null);
        }
    }

    @GetMapping("/")
    public ResponseResult<List<CartCommodity>> listCart() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CartCommodity> cartList = cartService.loadCart(name);
        if (cartList == null) {
            log.warn("[CART] something wrong when load cart <user_name={}>", name);
            return new ResponseResult<>(StateCode.FAIL, "加载购物车异常，请重试", null);
        }
        return new ResponseResult<>(StateCode.OK, cartList);
    }
}
