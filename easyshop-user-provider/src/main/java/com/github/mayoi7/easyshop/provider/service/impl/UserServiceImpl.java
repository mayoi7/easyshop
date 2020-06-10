package com.github.mayoi7.easyshop.provider.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.mayoi7.easyshop.constant.RedisKeys;
import com.github.mayoi7.easyshop.po.User;
import com.github.mayoi7.easyshop.provider.mapper.UserMapper;
import com.github.mayoi7.easyshop.provider.service.fallback.UserServiceFallback;
import com.github.mayoi7.easyshop.service.RedisService;
import com.github.mayoi7.easyshop.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.logging.log4j.util.Strings;
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

    @Reference
    private RedisService redisService;

    @Override
    @SentinelResource(value = "findUserById",
            blockHandlerClass = UserServiceFallback.class, blockHandler = "findUserByIdHandler")
    public User findUserById(Long id) {
        if (id == null) {
            return null;
        }
        User user = (User) redisService.get(RedisKeys.USER_ID, id.toString());
        if (user == null) {
            user = userMapper.selectByPrimaryKey(id);
            redisService.set(RedisKeys.USER_ID, id.toString(), user);
        }
        return user;
    }

    @Override
    public User findUserByName(String name) {
        if (Strings.isEmpty(name)) {
            return null;
        }
        User user = (User) redisService.get(RedisKeys.USER_NAME, name);
        if (user != null) {
            return user;
        } else {
            user = userMapper.selectByName(name);
            redisService.set(RedisKeys.USER_NAME, name, user);
            return user;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user) {
        user.setId(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);

        int result = userMapper.insertSelective(user);
        redisService.del(RedisKeys.USER_NAME, user.getName());
        redisService.del(RedisKeys.USER_ID, user.getId().toString());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        if (user.getId() == null) {
            return -1;
        }
        user.setUpdateTime(null);
        int result = userMapper.updateByPrimaryKeySelective(user);
        redisService.del(RedisKeys.USER_NAME, user.getName());
        redisService.del(RedisKeys.USER_ID, user.getId().toString());
        return result;
    }

}
