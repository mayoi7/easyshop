package com.github.mayoi7.easyshop.dto.commodity;

import com.github.mayoi7.easyshop.po.Commodity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品数据，用于接收参数
 *
 * @author LiuHaonan
 * @date 16:40 2020/5/21
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityParam implements Serializable {

    private static final long serialVersionUID = -8884124602627351135L;

    private Long id;

    private String name;

    private String description;

    private MultipartFile image;

    private Double price;
}
