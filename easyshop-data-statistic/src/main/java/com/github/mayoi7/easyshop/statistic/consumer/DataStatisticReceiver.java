package com.github.mayoi7.easyshop.statistic.consumer;

import com.alibaba.fastjson.JSON;
import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.dto.TransData;
import com.github.mayoi7.easyshop.statistic.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LiuHaonan
 * @date 16:40 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@Service
@Slf4j
public class DataStatisticReceiver {

    @Resource
    private RedisService redisService;

    /**
     * 接收交易数据消息
     * @param transData 消息数据
     */
    @StreamListener("trans-data-topic")
    public void receiveTransData(@Payload TransData transData) {
        log.info("[STATISTIC] get statistic message <user_id={}, amount={}>", transData.getUserId(),
                transData.getAmount());
        // 添加两个数据记录
        redisService.addAndGet(RedisKeys.TRANSACTION_DATA, transData.getAmount().doubleValue());
        redisService.addAndGet(RedisKeys.TRANSACTION_DATA, transData.getUserId().toString(),
                transData.getAmount().doubleValue());
    }
}
