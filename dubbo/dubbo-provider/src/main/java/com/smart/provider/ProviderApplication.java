package com.smart.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: ProviderApplication
 * @description:
 * @author: lukewei
 * @date: 2020/6/16 17:04
 * @remark: 修改内容
 */
@SpringBootApplication
@EnableDubbo
public class ProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProviderApplication.class,args);
        //在容器上启动服务提供者
    }
}
