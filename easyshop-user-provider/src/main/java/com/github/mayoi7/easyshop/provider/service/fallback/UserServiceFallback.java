package com.github.mayoi7.easyshop.provider.service.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.github.mayoi7.easyshop.po.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiuHaonan
 * @date 8:59 2020/6/10
 * @email acerola.orion@foxmail.com
 */
@Slf4j
public class UserServiceFallback {

    public static User findUserByIdHandler(BlockException blockException) {
        log.error("[SENTINEL<USER>] UserService#findUserById be blocked, run fallback");
        return null;
    }
}
