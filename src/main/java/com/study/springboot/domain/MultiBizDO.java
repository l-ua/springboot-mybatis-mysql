package com.study.springboot.domain;

import lombok.Data;

import java.util.Map;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
@Data
public class MultiBizDO {

    // 业务线类型
    private String serviceType;

    // 唯一主键。用户去判断是哪条业务线的那个具体的方法
    private String bizKey;

    // 放一些各个具体方法需要的额外参数
    private Map<String, Object> map;

    // 这里可以放一些不同业务线公共属性的字段
    // 比如 交通工具 分 汽车 火车  公交车  。。。那么他们的公共属性有比如 名字，载客量等等
    // 具体业务具体处理了

}
