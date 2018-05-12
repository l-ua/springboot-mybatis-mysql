package com.study.springboot.domain;

import lombok.Getter;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
@Getter
public enum ServiceTypeEnum {
    A("001", "A业务线"), B("002", "B业务线"), C("003", "C业务线");
    private String code;

    private String value;

    private ServiceTypeEnum(String code, String value) {
        this.code=code;
        this.value=value;
    }
}
