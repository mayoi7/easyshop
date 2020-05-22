package com.github.mayoi7.easyshop.server.service.impl;

import com.github.mayoi7.easyshop.constant.PageConstant;
import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.server.mapper.CommodityMapper;
import com.github.mayoi7.easyshop.service.CommodityService;
import com.github.mayoi7.easyshop.service.RedisService;
import com.github.mayoi7.easyshop.utils.KeyValueUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.github.mayoi7.easyshop.constant.PageConstant.PAGE_COUNT;
import static com.github.mayoi7.easyshop.utils.KeyValueUtils.splicePriceValue;

/**
 * @author LiuHaonan
 * @date 9:19 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
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
        int start = page * PAGE_COUNT;
        return commodityMapper.selectByIdDescInPage(start, PAGE_COUNT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveCommodity(Commodity commodity) {
        commodity.setId(null);
        // 商品创建/价格更新 时，需要在缓存中 存储/更新 价格变更队列
        // 队列key为商品id，值为价格和时间链表
        redisService.setInListWithExpire(RedisKeys.PRICE_LIST, commodity.getId().toString(),
                splicePriceValue(commodity.getCurrentPrice()), RedisKeys.DEFAULT_EXPIRE);
        int res = commodityMapper.insertSelective(commodity);
        // 避免数据库双写一致性问题，选择删除缓存而不是覆盖缓存
        redisService.del(RedisKeys.COMMODITY_ID, commodity.getId().toString());
        return res;
    }
}
