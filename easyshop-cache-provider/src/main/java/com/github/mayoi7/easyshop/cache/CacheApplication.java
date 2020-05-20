package com.github.mayoi7.easyshop.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author LiuHaonan
 * @date 16:02 2020/5/20
 * @email acerola.orion@foxmail.com
 */
@SpringBootApplication
@EnableCaching
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
