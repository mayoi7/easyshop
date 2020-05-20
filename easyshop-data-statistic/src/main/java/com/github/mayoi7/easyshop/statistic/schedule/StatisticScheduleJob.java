package com.github.mayoi7.easyshop.statistic.schedule;

import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.po.Statistic;
import com.github.mayoi7.easyshop.service.RedisService;
import com.github.mayoi7.easyshop.service.StatisticService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 23:06 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@Slf4j
public class StatisticScheduleJob extends QuartzJobBean {

    @Setter
    private StatisticService statisticService;

    @Setter
    private RedisService redisService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {

        // 获取当日销售额
        Double amount = redisService.count(RedisKeys.TRANSACTION_DATA_KEY);
        if (amount == null) {
            log.error("[Cache] amount data is null in redis");
            // 如果缓存为空，则将数据库中最后一条记录设置到缓存里
            Statistic lastRecord = statisticService.findByMostRecent();
            // 如果对象为空，则只可能是数据表中无记录，这种情况几乎不会发生，故不做判空
            amount = lastRecord.getAmount().doubleValue();
            redisService.initCounter(RedisKeys.TRANSACTION_DATA_KEY, amount);
        }
        // 重置销售额，从0开始计算当日销售额
        // 如果想计算累计销售额，而不是当日销售额，则需要将下面这条代码注释掉
        // 注意，如果想计算累计销售额，则会影响数据库中的记录含义
        redisService.initCounter(RedisKeys.TRANSACTION_DATA_KEY, 0);
        statisticService.saveTransactionData(BigDecimal.valueOf(amount));
    }
}
