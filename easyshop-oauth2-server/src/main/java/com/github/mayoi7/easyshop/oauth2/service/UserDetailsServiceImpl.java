package com.github.mayoi7.easyshop.oauth2.service;

import org.springframework.security.core.userdetails.User;
import com.github.mayoi7.easyshop.service.UserService;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiuHaonan
 * @date 16:11 2020/5/16
 * @email acerola.orion@foxmail.com
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 所有用户默认分配user权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);

        com.github.mayoi7.easyshop.po.User user = userService.findUserByName(s);
        // 账号存在
        if (user != null) {
            return new User(user.getName(), user.getPassword(), grantedAuthorities);
        } else {
            return null;
        }
    }
}