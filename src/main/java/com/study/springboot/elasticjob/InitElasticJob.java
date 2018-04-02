package com.study.springboot.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.internal.schedule.JobRegistry;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.study.springboot.elasticjob.services.TaskServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>InitElasticJob.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/28 15:48<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Component
@Slf4j
public class InitElasticJob implements CommandLineRunner {

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private TaskServices taskServices;

    @Override
    public void run(String... strings) throws Exception {

        log.info("==========从数据库查询需要执行的任务列表============");
        // 先注释掉
        // createTask();
    }

    private void createTask() {
        // 刪掉之前的job
        // JobRegistry.getInstance().shutdown(jobName);


        log.info("------------------开始创建任务------------------");

        // 生成cron表达式
        String cronExpresion = createCronFormatter();

        String jobName = "show Time";
        // .shardingItemParameters(null)
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(jobName, cronExpresion, 1)
                .jobParameter(null).build();
        SimpleJobConfiguration simpleJobConfig =
                new SimpleJobConfiguration(coreConfig, taskServices.getClass().getCanonicalName());
        LiteJobConfiguration jobConfig =
                LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
        SpringJobScheduler jobScheduler =
                new SpringJobScheduler(taskServices, zookeeperRegistryCenter, jobConfig);
        jobScheduler.init();

        log.info("------------------任务创建完成------------------");
    }

    private String createCronFormatter() {
        // crontab范例
        // 每隔5秒执行一次：*/5 * * * * ?

        // 每隔1分钟执行一次：0 */1 * * * ?

        // 每天23点执行一次：0 0 23 * * ?

        // 每天凌晨1点执行一次：0 0 1 * * ?

        // 每月1号凌晨1点执行一次：0 0 1 1 * ?

        // 每月最后一天23点执行一次：0 0 23 L * ?

        // 每周星期天凌晨1点实行一次：0 0 1 ? * L

        // 在26分、29分、33分执行一次：0 26,29,33 * * * ?

        // 每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

        return "*/5 * * * * ?";
    }
}
