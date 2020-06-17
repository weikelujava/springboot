package com.smart.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: FanoutRabbitMqConfig
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 11:13
 * @remark: 修改内容
 */

@Configuration
public class FanoutRabbitMqConfig {

    @Value("${mq.rabbitmq.fanout.exchange}")
    private String fanoutExchangeName;

    @Value("${mq.rabbitmq.fanout.queue}")
    private String fanoutQueueName;

//    @Bean
//    public Queue fanoutQueue(){
//        return new Queue(fanoutQueueName,true);
//    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(fanoutExchangeName,true,false);
    }
//
//    @Bean
//    public Binding bindingFanoutExchange(){
//        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
//    }



}
