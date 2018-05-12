package com.study.springboot.multibizmodule.impl;

import com.study.springboot.domain.*;
import com.study.springboot.multibizmodule.AbstractCommonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
@Service
@Slf4j
public class BBizServiceImpl extends AbstractCommonServiceImpl<BBizBean> {
    @Override
    protected void bathSaveData(List<BBizBean> data, MultiBizDO multiBizDO, Map<String, Object> map) {
        log.info("B业务线在对数据进行存储");
    }

    @Override
    protected List<ErrorBean> checkData(List<BBizBean> data, MultiBizDO multiBizDO, Map<String, Object> map) {
        log.info("B业务线在执行数据校验");
        return null;
    }

    @Override
    protected void beforeCheck(List<BBizBean> data, MultiBizDO multiBizDO, Map<String, Object> map) {
        log.info("B业务线正在做校验前处理");
    }

    @Override
    public boolean matchBiz(MultiBizDO multiBizDO) {
        return ServiceTypeEnum.B.getCode().equals(multiBizDO.getServiceType());
    }

    @Override
    public boolean matchKey(MultiBizDO multiBizDO) {
        return Const.Key.B_KEY.equals(multiBizDO.getBizKey());
    }
}