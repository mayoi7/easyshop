package com.github.mayoi7.easyshop.server.controller;

import com.github.mayoi7.easyshop.dto.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author LiuHaonan
 * @date 10:17 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/shop/order")
public class OrderController {

    @GetMapping("/test")
    public ResponseResult<Void> health() {
        return ResponseResult.SUCCESS;
    }

    @PostMapping("/")
    public ResponseResult<Integer> placeOrder() {
        return null;
    }
}
