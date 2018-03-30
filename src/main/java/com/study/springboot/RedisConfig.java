package com.study.springboot;

import com.study.springboot.redis.RedisService;
import com.study.springboot.redis.impl.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>RedisConfig.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/30 11:13<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisService redisService() {
        return new RedisServiceImpl();
    }

    @Bean
    public ListOperations<String, String> listOperations(StringRedisTemplate redisTemplate) {
        return redisTemplate.opsForList();
    }
}
