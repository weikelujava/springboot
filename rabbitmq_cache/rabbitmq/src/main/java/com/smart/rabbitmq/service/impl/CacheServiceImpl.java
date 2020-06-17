package com.smart.rabbitmq.service.impl;

import com.smart.rabbitmq.service.CacheService;
import com.smart.rabbitmq.service.DistributedLocker;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *
 * @version V1.0
 * @title: CacheServiceImpl
 * @description:
 * @author: lukewei
 * @date: 2020/6/17 13:21
 * @remark: 修改内容
 */
@Service
public class CacheServiceImpl implements CacheService {

    private static final String CACHE_NAME = "local";
    private static final String CACHE_KEY = "test";

    private static final String REDISSON_KEY = "redission_key";

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private DistributedLocker distributedLocker;

    @Override
    public String get() {
        CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
        if (null != cacheManager) {
            Cache cache = cacheManager.getCache(CACHE_NAME);
            if (null != cache) {
                Element element = cache.get(CACHE_KEY);
                Object objectValue = element.getObjectValue();
                if (null != objectValue && !"".equals(objectValue.toString())) {
                    System.out.println("从ehCache中获取数据");
                    return objectValue.toString();
                }
            }
        }
        // 从redis中取
        Object redisObj = redisTemplate.opsForValue().get(CACHE_KEY);
        if (null != redisObj && !"".equals(redisObj.toString())) {
            System.out.println("ehCache中没有数据,从Redis中获取");
            cacheManager = ehCacheCacheManager.getCacheManager();
            if (null != cacheManager) {
                Cache cache = cacheManager.getCache(CACHE_NAME);
                if (null == cache) {
                    cache = new Cache(CACHE_NAME, 100000, true, true, 0, 0);
                    ehCacheCacheManager.getCacheManager().addCache(cache);
                }
                cache.put(new Element(CACHE_KEY, redisObj));
                cache.flush();
                System.out.println("ehcache中没有数据,从Redis中获取后并写入到ehcache中");
            }
            return redisObj.toString();
        }

        System.out.println("Redis中没有数据,从mysql中获取");
        redisObj = "数据库中数据: hell0,word!";
        save(redisObj);
        return "数据库中数据: hell0,word!";
    }

    @Override
    public void save(Object value) {
        //保存到 EhCache
        boolean isGetLock = distributedLocker.tryLock(REDISSON_KEY, TimeUnit.SECONDS, 5L, 10L);

        if (isGetLock) {
            CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
            if (cacheManager != null) {
                Cache cache = cacheManager.getCache(CACHE_NAME);
                if (null == cache) {
                    cache = new Cache(CACHE_NAME, 100000, true, true, 0, 0);
                    ehCacheCacheManager.getCacheManager().addCache(cache);
                }
                cache.put(new Element(CACHE_KEY, value));
                cache.flush();
            }
            redisTemplate.opsForValue().set(CACHE_KEY, value);
        }

    }

    @Override
    public void delete() {
        System.out.println("开始删除 ehcache 缓存");
        CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
        if (cacheManager != null) {
            cacheManager.removeCache(CACHE_NAME);
        }
        System.out.println("开始删除 redis 缓存");
        redisTemplate.delete(CACHE_KEY);
    }
}
