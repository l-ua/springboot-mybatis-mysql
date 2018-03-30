package com.study.springboot.redis.impl;

import com.study.springboot.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>RedisServiceImpl.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/30 11:15<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getSeq(String seqKey) {
        return null;
    }

    /**
     *
     * @param nameSpace 锁的key值分两部分，一部分是namespace ,一部分是业务ID，拼接起来 namespace:key
     * @param key 业务ID
     * @param acquireTimeout 获取锁的时间，超过这个时间则，返回null
     * @param timeout 锁key在redis中保留时间，超过这个时间自动删除
     * @return
     */
    @Override
    public String getLock(String nameSpace, String key, long acquireTimeout, long timeout) {
        //
        String lockKey = nameSpace.concat(key);
        // 随机生成一个value
        String identifier = UUID.randomUUID().toString();

        long endTime = System.currentTimeMillis() + acquireTimeout;
        while (System.currentTimeMillis() < endTime) {
            // setIfAbsent 若redis已存在lockkey，则返回false，不存在则会把对象放进缓存中去
            if (redisTemplate.opsForValue().setIfAbsent(lockKey, identifier)) {
                /**
                 * timeout : 对象存在缓冲中的时长，超过即销毁， TimeUnit.MILLISECONDS ：时间单位
                 */
                redisTemplate.expire(lockKey, timeout, TimeUnit.MILLISECONDS);
                return identifier;
            }

            // 返回-1代表key没有设置超时时间，为key设置一个超时时间
            // 做个保险防止上面的设置超时时间出错
            if (redisTemplate.getExpire(lockKey, TimeUnit.MILLISECONDS) < 0) {
                redisTemplate.expire(lockKey, timeout, TimeUnit.MILLISECONDS);
            }

            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return null;
    }

    @Override
    public boolean releaseLock(String nameSpace, String key, String lockStr) {
        String lockKey = nameSpace.concat(key);
        // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
        if (lockStr.equals(redisTemplate.opsForValue().get(lockKey))) {
            redisTemplate.delete(lockKey);
        }
        return true;
    }

    @Override
    public void hset(String key, String field, String value) {

    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public boolean hexists(String key, String field) {
        return false;
    }

    @Override
    public long hdel(String key, Object... field) {
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        // 随机生成一个value
        String identifier = UUID.randomUUID().toString();

        String identifier2 = UUID.randomUUID().toString();
        Thread.sleep(1000);
        String identifier3 = UUID.randomUUID().toString();

        System.out.println(identifier.equals(identifier2));
        System.out.println(identifier2);
        System.out.println(identifier3);
    }
}
