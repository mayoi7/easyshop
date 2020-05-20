package com.github.mayoi7.easyshop.service;

import com.github.mayoi7.easyshop.po.Statistic;

import java.math.BigDecimal;

/**
 * 统计服务
 * @author LiuHaonan
 * @date 17:30 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public interface StatisticService {

    /**
     * 记录交易数据
     * @param amount 当日交易金额
     * @return 返回交易数据记录主键
     */
    int saveTransactionData(BigDecimal amount);

    /**
     * 查询最近日期的记录
     * @return 返回最近日期记录的总交易额记录
     */
    Statistic findByMostRecent();
}
