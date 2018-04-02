package com.study.springboot.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>CglibProxy.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/2 11:03<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        log.info("cglib 动态代理   before {}", methodProxy.getSuperName());
        Object result = methodProxy.invokeSuper(o, args);
        log.info("cglib 动态代理   after {}", methodProxy.getSuperName());
        return result;
    }
}
