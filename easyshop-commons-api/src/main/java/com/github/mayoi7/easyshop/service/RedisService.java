package com.github.mayoi7.easyshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
     * @param group 主题名
     * @param key 缓存key
     * @param value 缓存value
     */
    void set(String group, String key, Object value);

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
    Long setInListWithExpire(String key, Object value, long expire);

    /**
     * 设置分组的缓存队列
     * @param group 缓存分组名
     * @param key 缓存的key
     * @param value 缓存的value
     * @return 当使用事务或流水线时返回null
     */
    Long setInList(String group, String key, Object value);

    /**
     * 设置带有过期时间的分组缓存队列
     * @param group 缓存分组名
     * @param key 缓存的key
     * @param value 缓存的value
     * @param expire 过期时间（单位：秒）
     * @return 当使用事务或流水线时返回null
     */
    Long setInListWithExpire(String group, String key, Object value, long expire);

    /**
     * 从缓存队列中获取
     * @param key 缓存的key
     * @return 返回缓存的value值
     */
    Object getInList(String key);

    /**
     * 从对应组的缓存队列中获取
     * @param group 缓存名称
     * @param key 缓存的key
     * @return 返回缓存的value值
     */
    Object getInList(String group, String key);

    /**
     * 获取队列中最近插入的全部元素
     * @param key 缓存的key
     * @return 返回全部集合，如果不存在则返回null
     */
    List<Object> getAllInList(String key);

    /**
     * 获取缓存组队列中的全部元素
     * @param group 缓存组名
     * @param key 缓存的key
     * @return 返回全部集合，如果不存在则返回null
     */
    List<Object> getAllInList(String group, String key);

    /**
     * 设置缓存，以及其超时时间
     *
     * @param key 缓存的key
     * @param value 缓存的value
     * @param seconds 超时时间，单位为秒
     */
    void setWithExpire(String key, Object value, long seconds);

    /**
     * 设置缓存，以及其超时时间
     * @param group 缓存组名
     * @param key 缓存的key
     * @param value 缓存的value
     * @param seconds 超时时间，单位为秒
     */
    void setWithExpire(String group, String key, Object value, long seconds);

    /**
     * 设置缓存到集合
     * @param key 键
     * @param value 值
     */
    void setInSet(String key, Object value);

    /**
     * 设置缓存到集合
     * @param group 缓存组名
     * @param key 键
     * @param value 值
     */
    void setInSet(String group, String key, Object value);

    /**
     * 设置缓存到集合，带有过期时间
     * @param key 键
     * @param value 值
     * @param seconds 过期时间，单位秒
     */
    void setInSetWithExpire(String key, Object value, long seconds);

    /**
     * 设置缓存到集合，带有过期时间
     * @param group 缓存组名
     * @param key 键
     * @param value 值
     * @param seconds 过期时间，单位秒
     */
    void setInSetWithExpire(String group, String key, Object value, long seconds);

    /**
     * 检查某值是否在集合中存在
     * @param key 键
     * @param value 值
     * @return 返回对应键值是否存在，true：存在
     */
    Boolean checkExistenceInSet(String key, Object value);

    /**
     * 检查某值是否在集合中存在
     * @param group 缓存组名
     * @param key 键
     * @param value 值
     * @return 返回对应键值是否存在，true：存在
     */
    Boolean checkExistenceInSet(String group, String key, Object value);

    /**
     * 获取缓存集合中所有元素
     * @param key 键
     * @return 返回元素集合
     */
    Set<Object> getAllInSet(String key);

    /**
     * 获取缓存集合中所有元素
     * @param group 缓存组名
     * @param key 键
     * @return 返回元素集合
     */
    Set<Object> getAllInSet(String group, String key);

    /**
     * 获取缓存
     * @param key 缓存的key
     * @return key对应的缓存，如果不存在则返回null
     */
    Object get(String key);

    /**
     * 获取缓存
     * @param group 缓存组名
     * @param key 缓存的key
     * @return key对应的缓存，如果不存在则返回null
     */
    Object get(String group, String key);

    /**
     * 删除缓存
     * @param key 要删除的缓存的key
     */
    void del(String key);

    /**
     * 删除缓存
     * @param group 要删除的缓存组名
     * @param key 要删除的缓存的key
     */
    void del(String group, String key);

    /**
     * 从set集合中删除指定缓存
     * @param key 缓存的键
     * @param value 缓存的值
     */
    void removeFromSet(String key, Object value);

    /**
     * 从set集合中删除指定缓存
     * @param group 缓存组名
     * @param key 缓存的键
     * @param value 缓存的值
     */
    void removeFromSet(String group, String key, Object value);
    
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
