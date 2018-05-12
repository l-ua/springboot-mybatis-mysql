package com.study.springboot.domain;

import lombok.Data;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
@Data
public class MultiBizRequestVO {

    private MultiBizDO multiBizDO;

    // 具体业务线的数据。数据传输是以json的形式
    private String bizJson;
}
