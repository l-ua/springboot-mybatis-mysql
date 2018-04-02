package com.study.springboot.proxy.jdk;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>BussineService.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/2 10:44<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public interface BussineService {
    public void addOrder();

    public String queryTelphone(String ID);
}
