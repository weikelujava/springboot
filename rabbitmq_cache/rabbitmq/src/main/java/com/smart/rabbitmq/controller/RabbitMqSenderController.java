package com.smart.rabbitmq.controller;

import com.smart.rabbitmq.entity.Action;
import com.smart.rabbitmq.producer.FanoutMqSender;
import com.smart.rabbitmq.service.CacheService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: RabbitMqSenderController
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 12:13
 * @remark: 修改内容
 */

@RestController
public class RabbitMqSenderController {

    @Autowired
    private FanoutMqSender fanoutMqSender;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/rabbit/send")
    public void sendByRabbitMq(){
        Action action = new Action();
        action.setCode(0);
        action.setValue("hello,word");
        fanoutMqSender.sendMessage(action);
    }

    @GetMapping("/rabbit/update")
    public void updateByRabbitMq(){
        Action action = new Action();
        action.setCode(1);
        action.setValue("缓存已经更新了");
        fanoutMqSender.sendMessage(action);
    }

    @GetMapping("/rabbit/delete")
    public void delByRabbitMq(){
        Action action = new Action();
        action.setCode(2);
        action.setValue(null);
        fanoutMqSender.sendMessage(action);
    }

    @GetMapping("/get")
    public String get(){
       return cacheService.get();
    }

    @GetMapping("/save")
    public String save(){
        String value = "hello,word 2 !";
        cacheService.save(value);
        return "保存成功";
    }

    @GetMapping("/delete")
    public String delete(){
        cacheService.delete();
        return "删除成功";
    }

    @GetMapping("/cache/delete")
    public String cacheDelete(){
        CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
        if(null != cacheManager){
            cacheManager.removeCache("local");
        }
        return "删除本地缓存成功";
    }

    @GetMapping("/redis/delete")
    public String redisDelete(){
        Set keys = redisTemplate.keys("test*");
        redisTemplate.delete(keys);
        return "删除redis缓存成功";
    }
}
