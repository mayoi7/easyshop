package com.github.mayoi7.easyshop.server.controller;

import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.dto.order.OrderData;
import com.github.mayoi7.easyshop.dto.order.OrderParam;
import com.github.mayoi7.easyshop.po.Order;
import com.github.mayoi7.easyshop.po.User;
import com.github.mayoi7.easyshop.service.OrderService;
import com.github.mayoi7.easyshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 10:17 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/shop/order/")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @Reference
    private UserService userService;

    @GetMapping("/test")
    public ResponseResult<Void> health() {
        return ResponseResult.SUCCESS;
    }

    @PostMapping("/")
    public ResponseResult<Void> placeOrder(@RequestBody OrderParam orderParam) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByName(name);
        if (user == null) {
            log.error("[AUTH] logged user is not found <name={}>", name);
            return new ResponseResult<>(StateCode.LOGIN_ABNORMAL, null);
        }
        OrderData orderData = new OrderData(orderParam, user.getId());
        Integer quantity = orderService.placeOrder(orderData);
        if (quantity == null) {
            return new ResponseResult<>(StateCode.FAIL, "下单失败", null);
        }
        return null;
    }
}
