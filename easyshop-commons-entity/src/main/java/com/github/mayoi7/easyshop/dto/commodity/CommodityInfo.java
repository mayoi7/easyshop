package com.github.mayoi7.easyshop.dto.commodity;

import com.github.mayoi7.easyshop.po.Commodity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息，用于参数传递和结果返回
 * @author LiuHaonan
 * @date 7:20 2020/6/8
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityInfo implements Serializable {

    private static final long serialVersionUID = -8329872101310099778L;

    /** 商品id */
    private Long id;

    /** 商品创建人用户名 */
    private String creator;

    /** 商品名称 */
    private String name;

    /** 商品描述 */
    private String description;

    /** 商品图地址 */
    private String image;

    /** 商品当前价格 */
    private BigDecimal price;

    /** 商品创建时间 */
    private Date createTime;

    public CommodityInfo(Commodity commodity) {
        this.id = commodity.getId();
        this.creator = commodity.getCreator();
        this.name = commodity.getName();
        this.description = commodity.getDescription();
        this.image = commodity.getImage();
        this.price = commodity.getCurrentPrice();
        this.createTime = commodity.getCreateTime();
    }
}
