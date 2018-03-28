package com.study.springboot.kafka.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.study.springboot.kafka.Custom;

import lombok.extern.slf4j.Slf4j;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>CustomImpl.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/27 10:18<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Slf4j
@Service
public class CustomImpl implements Custom {

    /**
     *
     * @param content 服务端发送的消息体，目前只支持byte数组
     */
    @Override
    // kafka 监听服务端的注解
    // group 可以自定义
    // topic 需要被监听的主题
    @KafkaListener(topics = "haha", group = "${spring.kafka.consumer.groupId}")
    public void getMessage(byte[] content) {
        if (null == content) {
            log.info("custom get no message");
        } else {
            log.info("custom get message {}", new String(content));
        }

    }

    public static void main(String[] args) {
        jisuan(1, 30, 7);
    }

    private static void jisuan(int money, int year, int per) {
        Double totalMoney = 0d;
        for (int i = 1; i <= year; i++) {
            Double takeFree = (totalMoney + money) * per / 100D;
            totalMoney += money + takeFree;

            System.out.println("第" + i + "年投入" + money + "万，年回报率为" + per / 100D + ",当年总金额为"
                    + totalMoney + "万，当年收益为" + takeFree + "万元");
        }

    }
}
