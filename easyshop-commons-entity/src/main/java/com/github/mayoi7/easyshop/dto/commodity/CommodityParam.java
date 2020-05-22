package com.github.mayoi7.easyshop.dto.commodity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LiuHaonan
 * @date 16:40 2020/5/21
 * @email acerola.orion@foxmail.com
 */
@Data
public class CommodityParam {

    private Long id;

    private String name;

    private String description;

    private MultipartFile image;

    private Double price;
}
