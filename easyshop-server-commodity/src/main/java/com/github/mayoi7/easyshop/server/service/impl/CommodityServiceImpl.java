package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.constant.PageConstant;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.server.mapper.CommodityMapper;
import com.github.mayoi7.easyshop.service.CommodityService;
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

    @Override
    @Cacheable(cacheNames = "easyshop#commodity@id", key = "#commodityId")
    public Commodity findById(Long commodityId) {
        if (commodityId == null ){
            return null;
        } else {
            return commodityMapper.selectByPrimaryKey(commodityId);
        }
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
