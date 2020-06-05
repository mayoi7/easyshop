package com.github.mayoi7.easyshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 交易数据，用于消息传递
 * @author LiuHaonan
 * @date 15:29 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransData implements Serializable {

    private static final long serialVersionUID = 7860069228008895256L;

//    /** 交易编号，对应order表编号 */
//    private Long id;

    /** 交易用户的用户id */
    private Long userId;

    /** 交易金额 */
    private BigDecimal amount;
}
