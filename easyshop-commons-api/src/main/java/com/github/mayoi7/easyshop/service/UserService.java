package com.github.mayoi7.easyshop.service;

import com.github.mayoi7.easyshop.po.User;

/**
 * 用户服务，提供用户信息查询与创建等功能
 * @author LiuHaonan
 * @date 12:32 2020/5/16
 * @email acerola.orion@foxmail.com
 */
public interface UserService {

    /**
     * 通过主键id查询对应用户
     * @param id 主键
     * @return 查询到的用户，如果为空则返回null
     */
    User findUserById(Long id);

    /**
     * 根据用户名查询对应用户
     * @param name 用户名
     * @return 如果查询无结果则返回空
     */
    User findUserByName(String name);

    /**
     * 插入用户数据
     * @param user 用户信息，其中主键和时间信息会被忽略
     * @return 返回插入是否成功，插入后的主键会反映在user对象中
     */
    int insertUser(User user);

    /**
     * 更新用户数据
     * @param user 用户信息
     * @return 返回更新是否成功，插入后的主键会反映在user对象中
     */
    int updateUser(User user);
}
