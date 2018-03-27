package com.study.springboot.controller;

import com.study.springboot.kafka.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>KafkaControllerTest.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/3/27 10:36<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@RestController
public class KafkaControllerTest {
    @Autowired
    Product product;

    @RequestMapping("/kafkaTest")
    public void kafkaSendMessage() {
        product.sendMessage(null, null);
    }
}
