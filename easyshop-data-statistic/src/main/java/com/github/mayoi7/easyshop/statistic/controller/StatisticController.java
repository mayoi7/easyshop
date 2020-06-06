package com.github.mayoi7.easyshop.statistic.controller;

import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.service.CartService;
import com.github.mayoi7.easyshop.service.OrderService;
import com.github.mayoi7.easyshop.statistic.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 19:57 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/count")
@Slf4j
public class StatisticController {

    @Resource
    private RedisService redisService;

    @Reference
    private CartService cartService;

    @GetMapping("/test/{val}")
    public ResponseResult<Void> testIt(@PathVariable("val") Integer val) {
        boolean result = cartService.test(val);
        if (result) {
            return ResponseResult.SUCCESS;
        } else {
            return new ResponseResult<>(StateCode.FAIL, "value != 1", null);
        }
    }

    @GetMapping("/trans/total")
    public ResponseResult<BigDecimal> countTransactionRecord() {
        Double total = redisService.count(RedisKeys.TRANSACTION_DATA);
        if (total == null) {
            // TODO: 2020/5/18 改为从数据库中进行统计
            log.error("[STATISTIC] total transaction record is null in redis");
            return new ResponseResult<>(StateCode.FAIL, "缓存数据缺失", null);
        } else {
            return new ResponseResult<>(BigDecimal.valueOf(total));
        }
    }

    @GetMapping("/trans/user/{userId}")
    public ResponseResult<BigDecimal> countUserTransactionRecord(@PathVariable Long userId) {
        Double total = redisService.count(RedisKeys.TRANSACTION_DATA, userId.toString());
        if (total == null) {
            // TODO: 2020/5/18 改为从数据库中进行统计
            log.error("[STATISTIC] total transaction record is null in redis");
            return new ResponseResult<>(StateCode.FAIL, "缓存数据缺失", null);
        } else {
            return new ResponseResult<>(BigDecimal.valueOf(total));
        }
    }
}
