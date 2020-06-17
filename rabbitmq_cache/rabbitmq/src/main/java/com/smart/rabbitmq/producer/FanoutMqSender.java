package com.smart.rabbitmq.producer;

import com.alibaba.fastjson.JSONObject;
import com.smart.rabbitmq.entity.Action;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 * @version V1.0
 * @title: FanoutMqSender
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 11:27
 * @remark: 修改内容
 */
@Component
public class FanoutMqSender {

    @Value("${mq.rabbitmq.fanout.exchange}")
    private String fanoutExchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Action action){
        CorrelationData correlationData  = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(fanoutExchangeName,"", JSONObject.toJSONString(action),correlationData);
        System.out.println("已经发送消息到rabbitmq");
    }
}
