package com.study.springboot.elasticjob.services;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>TaskServices.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/28 16:07<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Service
@Slf4j
public class TaskServices implements SimpleJob {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void execute(ShardingContext shardingContext) {

        atomicInteger.incrementAndGet();
        if (atomicInteger.get() % 3 == 0) {
            // 就算发生了异常 也不会终止定时任务
            throw new IllegalArgumentException("xxxx");
        }
        String parameter = shardingContext.getJobParameter();

        String jobName = shardingContext.getJobName();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH mm ss");

        String dateStr = format.format(date);
        String taskID = shardingContext.getTaskId();
        log.info("=====当前时间为:{}========", dateStr);
        // log.info("=====当前时间为:{},执行定时任务： {},========", dateStr,jobName);
        log.info("当前PM 2.5为：{}", 10 + new Random().nextInt(200));
        Random random = new Random();
        int sleepTime = 1 + random.nextInt(10);
        try {
            // 当当前任务执行时间耗时超过了定时任务的时间间隔，那么下次任务会在当前任务执行完毕后立即执行
            // Thread.sleep(sleepTime * 1000);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("========定时任务执行完成,本次耗时为{}秒=========", sleepTime);

    }
}
