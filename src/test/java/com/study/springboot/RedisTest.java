package com.study.springboot;

import com.study.springboot.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>RedisTest.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/30 14:58<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void redisLockTest() {
        for (int i = 0; i < 9; i++) {
            new Thread() {
                @Override
                public void run() {
                    String keyValue = null;
                    String name = Thread.currentThread().getName();
                    try {
                        keyValue = redisService.getLock("namespace", "key", 1000, 10000);
                        if (null != keyValue) {
                            log.info(name + "抢到redis锁了，redis key对应的value的值为" + keyValue);
                        } else {
                            log.info(name + "在五秒内都未获取到redis锁");
                        }
                    } finally {
                        if (null != keyValue) {
                            redisService.releaseLock("namespace", "key", keyValue);
                            log.info(name + "释放了锁========");
                        }

                    }

                }
            }.start();


        }

    }
}


@Slf4j
class Runner implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private String name;
    private RedisService redisService;

    public Runner(CyclicBarrier cyclicBarrier, String name, RedisService redisService) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
        this.redisService = redisService;
    }

    @Override
    public void run() {
        String keyValue = null;
        try {
            Thread.sleep(10 * (new Random()).nextInt(8));
            keyValue = redisService.getLock("namespace", "key", 5000, 10000);
            if (null != keyValue) {
                log.info(name + "抢到redis锁了，redis key对应的value的值为" + keyValue);
            } else {
                log.info(name + "在五秒内都未获取到redis锁");
            }
            // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            if (null != keyValue) {
                redisService.releaseLock("namespace", "key", keyValue);
                log.info(name + "释放了锁========");
            }

        }

    }
}
