package com.smart.rabbitmq.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: RedissionConfig
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 15:24
 * @remark: 修改内容
 */

@Configuration
public class RedissonConfig {

    @Value("${redisson.address}")
    private String addressUrl;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient getRedission(){
        RedissonClient redisson = null;
        Config config = new Config();
        config.useSingleServer().setAddress(addressUrl);
        redisson = Redisson.create(config);
        return redisson;
    }
}
