package com.study.springboot.proxy.cglib;

import com.study.springboot.proxy.jdk.BussineService;
import com.study.springboot.proxy.jdk.BussineServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>CTest.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/2 11:07<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public class CTest {
    public static void main(String[] args) {

        CglibProxy cglibProxy = new CglibProxy();
        // 主要的增强类
        Enhancer enhancer = new Enhancer();
        // 设置父类，被增强的类
        enhancer.setSuperclass(BussineServiceImpl.class);
        // 回调对象
        enhancer.setCallback(cglibProxy);
        // 用cglibProxy来增强BussineServiceImpl
        BussineService o = (BussineService) enhancer.create();
        System.out.println(o.queryTelphone("1111111"));
        o.addOrder();
    }
}
