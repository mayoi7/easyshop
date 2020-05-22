package com.github.mayoi7.easyshop.server.controller;

import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.order.OrderParam;
import com.github.mayoi7.easyshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LiuHaonan
 * @date 10:17 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/shop/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/test")
    public ResponseResult<Void> health() {
        return ResponseResult.SUCCESS;
    }

    @PostMapping("/")
    public ResponseResult<Integer> placeOrder(@RequestBody OrderParam orderParam) {
        return null;
    }
}
