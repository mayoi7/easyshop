package com.github.mayoi7.easyshop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LiuHaonan
 * @date 16:17 2020/5/16
 * @email acerola.orion@foxmail.com
 */
@Data
@AllArgsConstructor
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = -1809113199341020608L;

    private String name;
}

