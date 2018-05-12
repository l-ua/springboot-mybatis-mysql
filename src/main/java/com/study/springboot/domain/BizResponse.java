package com.study.springboot.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
@Data
@Builder
public class BizResponse {
    private int status;

    private String data;

    private String errorCode;

    private String message;
}
