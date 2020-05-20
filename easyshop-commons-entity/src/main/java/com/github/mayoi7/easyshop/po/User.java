package com.github.mayoi7.easyshop.po;

import java.io.Serializable;
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
 * 用户信息表
 * @author KurobaAkira
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -2899267684466495986L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    @Column(name = "name" )
    private String name;

    @Column(name = "password" )
    private String password;

    @Column(name = "create_time" )
    private Date createTime;

    @Column(name = "update_time" )
    private Date updateTime;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}