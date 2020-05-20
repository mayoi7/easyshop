package com.github.mayoi7.easyshop.provider.service.impl;

import com.github.mayoi7.easyshop.po.User;
import com.github.mayoi7.easyshop.provider.mapper.UserMapper;
import com.github.mayoi7.easyshop.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author LiuHaonan
 * @date 8:25 2020/5/16
 * @email acerola.orion@foxmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Cacheable(cacheNames = "easyshop#user@id", key = "#id")
    public User findUserById(Long id) {
        if (id == null) {
            return null;
        }
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Cacheable(cacheNames = "easyshop#user@name", key = "#name")
    public User findUserByName(String name) {
        if (Strings.isEmpty(name)) {
            return null;
        } {
            return userMapper.selectByName(name);
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "easyshop#user@name", key = "#user.name"),
            @CacheEvict(cacheNames = "easyshop#user@id", key = "#user.id")
    })
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user) {
        user.setId(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);

        return userMapper.insertSelective(user);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "easyshop#user@name", key = "#user.name"),
            @CacheEvict(cacheNames = "easyshop#user@id", key = "#user.id")
    })
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        if (user.getId() == null) {
            return -1;
        }
        user.setUpdateTime(null);
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
