package com.study.springboot.controller;

import com.study.springboot.domain.BizResponse;
import com.study.springboot.domain.MultiBizDO;
import com.study.springboot.domain.MultiBizRequestVO;
import com.study.springboot.multibizmodule.ServiceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
@RestController
@RequestMapping("/multiBizTest")
@Slf4j
public class MultiBizControllerTest {

    @Autowired
    private ServiceContext serviceContext;

    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    public BizResponse batchAdd(@RequestBody MultiBizRequestVO request) {
        MultiBizDO multiBizDO = request.getMultiBizDO();
        String bizJsonData = request.getBizJson();
        Map<String, Object> map = multiBizDO.getMap();

        BizResponse response = null;
        try {
            response = serviceContext.execute(multiBizDO, bizJsonData, map, "batchAdd");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return response;
    }
}
