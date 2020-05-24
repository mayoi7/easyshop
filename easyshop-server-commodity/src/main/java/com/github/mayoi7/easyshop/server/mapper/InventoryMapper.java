package com.github.mayoi7.easyshop.server.mapper;


import com.github.mayoi7.easyshop.po.Inventory;
import tk.mybatis.mapper.MyMapper;

/**
 * 
 * @date 17:04 2020/5/15
 * @author LiuHaonan 
 * @email acerola.orion@foxmail.com
 */
public interface InventoryMapper extends MyMapper<Inventory> {

    /**
     * 查询商品库存
     * @param commodityId 商品id
     * @return 返回库存对象
     */
    Inventory selectByCommodity(Long commodityId);
}