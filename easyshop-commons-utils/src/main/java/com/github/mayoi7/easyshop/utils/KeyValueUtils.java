package com.github.mayoi7.easyshop.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 该工具类不操作缓存，仅用于生成一些特定的缓存键值
 * @author LiuHaonan
 * @date 15:12 2020/5/22
 * @email acerola.orion@foxmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyValueUtils {

    public static final String DEFAULT_DELIMITER = "#";

    /**
     * 生成用于价格队列的值，时间属性默认使用当前时间
     * @param price 价格
     * @return 返回拼接后的值
     */
    public static String splicePriceValue(BigDecimal price) {
        long currentTime = System.currentTimeMillis();
        return price + DEFAULT_DELIMITER + currentTime;
    }
}
