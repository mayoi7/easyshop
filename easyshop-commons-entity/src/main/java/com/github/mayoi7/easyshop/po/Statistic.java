package com.github.mayoi7.easyshop.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 订单金额统计信息表（每天定时持久化一次）
 * @date 10:12 2020/5/18
 * @author LiuHaonan
 * @email acerola.orion@foxmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "statistic")
public class Statistic implements Serializable {

    private static final long serialVersionUID = -3260332225474561021L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    @Column(name = "amount" )
    private BigDecimal amount;

    /**
    * 创建时间，代表某天的统计数据
    */
    @Column(name = "create_time" )
    private Date createTime;

    public Statistic(BigDecimal amount) {
        this.amount = amount;
    }
}