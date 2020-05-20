package com.github.mayoi7.easyshop.statistic;

import com.github.mayoi7.easyshop.statistic.sink.DataStatisticSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author LiuHaonan
 * @date 10:23 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(DataStatisticSink.class)
@MapperScan(basePackages = "com.github.mayoi7.easyshop.statistic.mapper")
public class DataStatisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStatisticApplication.class, args);
    }
}
