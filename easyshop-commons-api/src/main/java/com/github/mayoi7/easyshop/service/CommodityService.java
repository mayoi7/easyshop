package com.github.mayoi7.easyshop.service;

import com.github.mayoi7.easyshop.po.Commodity;

import java.util.List;

/**
 * 商品服务，提供商品信息的查询
 * @author LiuHaonan
 * @date 9:19 2020/5/17
 * @email acerola.orion@foxmail.com
 */
public interface CommodityService {

    /**
     * 根据id进行查询
     * @param commodityId 商品id
     * @return 如果不存在则返回null
     */
    Commodity findById(Long commodityId);

    /**
     * 根据时间倒序分页查询
     * @param page 页号，从0开始计
     * @return 返回查询的结果
     */
    List<Commodity> findListByIdDescInPage(int page);

    /**
     * 添加新商品，禁止更新，在执行时会将id属性置空
     * @param commodity 商品
     * @return 返回是否添加成功的响应
     */
    int saveCommodity(Commodity commodity);
}
