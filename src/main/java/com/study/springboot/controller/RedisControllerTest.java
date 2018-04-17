package com.study.springboot.controller;

import com.study.springboot.redis.RankByRedisService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>RedisControllerTest.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/17 14:18<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Log4j
@RestController
public class RedisControllerTest {

    @Autowired
    private RankByRedisService rankByRedisService;

    @RequestMapping("/redisWriteTest")
    public void redisWriteTest() {

        log.info("开启多线程，模仿并发写数据");

        Long outTime = System.currentTimeMillis() + 60 * 1000;

        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 5; i++) {
            new Thread() {

                @Override
                public void run() {
                    String id = "user_" + atomicInteger.incrementAndGet();

                    while (true) {
                        ThreadLocalRandom random = ThreadLocalRandom.current();
                        int moneny = random.nextInt(10000);
                        rankByRedisService.writeDataToRedis("zset", "test", String.valueOf(moneny),
                                id, outTime);
                        log.info(String.format("用户%s本次充值了%s", id, String.valueOf(moneny)));
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

    }


    @RequestMapping("/readRank")
    public List<String> readRank() {
        List<String> res = rankByRedisService.queryRankByPage("zset", "test", null, null, true);
        return res;

    }
}
