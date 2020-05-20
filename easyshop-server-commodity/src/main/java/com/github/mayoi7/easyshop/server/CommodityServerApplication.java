package com.github.mayoi7.easyshop.server;

import com.github.mayoi7.easyshop.server.message.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author LiuHaonan
 * @date 8:32 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableBinding(MessageSource.class)
@MapperScan(basePackages = "com.github.mayoi7.easyshop.server.mapper")
public class CommodityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityServerApplication.class, args);
    }
}
