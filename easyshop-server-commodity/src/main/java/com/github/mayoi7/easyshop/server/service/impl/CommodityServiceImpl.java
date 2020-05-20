package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.constant.PageConstant;
import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.server.mapper.CommodityMapper;
import com.github.mayoi7.easyshop.service.CommodityService;
import com.github.mayoi7.easyshop.service.RedisService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuHaonan
 * @date 9:19 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Reference
    private RedisService redisService;

    @Override
    public Commodity findById(Long commodityId) {
        if (commodityId == null ){
            return null;
        }
        Commodity commodity = (Commodity) redisService.get(RedisKeys.COMMODITY_ID, commodityId.toString());
        if (commodity == null) {
            commodity = commodityMapper.selectByPrimaryKey(commodityId);
            redisService.set(RedisKeys.COMMODITY_ID, commodityId.toString(), commodity);
        }
        return commodity;
    }

    @Override
    public List<Commodity> findListByIdDescInPage(int page) {
        if (page < 0) {
            return new ArrayList<>(2);
        }
        // 计算起始
        int currentCount = page * PageConstant.PAGE_COUNT;
        return null;
    }
}
