package com.smart.rabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import com.smart.rabbitmq.entity.Action;
import com.smart.rabbitmq.service.CacheService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @version V1.0
 * @title: FanoutConsumer
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 11:33
 * @remark: 修改内容
 */

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue,
        exchange = @Exchange(name = "${mq.rabbitmq.fanout.exchange}", type = ExchangeTypes.FANOUT)))
public class FanoutConsumer2 {

    @Autowired
    private CacheService cacheService;


    @RabbitHandler
    private void process(String msg){
        System.out.println("FanoutConsumer2 接收到消息:"+msg);
        Action action = JSON.parseObject(msg, Action.class);
        switch (action.getCode()){
            case 0:
                System.out.println("FanoutConsumer2 保存缓存:");
                cacheService.save(action.getValue());
                break;
            case 1:
                System.out.println("FanoutConsumer2 更新缓存:");
                cacheService.save(action.getValue());
                break;
            case 2:
                System.out.println("FanoutConsumer2 删除缓存:");
                cacheService.delete();
                break;
            default:
                break;
        }
    }


}
