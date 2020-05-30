package com.github.mayoi7.easyshop.service;

/**
 * 库存表
 * @author LiuHaonan
 * @date 20:19 2020/5/30
 * @email acerola.orion@foxmail.com
 */
public interface InventoryService {

    /**
     * 查询库存数量
     * @param commodityId 商品id
     * @return 返回商品当前的库存
     */
    Integer checkInventory(Long commodityId);

    /**
     * 添加库存
     * @param commodityId 商品id
     * @param amount 要添加库存的数量
     * @return 返回更新后的库存
     */
    Integer addInventory(Long commodityId, Integer amount);

    /**
     * 减少库存
     * @param commodityId 商品id
     * @param amount 要减少库存的数量
     * @return 返回更新后的库存
     */
    Integer reduceInventory(Long commodityId, Integer amount);
}
