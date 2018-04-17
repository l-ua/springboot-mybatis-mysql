package com.study.springboot.redis.impl;

import com.study.springboot.redis.RankByRedisService;
import com.study.springboot.redis.RedisService;
import com.study.springboot.util.ObjectEmptyUtil;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>RankByRedisServiceImpl.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/17 11:37<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Service
@Log4j
public class RankByRedisServiceImpl implements RankByRedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    @Override
    public void writeDataToRedis(String key, String subKey, String value, String id, long outTime) {

        // 若不存在，则说明这个人是第一次充值
        // 假设这个是用户的充值时间，具体业务自己实现，可以通过参数的形式把时间传递过来
        Long currentTimeMillis = System.currentTimeMillis();
        // 离结束时间还差多久
        Long xiaoShuPart = outTime - currentTimeMillis;

        if (xiaoShuPart < 0) {
            log.error("充值时间已到期，本次充值不纳入充值排行榜");
        }
        String lock = null;
        try {
            // 数据可能频繁的被更新，这里需要加上个分布式事物锁
            // 同一条数据更新需要加上锁
            lock = redisService.getLock("rank_", "money" + id, 5000L, 10000L);

            if (null == lock) {
                log.error("本次获取锁超时");
                // 这里应该加个重试功能，不然就会有部分充值的金额没有纳入充值排行榜
                return;
            }
            String zsetKey = key + "_" + subKey + "_zset";

            // 获取原有的分数,
            // 分数是一个小数，整数部分是充值的钱。小数部分，是代表最后一次充值距离截止时间还有多久，
            Double oldScore = redisTemplate.opsForZSet().score(zsetKey, id);
            Double newScore = null;


            String newScoreStr;
            if (null == oldScore) {
                newScoreStr = value + "." + xiaoShuPart;
            } else {
                // 存在，则需要对score进行解析
                String[] scoreArr = String.valueOf(oldScore).split("\\.");
                int oldMoney = Integer.valueOf(scoreArr[0]);
                // 离结束时间还差多久
                Long newMoney = oldMoney + Long.valueOf(value);
                newScoreStr = newMoney + "." + xiaoShuPart;

            }
            Assert.assertFalse("战力值不能为空", ObjectEmptyUtil.isNullOrEmptyObj(newScoreStr));
            log.info("当前排行榜的值为：" + newScoreStr);
            newScore = Double.valueOf(newScoreStr);
            // 更新redis充值榜
            redisTemplate.opsForZSet().add(zsetKey, id, newScore);

        } finally {
            if (null != lock) {
                redisService.releaseLock("rank_", "money" + id, lock);
            }
        }


    }

    @Override
    public <T> List<T> queryRankByPage(String key, String subKey, Integer pageNum, Integer pageSize,
            boolean isDesc) {
        String zsetKey = key + "_" + subKey + "_zset";
        // 默认第一页
        pageNum = null == pageNum || 0 == pageNum ? 1 : pageNum;
        // 默认每页10条记录
        pageSize = null == pageSize || 0 == pageSize ? 10 : pageSize;

        // 缓存中有序集合的开始获取数据的位置
        int startIndex = (pageNum - 1) * pageSize;
        // 缓存中有序集合的结束获取数据的位置
        int endIndex = pageNum * pageSize - 1;

        // 获取数据,升序
        Set<ZSetOperations.TypedTuple<String>> set =
                redisTemplate.opsForZSet().rangeWithScores(zsetKey, startIndex, endIndex);

        Set<String> set2 = redisTemplate.opsForZSet().reverseRange(zsetKey, startIndex, endIndex);

        List<String> ids = new ArrayList<>();
        for (String id : set2) {
            log.info(id);
            ids.add(id);
        }
        return (List<T>) ids;
    }
}
