package com.github.mayoi7.easyshop.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuHaonan
 * @date 12:00 2020/5/19
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Value("${my.config}")
    private String config;

    @GetMapping("/config")
    public String getCustomConfig() {
        return config;
    }
}
