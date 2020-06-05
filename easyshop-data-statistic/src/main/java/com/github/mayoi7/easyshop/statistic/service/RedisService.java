package com.github.mayoi7.easyshop.statistic.service;

import org.springframework.data.redis.support.atomic.RedisAtomicDouble;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuHaonan
 * @date 9:22 2020/6/3
 * @email acerola.orion@foxmail.com
 */
public interface RedisService {

    void set(String key, Object value);

    void set(String cacheName, String key, Object value);


    void setWithExpire(String key, Object value, int seconds);

    void setWithExpire(String cacheName, String key, Object value, int seconds);

    Object get(String key);

    Object get(String cacheName, String key);

    void del(String key);

    void del(String cacheName, String key);

    Double count(String key);

    Double count(String group, String key);

    void initCounter(String key, double initVal);

    void initCounter(String group, String key, double initVal);

    Double addAndGet(String key, Double added);

    Double addAndGet(String group, String key, Double added);
}
