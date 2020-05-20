package com.github.mayoi7.easyshop.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 
 * @date 17:04 2020/5/15
 * @author LiuHaonan 
 * @email acerola.orion@foxmail.com
 */
/**
 * 商品表
 * @author KurobaAkira
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commodity")
public class Commodity implements Serializable {

    private static final long serialVersionUID = -3398349913349106975L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    /**
    * 商品名称
    */
    @Column(name = "name" )
    private String name;

    /**
    * 商品详情
    */
    @Column(name = "description" )
    private String description;

    /**
    * 商品图片路径
    */
    @Column(name = "image" )
    private String image;

    /**
    * 原价
    */
    @Column(name = "original_price" )
    private BigDecimal originalPrice;

    /**
    * 现价
    */
    @Column(name = "current_price" )
    private BigDecimal currentPrice;

    /**
    * 商品创建/编辑者名称，默认为system
    */
    @Column(name = "creator" )
    private String creator;

    @Column(name = "create_time" )
    private Date createTime;

    @Column(name = "update_time" )
    private Date updateTime;
}