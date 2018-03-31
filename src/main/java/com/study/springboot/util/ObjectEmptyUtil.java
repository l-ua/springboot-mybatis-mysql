package com.study.springboot.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>ObjectEmptyUtil.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2017/12/11 9:59<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public class ObjectEmptyUtil {
    /**
     * 判断对象是否为空 或者空对象
     * @param obj
     * @return boolean
     */
    public static boolean isNullOrEmptyObj(Object obj) {

        if (!Optional.ofNullable(obj).isPresent()) {
            return true;
        }

        // list类型判断
        if (obj instanceof List) {
            if (((List) obj).size() > 0) {
                return false;
            } else {
                return true;

            }

        }

        // map 类型判断
        if (obj instanceof Map) {
            if (((Map) obj).size() > 0) {
                return false;
            } else {
                return true;

            }
        }

        // string 类型判断
        if (obj instanceof String) {
            if ("".equals(((String) obj).trim())) {
                return true;
            } else {
                return false;

            }
        }

        return !Optional.ofNullable(obj).isPresent();
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        System.out.println(ObjectEmptyUtil.isNullOrEmptyObj(map));
        map.put("1", 1);
        System.out.println(ObjectEmptyUtil.isNullOrEmptyObj(map));

    }

}
