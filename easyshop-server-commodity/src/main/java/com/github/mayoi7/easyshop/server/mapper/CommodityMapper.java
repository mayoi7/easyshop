package com.github.mayoi7.easyshop.server.mapper;

import com.github.mayoi7.easyshop.po.Commodity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * 
 * @date 17:04 2020/5/15
 * @author LiuHaonan 
 * @email acerola.orion@foxmail.com
 */
public interface CommodityMapper extends MyMapper<Commodity> {

    /**
     * 按id（时间）倒序查询商品信息
     * @param start 查询起始条目（当前页 * 每页展示数量）（第一页从0开始计数）
     * @param pageCount 每页展示数量
     * @return 返回商品列表
     */
    List<Commodity> selectByIdDescInPage(@Param("start") int start,
                                         @Param("pageCount") int pageCount);
}