package com.github.mayoi7.easyshop.server.controller;

import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.dto.commodity.CommodityInfo;
import com.github.mayoi7.easyshop.dto.order.OrderData;
import com.github.mayoi7.easyshop.dto.order.OrderDetail;
import com.github.mayoi7.easyshop.dto.order.OrderList;
import com.github.mayoi7.easyshop.dto.order.OrderParam;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.po.Order;
import com.github.mayoi7.easyshop.po.User;
import com.github.mayoi7.easyshop.service.CommodityService;
import com.github.mayoi7.easyshop.service.OrderService;
import com.github.mayoi7.easyshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private CommodityService commodityService;

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
    public ResponseResult<Void> listPlaceOrder(@RequestBody List<OrderParam> orders) {
        if (orders == null || orders.isEmpty()) {
            return new ResponseResult<>("列表为空", null);
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.findUserByName(name).getId();

        // 将OrderParam对象转换为OrderData对象
        List<OrderData> orderDataList = new ArrayList<>(orders.size());
        orders.forEach(orderParam -> {
            orderDataList.add(new OrderData(orderParam, userId));
        });
        boolean result = orderService.placeOrders(orderDataList);
        if (result) {
            return ResponseResult.SUCCESS;
        } else {
            return new ResponseResult<>(StateCode.FAIL, "购买商品失败，请重试", null);
        }
    }

    /**
     * 根据订单号查询对应订单
     * @param orderId 订单号
     * @return 返回查询到的订单数据
     */
    @GetMapping("/{orderId}")
    public ResponseResult<OrderDetail> findOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return new ResponseResult<>(StateCode.WRONG_PARAM, "订单不存在", null);
        }
        Long commodityId = order.getCommodityId();
        Commodity commodity = commodityService.findById(commodityId);
        if (commodity == null) {
            log.error("[ORDER] commodity corresponding to this order does not exist " +
                    "<order_id={}, commodity_id={}>", orderId, commodityId);
            return new ResponseResult<>(StateCode.DATA_ABNORMAL, "数据有误，请稍后再试", null);
        }
        CommodityInfo commodityInfo = new CommodityInfo(commodity);
        OrderDetail orderDetail = new OrderDetail(order, commodityInfo);
        return new ResponseResult<>(orderDetail);
    }

    /**
     * 查询某用户的所有订单
     * @param userId 用户id，默认值为当前登陆用户
     * @param timePoint 时间点
     * @param isAfter true：表示查询在时间点之后的数据；false：表示查询在时间点之前的数据
     * @return 返回订单详细数据列表
     */
    @GetMapping("/list")
    public ResponseResult<List<OrderDetail>> findDetailList(@RequestParam(
                    value = "userId", required = false) Long userId,
                    @RequestParam(value = "timePoint", required = false) Date timePoint,
                    @RequestParam(value = "isAfter", required = false, defaultValue = "false") boolean isAfter) {
        if (userId == null) {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            userId = userService.findUserByName(name).getId();
        }
        // FIXME: 2020/6/9 时间有问题
        if (timePoint == null) {
            timePoint = new Date(System.currentTimeMillis());
        }
        List<OrderDetail> orderDetails = orderService.findDetailListByUserAndTime(userId, timePoint, isAfter);
        if (orderDetails == null) {
            return new ResponseResult<>(StateCode.WRONG_PARAM, "参数不正确", null);
        } else if (orderDetails.isEmpty()) {
            return new ResponseResult<>(StateCode.DATA_ABNORMAL, "数据异常，请稍后再试", null);
        } else {
            return new ResponseResult<>(orderDetails);
        }
    }
}
