package com.github.mayoi7.easyshop.statistic.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 提供线程池
 * @author LiuHaonan
 * @date 22:37 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public class CustomThreadPoolFactory {

    private ThreadFactory threadFactory;

    public CustomThreadPoolFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public ThreadPoolTaskExecutor getInstance() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setThreadFactory(new StatisticThreadFactory());
        taskExecutor.setQueueCapacity(10);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
