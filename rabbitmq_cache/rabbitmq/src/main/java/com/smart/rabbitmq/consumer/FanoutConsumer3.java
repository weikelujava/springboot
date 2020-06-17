package com.smart.rabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import com.smart.rabbitmq.entity.Action;
import com.smart.rabbitmq.service.CacheService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
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
public class FanoutConsumer3 {

    @Autowired
    private CacheService cacheService;


    @RabbitHandler
    private void process(String msg){
        System.out.println("FanoutConsumer3 接收到消息:"+msg);
        Action action = JSON.parseObject(msg, Action.class);
        switch (action.getCode()){
            case 0:
                System.out.println("FanoutConsumer3 保存缓存:");
                cacheService.save(action.getValue());
                break;
            case 1:
                System.out.println("FanoutConsumer3 更新缓存:");
                cacheService.save(action.getValue());
                break;
            case 2:
                System.out.println("FanoutConsumer3 删除缓存:");
                cacheService.delete();
                break;
            default:
                break;
        }
    }


}
