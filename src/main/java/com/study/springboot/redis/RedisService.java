package com.study.springboot.redis;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>RedisService.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/30 11:15<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public interface RedisService {
    /**
     * 获取SEQ
     *
     * @param seqKey
     * @return String
     */
    String getSeq(String seqKey);

    /**
     * 获取redis分布式锁
     *
     * @param nameSpace 锁的key值分两部分，一部分是namespace ,一部分是业务ID，拼接起来 namespace:key
     * @param key 业务ID
     * @param acquireTimeout 获取锁的时间，超过这个时间则，返回null
     * @param timeout 锁key在redis中保留时间，超过这个时间自动删除
     * @return 锁内容，利于释放锁时使用
     */
    String getLock(String nameSpace, String key, long acquireTimeout, long timeout);

    /**
     * 释放redis分布式锁
     *
     * @param nameSpace 锁的key值分两部分，一部分是namespace ,一部分是业务ID，拼接起来 namespace:key
     * @param key 业务ID
     * @param lockStr 锁消息内容
     * @return 解锁成功还是是失败
     */
    boolean releaseLock(String nameSpace, String key, String lockStr);


    /**
     * Hash设置值得操作
     *
     * @param key
     * @param field
     * @param value
     */
    void hset(String key, String field, String value);

    /**
     * Hash获取值操作
     *
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * Hash判断值是否存在
     *
     * @param key
     * @param field
     * @return
     */
    boolean hexists(String key, String field);

    /**
     * Hash删除一个值
     *
     * @param key
     * @param field
     * @return
     */
    long hdel(String key, Object... field);
}
