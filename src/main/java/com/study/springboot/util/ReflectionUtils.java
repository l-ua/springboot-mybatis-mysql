package com.study.springboot.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>ReflectionUtils.java<br/>
 * <b>Description：</b> 反射工具类 通过反射给对象属性设置值 包含父类的属性<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2017/12/9 16:30<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public class ReflectionUtils {

    /**
     * 获取这个类的所有属性 包含父类的属性
     * @param object
     * @return List<String>
     */
    public static List<String> getAllFields(Object object) {
        Set<String> set = new HashSet<String>();
        for (Class< ?> clazz = object.getClass(); clazz != Object.class; clazz =
                clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                set.add(field.getName());
            }
        }
        return new ArrayList<String>(set);
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     * 
     * @param object : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;

        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

            }
        }

        return null;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     * 
     * @param object : 子类对象
     * @param fieldName : 父类中的属性名
     * @param value : 将要设置的值
     * @param fieldIDs : 对象属性 包含父类
     */
    public static void setFieldValue(Object object, String fieldName, Object value,
            List<String> fieldIDs) {
        // 获取对象的所有字段
        if (null == fieldIDs || fieldIDs.size() == 0) {
            fieldIDs = getAllFields(object);
        }

        boolean isExist = false;
        // excel里面存的字段全是大写，但是对象属性是区分大小写的
        // 这里就需要通过全大写字符串找到具体对象的属性
        for (String fieldID : fieldIDs) {
            if (fieldID.toUpperCase().equals(fieldName.toUpperCase())) {
                // 这个属性存在当前对象
                isExist = true;
                // 全大写转换成对象属性
                fieldName = fieldID;

                break;
            }
        }
        if (!isExist) {
            /* System.out.println("对象{0}不存在这个属性{1}", object.getClass().getName(), fieldName); */
            return;
        }

        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);

        // 抑制Java对其的检查
        field.setAccessible(true);



        try {
            if ("java.lang.Integer".equals(field.getType().getName())) {
                String strValue = (String) value;
                Integer integerValue = Integer.valueOf(strValue);
                field.set(object, integerValue);
            } else {
                // 将 object 中 field 所代表的值 设置为 value
                field.set(object, value);
            }

        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        /*
         * ApplyOrderBOSSDataVO vo = new ApplyOrderBOSSDataVO(); List<String> fieldList =
         * ReflectionUtils.getAllFields(vo); fieldList.stream().parallel().forEach(field ->
         * System.out.println(field));
         */
    }


}
