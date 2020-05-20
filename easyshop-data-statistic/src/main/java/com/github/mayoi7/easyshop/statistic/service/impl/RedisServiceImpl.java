package com.github.mayoi7.easyshop.statistic.service.impl;

import com.github.mayoi7.easyshop.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicDouble;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuHaonan
 * @date 18:06 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, int seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Double count(String key) {
        RedisAtomicDouble counter = new RedisAtomicDouble(key,
                Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return counter.get();
    }

    @Override
    public void initCounter(String key, double initVal) {
        RedisAtomicDouble counter = new RedisAtomicDouble(key,
                Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        counter.set(initVal);
    }

    @Override
    public Double addAndGet(String key, Double added) {
        RedisAtomicDouble counter = new RedisAtomicDouble(key,
                Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return counter.addAndGet(added);
    }
}
