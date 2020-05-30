package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.po.Inventory;
import com.github.mayoi7.easyshop.server.mapper.InventoryMapper;
import com.github.mayoi7.easyshop.service.InventoryService;
import com.github.mayoi7.easyshop.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LiuHaonan
 * @date 20:25 2020/5/30
 * @email acerola.orion@foxmail.com
 */
@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Reference
    private RedisService redisService;

    @Resource
    private InventoryMapper inventoryMapper;

    @Override
    public Integer checkInventory(Long commodityId) {
        Integer quantity = (Integer) redisService.get(RedisKeys.INVENTORY_COMMODITY, commodityId.toString());
        if (quantity == null) {
            // 如果库存为空则去数据库中查询
            Inventory inventory = inventoryMapper.selectByCommodity(commodityId);
            quantity = inventory.getQuantity();
            // 设置缓存
            redisService.set(RedisKeys.INVENTORY_COMMODITY, commodityId.toString(), quantity);
        }
        return quantity;
    }

    @Override
    public Integer addInventory(Long commodityId, Integer amount) {
        Inventory inventory = inventoryMapper.selectByCommodity(commodityId);
        if (amount == null || amount == 0) {
            return inventory.getQuantity();
        }
        Integer quantity = inventory.getQuantity() + amount;
        inventory.setQuantity(quantity);
        inventoryMapper.updateByPrimaryKeySelective(inventory);
        // 删除缓存
        redisService.del(RedisKeys.INVENTORY_COMMODITY, commodityId.toString());
        return quantity;
    }

    @Override
    public Integer reduceInventory(Long commodityId, Integer amount) {
        Inventory inventory = inventoryMapper.selectByCommodity(commodityId);
        if (amount == null || amount == 0) {
            return inventory.getQuantity();
        }
        if (inventory.getQuantity() < amount) {
            // 库存不足，则禁止操作
            log.warn("[INVENTORY] commodity is out of stock <commodity_id={}>", commodityId);
            // 返回null而不是当前库存，避免调用方判断失误
            return null;
        }
        Integer quantity = inventory.getQuantity() - amount;
        inventory.setQuantity(quantity);
        inventoryMapper.updateByPrimaryKeySelective(inventory);
        return quantity;
    }
}
