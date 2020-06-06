package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.dto.cart.CartCommodity;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.service.CartService;
import com.github.mayoi7.easyshop.service.CommodityService;
import com.github.mayoi7.easyshop.service.InventoryService;
import com.github.mayoi7.easyshop.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author LiuHaonan
 * @date 20:02 2020/5/30
 * @email acerola.orion@foxmail.com
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Reference
    private RedisService redisService;

    @Resource
    private CommodityService commodityService;

    @Resource
    private InventoryService inventoryService;

    @Override
    public boolean addCart(String username, Long commodityId) {
        if (redisService.checkExistenceInSet(RedisKeys.CART_LIST, username, commodityId)) {
            return false;
        }
        redisService.setInSet(RedisKeys.CART_LIST, username, commodityId);
        return true;
    }

    @Override
    public List<CartCommodity> loadCart(String username) {
        Set<Object> rawCartList = redisService.getAllInSet(RedisKeys.CART_LIST, username);
        // 因为缓存中是正序数据，查询时需要逆序数据，所以采用链表进行首追加操作
        LinkedList<CartCommodity> cartList = new LinkedList<>();
        if (rawCartList.isEmpty()) {
            return cartList;
        }
        rawCartList.forEach(id -> {
            Long commodityId = Long.parseLong(id.toString());
            Commodity commodity = commodityService.findById(commodityId);
            Integer quantity = inventoryService.checkInventory(commodityId);
            cartList.addFirst(new CartCommodity(commodity, quantity));
        });
        return cartList;
    }

    @Override
    public boolean removeCart(String username, Long commodityId) {
        if (redisService.checkExistenceInSet(RedisKeys.CART_LIST, username, commodityId)) {
            redisService.removeFromSet(RedisKeys.CART_LIST, username, commodityId);
        }
        return true;
    }

    @Override
    public boolean test(int value) {
        return value == 1;
    }
}
