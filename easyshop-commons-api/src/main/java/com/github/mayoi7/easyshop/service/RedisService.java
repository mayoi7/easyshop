package com.github.mayoi7.easyshop.service;

import java.math.BigDecimal;

/**
 * 缓存接口
 * @author LiuHaonan
 * @date 18:06 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public interface RedisService {

    /**
     * 设置缓存
     *
     * @param key   缓存的key
     * @param value 缓存的value
     */
    void set(String key, Object value);

    /**
     * 设置缓存，以及其超时时间
     *
     * @param key     缓存的key
     * @param value   缓存的value
     * @param seconds 超时时间，单位为秒
     */
    void set(String key, Object value, int seconds);

    /**
     * 获取缓存
     *
     * @param key 缓存的key
     * @return key对应的缓存，如果不存在则返回null
     */
    Object get(String key);

    /**
     * 删除缓存
     *
     * @param key 要删除的缓存的key
     */
    void del(String key);

    /**
     * 统计计数值
     * @param key 计数值的key
     * @return 计数值
     */
    Double count(String key);

    /**
     * 初始化计数器
     * @param key 计数值的key
     * @param initVal 初始值
     */
    void initCounter(String key, double initVal);

    /**
     * 增加计数值（先增加再返回）
     * @param key 计数值的key
     * @param added 增加的值
     * @return 增加后的结果
     */
    Double addAndGet(String key, Double added);
}
