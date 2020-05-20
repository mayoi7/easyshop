package com.github.mayoi7.easyshop.factory;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LiuHaonan
 * @date 21:07 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public class NormalThreadFactory implements ThreadFactory {

    private static final String DEFAULT_FACTORY_NAME = "MyThreadFactory";

    private static final String DEFAULT_THREAD_NAME = "MyThread";

    private final String namePrefix;

    private final AtomicInteger nextId = new AtomicInteger(1);

    /**
     * 创建线程工厂类
     */
    public NormalThreadFactory() {
        this(DEFAULT_FACTORY_NAME, DEFAULT_THREAD_NAME);
    }

    /**
     * 创建线程工厂类
     * @param threadName 线程名称
     */
    public NormalThreadFactory(String threadName) {
        this(DEFAULT_FACTORY_NAME, threadName);
    }

    /**
     * 创建线程工厂类
     * @param factoryName 线程工厂名称
     * @param threadName 线程名称
     */
    public NormalThreadFactory(String factoryName, String threadName) {
        namePrefix = DEFAULT_FACTORY_NAME + "-" + threadName + "-Worker-";
    }

    @Override
    public Thread newThread(@NotNull Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        return new Thread(null, task, name, 0);
    }
}
