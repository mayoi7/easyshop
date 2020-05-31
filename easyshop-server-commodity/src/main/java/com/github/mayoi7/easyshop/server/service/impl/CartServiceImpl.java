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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        redisService.setInList(RedisKeys.CART_LIST, username, commodityId);
        return true;
    }

    @Override
    public List<CartCommodity> loadCart(String username) {
        List<Object> rawCartList = redisService.getAllInList(RedisKeys.CART_LIST, username);
        List<CartCommodity> cartList = new ArrayList<>(rawCartList.size());
        if (rawCartList.isEmpty()) {
            return cartList;
        }
        rawCartList.forEach(id -> {
            Long commodityId = Long.parseLong(id.toString());
            Commodity commodity = commodityService.findById(commodityId);
            Integer quantity = inventoryService.checkInventory(commodityId);
            cartList.add(new CartCommodity(commodity, quantity));
        });
        return cartList;
    }
}
