package com.github.mayoi7.easyshop.server;

import com.github.mayoi7.easyshop.server.message.MessageSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;

/**
 * @author LiuHaonan
 * @date 8:32 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(MessageSource.class)
@MapperScan(basePackages = "com.github.mayoi7.easyshop.server.mapper")
public class CommodityServerApplication {

    @Value("${image.upload.home}")
    private String uploadHome;

    public static void main(String[] args) {
        SpringApplication.run(CommodityServerApplication.class, args);
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadHome);
        return factory.createMultipartConfig();
    }
}
