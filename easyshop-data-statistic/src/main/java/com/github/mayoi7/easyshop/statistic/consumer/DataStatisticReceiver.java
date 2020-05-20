package com.github.mayoi7.easyshop.statistic.consumer;

import com.alibaba.fastjson.JSON;
import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.dto.TransData;
import com.github.mayoi7.easyshop.service.RedisService;
import com.github.mayoi7.easyshop.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.redis.core.RedisTemplate;
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
     * @param transDataJson 消息json
     */
    @StreamListener("trans-data-topic")
    public void receiveTransData(String transDataJson) {
        if (transDataJson == null) {
            log.error("[MESSAGE] msg is null");
        }
        TransData transData = JSON.parseObject(transDataJson, TransData.class);
        if (transData == null) {
            log.error("[MESSAGE] msg convert fail <msg_json={}>", transDataJson);
            return;
        }
        redisService.addAndGet(RedisKeys.TRANSACTION_DATA_KEY, transData.getAmount().doubleValue());
    }
}
