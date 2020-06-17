package com.smart.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: RabbitMqApplication
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 12:12
 * @remark: 修改内容
 */
@SpringBootApplication
@EnableCaching
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class,args);
    }
}
