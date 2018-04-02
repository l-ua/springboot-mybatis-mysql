package com.study.springboot.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * <b>System：</b>springboot-mybatis-mysql<br/>
 * <b>Title：</b>BussineServiceImpl.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/4/2 10:45<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Slf4j
public class BussineServiceImpl implements BussineService {
    @Override
    public void addOrder() {
        log.info("添加订单成功");
    }

    @Override
    public String queryTelphone(String ID) {
        log.info("根据id{}查询用户的手机号码", ID);
        return ID;
    }
}
