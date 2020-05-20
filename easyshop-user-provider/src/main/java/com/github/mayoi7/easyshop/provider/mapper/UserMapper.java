package com.github.mayoi7.easyshop.provider.mapper;

import com.github.mayoi7.easyshop.po.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

/**
 * 
 * @date 17:04 2020/5/15
 * @author LiuHaonan 
 * @email acerola.orion@foxmail.com
 */
public interface UserMapper extends MyMapper<User> {

    User selectByName(@Param("name") String name);
}