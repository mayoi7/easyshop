package com.github.mayoi7.easyshop.server.controller;

import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.dto.order.OrderData;
import com.github.mayoi7.easyshop.dto.order.OrderList;
import com.github.mayoi7.easyshop.dto.order.OrderParam;
import com.github.mayoi7.easyshop.po.User;
import com.github.mayoi7.easyshop.service.OrderService;
import com.github.mayoi7.easyshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        boolean orderResult = orderService.placeOrder(orderData);
        if (!orderResult) {
            return new ResponseResult<>(StateCode.FAIL, "下单失败", null);
        }
        return ResponseResult.SUCCESS;
    }

    /**
     * 提交多个订单（购物车下单）
     * @param orders 订单列表
     * @return 返回是否添加成功的响应（因为是异步下单，所以无法返回订单号）
     */
    @PostMapping("/list")
    public ResponseResult<Void> listPlaceOrder(@RequestBody OrderList orders) {
        List<OrderParam> orderParamList = orders.getOrders();
        if (orderParamList == null || orderParamList.isEmpty()) {
            return new ResponseResult<>("列表为空", null);
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.findUserByName(name).getId();

        // 将OrderParam对象转换为OrderData对象
        List<OrderData> orderDataList = new ArrayList<>(orderParamList.size());
        orderParamList.forEach(orderParam -> {
            orderDataList.add(new OrderData(orderParam, userId));
        });
        boolean result = orderService.placeOrders(orderDataList);
        if (result) {
            return ResponseResult.SUCCESS;
        } else {
            return new ResponseResult<>(StateCode.FAIL, "购买商品失败，请重试", null);
        }
    }
}
