package com.smart.consumer.controller;

import com.smart.common.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Reference(version = "1.0.0")
    private TestService testService;


    @GetMapping("/find")
    public String find(){

        return testService.find();
    }
}
