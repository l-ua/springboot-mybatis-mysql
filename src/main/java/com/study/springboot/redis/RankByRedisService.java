package com.study.springboot.redis;

import java.util.List;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>RankByRedisService.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/17 11:15<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public interface RankByRedisService {

    /**
     * 充值排行榜-redis实现</br>
     * 需求：</br>
     * 1。充钱一样的，先冲的排在前面</br>
     * 2。充钱不一样的，多的排在前面</br>
     * 3。在规定时间内充钱才有效，超过时间限制的那部分充值的钱不会纳入统计</br>
     * 4。支持实时刷新</br>
     * redis存储选择 有序集合 zset </br>
     * zset add key sorce value</br>
     * 
     * key = 业务key+对象key </br>
     * sorce = value + '.' + outTime-当前时间</br>
     * value = 用户id</br>
     * 
     * @param key 业务key
     * @param subKey 对象key
     * @param value 值（比如 战力排行榜就是指战力，点赞排行榜就是指点赞数)
     * @param id 用户id
     * @param outTime 一般排行榜都有个时间限制，比如七日内，24小时
     */
    void writeDataToRedis(String key, String subKey, String value, String id, long outTime);

    /**
     * 获取排行榜的信息
     * 
     * @param <T>
     * @return
     */
    <T> List<T> queryRankByPage(String key, String subKey, Integer pageNum, Integer pageSize,
            boolean isDesc);

}
