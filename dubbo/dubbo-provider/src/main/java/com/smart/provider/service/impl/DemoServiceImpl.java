package com.smart.provider.service.impl;

import com.smart.common.service.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @class DemoServiceImpl
 * @auther weikelu
 * @date 2020/6/17 22:10
 * @remark
 * @description
 */
@Service(version = "${demo.service.version}")
@Component
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello() {
        return "hello dubbo";
    }
}
