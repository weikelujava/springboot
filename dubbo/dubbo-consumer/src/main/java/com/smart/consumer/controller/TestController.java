package com.smart.consumer.controller;

import com.smart.common.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: TestController
 * @description:
 * @author: lukewei
 * @date: 2020/6/16 17:17
 * @remark: 修改内容
 */
@RestController
public class TestController {

    @Reference(version = "${demo.service.version}",url = "${demo.service.ulr}")
    private DemoService demoService;


    @GetMapping("/find")
    public String find(){

        return demoService.sayHello();
    }
}
