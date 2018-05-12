package com.study.springboot.multibizmodule;

import com.study.springboot.domain.ABizBean;
import com.study.springboot.domain.BizResponse;
import com.study.springboot.domain.MultiBizDO;
import com.study.springboot.domain.UnCheckedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/5/12
 */
@Slf4j
@Service
public class ServiceContext {
    private List<CommonService> serviceList = new LinkedList<>();

    private static Map<String, Method[]> classID2ClassMethodMap = new HashMap<>();

    public void addService(CommonService commonService) {
        serviceList.add(commonService);
    }

    /**
     * 掉用具体的业务线的方法
     *
     * @param multiBizDO  前台传递的业务标识
     * @param bizDataJson 业务数据，以json形式存储
     * @param map         额外的参数
     * @param methodName  需要被掉用的方法
     * @return
     * @throws Exception
     */
    public BizResponse execute(MultiBizDO multiBizDO, String bizDataJson, Map<String, Object> map, String methodName) throws Exception {
        CommonService service = null;

        Object[] arrs = new Object[]{multiBizDO, bizDataJson, map};
        // 遍历所有的服务
        for (Iterator<CommonService> it = serviceList.iterator(); it
                .hasNext(); ) {
            service = it.next();
            // 职责链找到对应的服务
            if (service.matchBiz(multiBizDO) && service
                    .matchKey((multiBizDO))) {
                // 对应的服务实现功能
                try {
                    //  当方法名比较多的时候，也可以把method存储起来，提高性能，比较简单，可以自己实现
                    Method[] methods = getMethodsByClass(service);
                    for (Method method : methods) {
                        // 注意事项：
                        // 1. 子类的方法名不能有重复，因为这个是根据方法名进行匹配。所以若有重复，可能会掉用错误的方法
                        // 2. 方法的入参必须跟arrs保持一致。因为这个只是简单的名称匹配
                        if (method.getName().equalsIgnoreCase(methodName)) {
                            return (BizResponse) method.invoke(service, arrs);
                        }
                    }
                    throw new Exception("can not found method");

                } catch (IllegalArgumentException e) {
                    log.error(e.getMessage(), e);

                    return BizResponse.builder().message(e.getMessage()).status(-1).build();
                } catch (UnCheckedException e) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

                    return BizResponse.builder().data("").message("自定义异常捕获").status(-1)
                            .build();
                } catch (Exception e) {
                    log.error("执行服务出错:" + e.getMessage(), e);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return BizResponse.builder().message("未知异常").status(-1).build();
                }

            }
        }
        throw new Exception("can not found service");
    }

    private Method[] getMethodsByClass(CommonService service) {
        Class clazz = service.getClass();
        String className = clazz.getName();
        if (null != classID2ClassMethodMap.get(className)) {
            return classID2ClassMethodMap.get(className);
        }
        Method[] methods = clazz.getMethods();
        classID2ClassMethodMap.put(className, methods);
        return methods;
    }

    public static void main(String[] args) {
        ABizBean bean = new ABizBean();
        Class clazz = bean.getClass();
        System.out.println(clazz.getName());
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getTypeName());
    }

}
