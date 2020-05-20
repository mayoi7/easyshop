package com.github.mayoi7.easyshop.statistic.thread;

import com.github.mayoi7.easyshop.factory.NormalThreadFactory;

/**
 * @author LiuHaonan
 * @date 21:20 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public class StatisticThreadFactory extends NormalThreadFactory {

    public static final String FACTORY_NAME = "[statistic-thread-factory]";

    public static final String THREAD_NAME = "[statistic-thread]";

    public StatisticThreadFactory() {
        super(FACTORY_NAME, THREAD_NAME);
    }
}
