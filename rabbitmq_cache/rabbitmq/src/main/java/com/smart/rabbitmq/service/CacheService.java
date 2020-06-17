package com.smart.rabbitmq.service;

/**
 *
 * @version V1.0
 * @title: CacheService
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 13:20
 * @remark: 修改内容
 */
public interface CacheService {

    /**
     * 获取数据
     *  1.ehcache -> redis -> mysql
     *  2.ehcache中无，从redis中拿到后并写入到ehcache中
     * @return value
     */
    String get();

    /**
     * 保存或者更新数据
     *  1.ehcache -> redis
     * @param value  value
     */
    void save(Object value);

    /**
     * 删除数据
     */
    void delete();
}
