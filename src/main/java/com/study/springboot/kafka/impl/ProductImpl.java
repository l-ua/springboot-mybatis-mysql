package com.study.springboot.kafka.impl;

import com.study.springboot.kafka.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>ProductImpl.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/27 10:15<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Slf4j
@Service
public class ProductImpl implements Product {
    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void sendMessage(String topic, byte[] message) {
        topic = "haha";
        Random random = new Random();
        String messageByte = "message " + random.nextInt(10000);
        log.info("kafka start send message topic : {}, message : {}", topic, messageByte);
        // topic：发送的主题，message：发送的消息
        kafkaTemplate.send(topic, messageByte.getBytes());
        log.info("kafka end send message topic : {}, message : {}", topic, messageByte);
    }
}
