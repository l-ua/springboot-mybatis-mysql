package com.study.springboot.multibizmodule;

import com.study.springboot.domain.BizResponse;
import com.study.springboot.domain.ErrorBean;
import com.study.springboot.domain.MultiBizDO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
public abstract class AbstractCommonServiceImpl<T> implements CommonService<T>, InitializingBean {

    /**
     * 具体数据对象的实体类，在子类初始化完成
     */
    protected Class<T> entityClass;

    @Autowired
    protected ServiceContext serviceContext;

    /**
     * 多业务线新增的公共逻辑，具体逻辑各自子类实现
     *
     * @param multiBizDO
     * @param bizDataJson
     * @param map
     * @return
     */
    @Override
    public BizResponse batchAdd(MultiBizDO multiBizDO, String bizDataJson, Map<String, Object> map) {
        List<T> data = json2List(bizDataJson);

        // 校验前准备
        beforeCheck(data, multiBizDO, map);
        // 校验数据
        List<ErrorBean> checkResult = checkData(data, multiBizDO, map);

        if (null != checkResult || checkResult.size() > 0) {
            return BizResponse.builder().message("批量新增失败").status(-1).data(JSON.toJSONString(checkResult)).build();
        }
        // 数据入库
        bathSaveData(data, multiBizDO, map);

        return BizResponse.builder().message("批量新增成功").status(0).build();
    }

    protected abstract void bathSaveData(List<T> data, MultiBizDO multiBizDO, Map<String, Object> map);

    protected abstract List<ErrorBean> checkData(List<T> data, MultiBizDO multiBizDO, Map<String, Object> map);

    protected abstract void beforeCheck(List<T> data, MultiBizDO multiBizDO, Map<String, Object> map);

    protected T json2T(String bizDataJson) {
        return (T) JSON.parseObject(bizDataJson, entityClass.getClass());
    }

    protected List<T> json2List(String bizDataJson) {
        return (List<T>) JSON.parseArray(bizDataJson, entityClass.getClass());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 链表模式，把动作服务统一收集
        serviceContext.addService(this);
        // 获取实际运行的类的直接超类的泛型类型
        Type t = getClass().getGenericSuperclass();
        // 如果该泛型类型是参数化类型
        if (t instanceof ParameterizedType) {
            // 如果该泛型类型是参数化类型
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            // 这样就在父类里面找到具体的子类的实现类
            this.entityClass = (Class<T>) p[0];

        }
    }
}
