package com.github.mayoi7.easyshop.statistic.service.impl;

import com.github.mayoi7.easyshop.po.Statistic;
import com.github.mayoi7.easyshop.service.StatisticService;
import com.github.mayoi7.easyshop.statistic.mapper.StatisticMapper;
import com.github.mayoi7.easyshop.statistic.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 17:40 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Resource
    private StatisticMapper statisticMapper;

    @Resource
    private RedisService redisService;

    @Override
    public int saveTransactionData(BigDecimal amount) {
        if (amount == null) {
            return -1;
        }
        return statisticMapper.insert(new Statistic(amount));
    }

    @Override
    public Statistic findByMostRecent() {
        return statisticMapper.selectLastOne();
    }
}
