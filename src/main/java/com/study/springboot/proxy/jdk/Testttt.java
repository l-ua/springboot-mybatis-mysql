package com.study.springboot.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>Testttt.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/2 10:47<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public class Testttt {
    public static void main(String[] args) {
        BussineService service = new BussineServiceImpl();
        // 创建一个InvocationHandler，描述我们希望代理者执行哪些操作
        InvocationHandler invocationHandler = new MyInvocationHandler(service);
        // 通过刚才创建的InvocationHandler，创建真正的代理者。第一个参数是类加载器，第二个参数是这个代理者实现哪些接口(与被代理者实现的是相同的接口)
        BussineService seviceProxy =
                (BussineService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                        service.getClass().getInterfaces(), invocationHandler);
        seviceProxy.addOrder();
        System.out.println(seviceProxy.queryTelphone("123"));
    }

}
