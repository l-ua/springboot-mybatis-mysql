package com.study.springboot.multibizmodule;

import com.study.springboot.domain.BizResponse;
import com.study.springboot.domain.MultiBizDO;

import java.util.List;
import java.util.Map;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
public interface CommonService<T> {

    /**
     * 增
     *
     * @param multiBizDO
     * @param bizDataJson
     * @param map
     * @return
     */
    BizResponse batchAdd(MultiBizDO multiBizDO, String bizDataJson, Map<String, Object> map);

    /**
     * 删
     *
     * @param multiBizDO
     * @param bizDataJson
     * @param map
     * @return
     */
    //BizResponse batchDel(MultiBizDO multiBizDO, String bizDataJson, Map<String, Object> map);

    /**
     * 改
     *
     * @param multiBizDO
     * @param bizDataJson
     * @param map
     * @return
     */
    //BizResponse batchUpdate(MultiBizDO multiBizDO, String bizDataJson, Map<String, Object> map);

    /**
     * 查
     *
     * @param multiBizDO
     * @param bizDataJson
     * @param map
     * @return
     */
    //BizResponse queryList(MultiBizDO multiBizDO, String bizDataJson, Map<String, Object> map);

    /**
     * 分页查询
     *
     * @param multiBizDO
     * @param bizDataJson
     * @param map
     * @return
     */
    //BizResponse queryListForPage(MultiBizDO multiBizDO, String bizDataJson, Map<String, Object> map);


    /**
     * 匹配业务线
     *
     * @param multiBizDO
     * @return
     */
    boolean matchBiz(MultiBizDO multiBizDO);

    /**
     * 匹配key
     *
     * @param multiBizDO
     * @return
     */
    boolean matchKey(MultiBizDO multiBizDO);
}
