package com.github.mayoi7.easyshop.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author LiuHaonan
 * @date 16:17 2020/5/16
 * @email acerola.orion@foxmail.com
 */
@Data
public class LoginParam implements Serializable {

    private static final long serialVersionUID = -1144854814680952811L;

    /** 用户名，5-20位 */
    @Pattern(regexp = "^[^0-9][\\w_]{4,19}$", message = "用户名格式错误，应为5-20位的合法字符")
    private String username;

    /** 密码，5-20位 */
    @Pattern(regexp = "^[\\w_]{5,20}$", message = "密码格式错误，应为5-20位的合法字符")
    private String password;
}
