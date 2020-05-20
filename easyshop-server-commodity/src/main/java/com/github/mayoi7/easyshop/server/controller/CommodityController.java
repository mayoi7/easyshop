package com.github.mayoi7.easyshop.server.controller;

import com.alibaba.fastjson.JSON;
import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.service.CommodityService;
import com.github.mayoi7.easyshop.service.RedisService;
import com.github.mayoi7.easyshop.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LiuHaonan
 * @date 10:17 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/shop/commodity")
public class CommodityController {

    @Reference
    private UserService userService;

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/{id}")
    public ResponseResult<String> findById(@PathVariable("id") Long id) {
        Commodity commodity = commodityService.findById(id);

        return new ResponseResult<>(JSON.toJSONString(commodity));
    }
}
