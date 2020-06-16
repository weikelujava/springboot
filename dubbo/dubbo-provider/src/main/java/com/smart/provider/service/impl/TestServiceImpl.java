package com.smart.provider.service.impl;

import com.smart.common.service.TestService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: TestServiceImpl
 * @description:
 * @author: lukewei
 * @date: 2020/6/16 17:09
 * @remark: 修改内容
 */
@Service(version = "1.0.0")
@Component
public class TestServiceImpl implements TestService{


    public String find() {
        System.out.println("查询操作");
        return "这是一个查询操作";
    }
}
