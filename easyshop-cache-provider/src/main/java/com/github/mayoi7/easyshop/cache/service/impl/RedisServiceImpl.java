package com.github.mayoi7.easyshop.cache.service.impl;

import com.github.mayoi7.easyshop.service.RedisService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicDouble;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuHaonan
 * @date 16:20 2020/5/20
 * @email acerola.orion@foxmail.com
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final int LIST_MAX_AMOUNT = 20;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private String spliceKey(String cacheName, String key) {
        // 编译时会自动转换为StringBuilder的拼接方式
        return cacheName + "::" + key;
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String cacheName, String key, Object value) {
        set(spliceKey(cacheName, key), value);
    }

    @Override
    public Long setInList(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long setInListWithExpire(String key, Object value, int expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long setInList(String cacheName, String key, Object value) {
        return setInList(spliceKey(cacheName, key), value);
    }

    @Override
    public Long setInListWithExpire(String cacheName, String key, Object value, int expire) {
        return setInListWithExpire(spliceKey(cacheName, key), value, expire);
    }

    @Override
    public Object getInList(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Object getInList(String cacheName, String key) {
        return getInList(spliceKey(cacheName, key));
    }

    @Override
    public List<Object> getAllInList(String key) {
        return redisTemplate.opsForList().range(key, 0, LIST_MAX_AMOUNT - 1);
    }

    @Override
    public List<Object> getAllInList(String cacheName, String key) {
        return getAllInList(spliceKey(cacheName, key));
    }

    @Override
    public void setWithExpire(String key, Object value, int seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void setWithExpire(String cacheName, String key, Object value, int seconds) {
        setWithExpire(spliceKey(cacheName, key), value, seconds);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object get(String cacheName, String key) {
        return get(spliceKey(cacheName, key));
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void del(String cacheName, String key) {
        del(spliceKey(cacheName, key));
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