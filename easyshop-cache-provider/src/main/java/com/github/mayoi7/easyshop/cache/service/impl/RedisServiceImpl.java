package com.github.mayoi7.easyshop.cache.service.impl;

import com.github.mayoi7.easyshop.service.RedisService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicDouble;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuHaonan
 * @date 16:20 2020/5/20
 * @email acerola.orion@foxmail.com
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final long LIST_MAX_AMOUNT = 20;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private String spliceKey(String group, String key) {
        // 编译时会自动转换为StringBuilder的拼接方式
        return group + "::" + key;
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String group, String key, Object value) {
        set(spliceKey(group, key), value);
    }

    @Override
    public Long setInList(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long setInListWithExpire(String key, Object value, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long setInList(String group, String key, Object value) {
        return setInList(spliceKey(group, key), value);
    }

    @Override
    public Long setInListWithExpire(String group, String key, Object value, long expire) {
        return setInListWithExpire(spliceKey(group, key), value, expire);
    }

    @Override
    public Object getInList(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Object getInList(String group, String key) {
        return getInList(spliceKey(group, key));
    }

    @Override
    public List<Object> getAllInList(String key) {
        Long size = redisTemplate.opsForList().size(key);
        if (size == null) {
            return new ArrayList<>();
        }
        return redisTemplate.opsForList().range(key, 0, size);
    }

    @Override
    public List<Object> getAllInList(String group, String key) {
        return getAllInList(spliceKey(group, key));
    }


    @Override
    public void setWithExpire(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void setWithExpire(String group, String key, Object value, long seconds) {
        setWithExpire(spliceKey(group, key), value, seconds);
    }

    @Override
    public void setInSet(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public void setInSet(String group, String key, Object value) {
        setInSet(spliceKey(group, key), value);
    }

    @Override
    public void setInSetWithExpire(String key, Object value, long seconds) {
        redisTemplate.opsForSet().add(key, value);
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void setInSetWithExpire(String group, String key, Object value, long seconds) {
        setInSetWithExpire(spliceKey(group, key), value, seconds);
    }

    @Override
    public Boolean checkExistenceInSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public Boolean checkExistenceInSet(String group, String key, Object value) {
        return checkExistenceInSet(spliceKey(group, key), value);
    }

    @Override
    public Set<Object> getAllInSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Set<Object> getAllInSet(String group, String key) {
        return getAllInSet(spliceKey(group, key));
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object get(String group, String key) {
        return get(spliceKey(group, key));
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void del(String group, String key) {
        del(spliceKey(group, key));
    }

    @Override
    public void removeFromSet(String key, Object value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public void removeFromSet(String group, String key, Object value) {
        removeFromSet(spliceKey(group, key), value);
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
