package com.smart.rabbitmq.service;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * All rights Reserved, Designed By http://www.hollysmart.com.cn
 *
 * @version V1.0
 * @title: DistributedLocker
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 15:34
 * @remark: 修改内容
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, long timeout);

    RLock lock(String lockKey, TimeUnit unit, long timeout);

    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);


}
