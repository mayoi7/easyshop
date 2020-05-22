package com.github.mayoi7.easyshop.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
 * 订单表
 * @author KurobaAkira
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
@Builder
public class Order implements Serializable {

    private static final long serialVersionUID = 1620189116291746040L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    @Column(name = "user_id" )
    private Long userId;

    @Column(name = "commodity_id" )
    private Long commodityId;

    @Column(name = "quantity" )
    private Integer quantity;

    /**
    * 下单时的价格
    */
    @Column(name = "price" )
    private BigDecimal price;

    /**
    * 总计价格
    */
    @Column(name = "total" )
    private BigDecimal total;

    @Column(name = "create_time" )
    private Date createTime;
}