package com.smart.rabbitmq.entity;

import java.io.Serializable;

/**
 *
 * @version V1.0
 * @title: Action
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 14:20
 * @remark: 修改内容
 */
public class Action implements Serializable {

    private static final long serialVersionUID = 2466519078757852300L;

    private Integer code;

    private Object value;

    public Action() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
