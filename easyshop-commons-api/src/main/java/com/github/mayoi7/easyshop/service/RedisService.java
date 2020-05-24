package com.github.mayoi7.easyshop.service;

import java.math.BigDecimal;
import java.util.List;

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
     * @param key 缓存的key
     * @param value 缓存的value
     */
    void set(String key, Object value);

    /**
     * 设置分组缓存
     * @param cacheName 主题名
     * @param key 缓存key
     * @param value 缓存value
     */
    void set(String cacheName, String key, Object value);

    /**
     * 设置缓存队列
     * @param key 缓存的key
     * @param value 缓存的value
     * @return 当使用事务或流水线时返回null
     */
    Long setInList(String key, Object value);

    /**
     * 设置带有过期时间的缓存队列
     * @param key 缓存的key
     * @param value 缓存的value
     * @param expire 过期时间（单位：秒）
     * @return 当使用事务或流水线时返回null
     */
    Long setInListWithExpire(String key, Object value, int expire);

    /**
     * 设置分组的缓存队列
     * @param cacheName 缓存分组名
     * @param key 缓存的key
     * @param value 缓存的value
     * @return 当使用事务或流水线时返回null
     */
    Long setInList(String cacheName, String key, Object value);

    /**
     * 设置带有过期时间的分组缓存队列
     * @param cacheName 缓存分组名
     * @param key 缓存的key
     * @param value 缓存的value
     * @param expire 过期时间（单位：秒）
     * @return 当使用事务或流水线时返回null
     */
    Long setInListWithExpire(String cacheName, String key, Object value, int expire);

    /**
     * 从缓存队列中获取
     * @param key 缓存的key
     * @return 返回缓存的value值
     */
    Object getInList(String key);

    /**
     * 从对应组的缓存队列中获取
     * @param cacheName 缓存名称
     * @param key 缓存的key
     * @return 返回缓存的value值
     */
    Object getInList(String cacheName, String key);

    /**
     * 获取队列中最近插入的全部元素
     * @param key 缓存的key
     * @return 返回全部集合，如果不存在则返回null
     */
    List<Object> getAllInList(String key);

    /**
     * 获取缓存组队列中的全部元素
     * @param cacheName 缓存组名
     * @param key 缓存的key
     * @return 返回全部集合，如果不存在则返回null
     */
    List<Object> getAllInList(String cacheName, String key);

    /**
     * 设置缓存，以及其超时时间
     *
     * @param key 缓存的key
     * @param value 缓存的value
     * @param seconds 超时时间，单位为秒
     */
    void setWithExpire(String key, Object value, int seconds);

    /**
     * 设置缓存，以及其超时时间
     * @param cacheName 缓存组名
     * @param key 缓存的key
     * @param value 缓存的value
     * @param seconds 超时时间，单位为秒
     */
    void setWithExpire(String cacheName, String key, Object value, int seconds);

    /**
     * 获取缓存
     * @param key 缓存的key
     * @return key对应的缓存，如果不存在则返回null
     */
    Object get(String key);

    /**
     * 获取缓存
     * @param cacheName 缓存组名
     * @param key 缓存的key
     * @return key对应的缓存，如果不存在则返回null
     */
    Object get(String cacheName, String key);

    /**
     * 删除缓存
     * @param key 要删除的缓存的key
     */
    void del(String key);

    /**
     * 删除缓存
     * @param cacheName 要删除的缓存组名
     * @param key 要删除的缓存的key
     */
    void del(String cacheName, String key);

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
