package com.github.mayoi7.easyshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @date 17:04 2020/5/15
 * @author LiuHaonan 
 * @email acerola.orion@foxmail.com
 */
/**
 * 库存表
 * @author KurobaAkira
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 6798621736289799245L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    /**
    * 商品id
    */
    @Column(name = "commodity_id" )
    private Long commodityId;

    /**
    * 库存
    */
    @Column(name = "quantity" )
    private Integer quantity;
}