package com.study.springboot.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>MyInvocationHandler.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/2 10:31<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Slf4j
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    private MyInvocationHandler() {

    }

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("jdk 动态代理 before {}", method.getName());
        Object result = method.invoke(target, args);
        log.info("jdk 动态代理 after {}", method.getName());
        return result;
    }
}
