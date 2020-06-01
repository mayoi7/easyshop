package com.github.mayoi7.easyshop.constant;

/**
 * @author LiuHaonan
 * @date 20:01 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public class RedisKeys {

    /** 缓存默认保存7天 */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 7;

    /** 订单额统计 */
    public static final String TRANSACTION_DATA = "statistic#trans_data";

    /** 用户名 */
    public static final String USER_NAME = "easyshop#user@name";

    /** 用户id */
    public static final String USER_ID = "easyshop#user@id";

    /** 商品id */
    public static final String COMMODITY_ID = "easyshop#commodity@id";

    /** 通过用户id查询订单列表 */
    public static final String ORDER_USER = "easyshop#order@user";

    /** 商品库存缓存 */
    public static final String INVENTORY_COMMODITY = "easyshop#inventory@commodity";

    /** 价格变更列表 */
    public static final String PRICE_LIST = "easyshop#price";

    /** 购物车缓存 */
    public static final String CART_LIST = "easyshop#cart";

    /** 订单缓存锁 */
    public static final String ORDER_LOCK = "easyshop#orderkey" ;

}
