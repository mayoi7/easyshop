package com.github.mayoi7.easyshop.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author LiuHaonan
 * @date 8:37 2020/5/16
 * @email acerola.orion@foxmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.github.mayoi7.easyshop.provider.mapper")
public class UserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }
}
