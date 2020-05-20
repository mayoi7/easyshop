package com.github.mayoi7.easyshop.cache.redis;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiuHaonan
 * @date 16:54 2020/5/20
 * @email acerola.orion@foxmail.com
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
//@NacosConfigurationProperties(
//        dataId = "easyshop-cache-provider-dev.yaml",
//        prefix = "spring.redis.cluster")
public class RedisClusterConfigProperties {
    private List<String> nodes;

    private Integer maxRedirects;
//
//    private Integer maxAttempts;
//
//    private Integer connectionTimeout;
//
//    private Integer soTimeout;

    private String password;
}
