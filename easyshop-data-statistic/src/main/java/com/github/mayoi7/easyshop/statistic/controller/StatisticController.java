package com.github.mayoi7.easyshop.statistic.controller;

import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.statistic.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/trans")
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
}
