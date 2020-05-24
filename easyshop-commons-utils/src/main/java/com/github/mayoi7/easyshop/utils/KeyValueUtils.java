package com.github.mayoi7.easyshop.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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

    @AllArgsConstructor
    public static class PriceInfo {
        public BigDecimal price;

        /** 表示商品价格变更的时间 */
        public long timestamp;
    }

    /**
     * 生成用于价格队列的值，时间属性默认使用当前时间
     * @param price 价格
     * @return 返回拼接后的值
     */
    public static String splicePriceValue(BigDecimal price) {
        long currentTime = System.currentTimeMillis();
        return splicePriceValue(price, currentTime);
    }

    /**
     * 生成用于价格队列的值，时间属性使用预设时间
     * @param price 价格
     * @param timestamp 价格更新时的时间戳
     * @return 返回拼接后的值
     */
    public static String splicePriceValue(BigDecimal price, long timestamp) {
        return price + DEFAULT_DELIMITER + timestamp;
    }

    /**
     * 将价格队列的值拆分出来
     * @param spliceString 拼接后的字符串，即价格队列中的值
     * @return 返回包含拆分后值的数据对象
     */
    public static PriceInfo loadPriceInfo(String spliceString) {
        String[] values = spliceString.split(DEFAULT_DELIMITER);
        if (values.length == 2) {
            BigDecimal price =  BigDecimal.valueOf(Double.parseDouble(values[0]));
            long timestamp = Long.parseLong(values[1]);
            return new PriceInfo(price, timestamp);
        } else {
            return null;
        }
    }
}
