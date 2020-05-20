package com.github.mayoi7.easyshop.statistic.mapper;

import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.po.Statistic;
import tk.mybatis.mapper.MyMapper;

/**
 * 
 * @date 10:12 2020/5/18
 * @author LiuHaonan 
 * @email acerola.orion@foxmail.com
 */
public interface StatisticMapper extends MyMapper<Statistic> {

    Statistic selectLastOne();
}