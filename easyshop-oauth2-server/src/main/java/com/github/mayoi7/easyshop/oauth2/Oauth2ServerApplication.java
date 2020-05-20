package com.github.mayoi7.easyshop.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiuHaonan
 * @date 8:45 2020/5/16
 * @email acerola.orion@foxmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }
}
