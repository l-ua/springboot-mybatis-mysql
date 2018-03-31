package com.study.springboot.util;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>NumberUtils.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2017/12/14 9:49<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public class NumberUtils {
    /**
     * 给数字左侧补0,只补位，不截取
     * 
     * @param number 被补位数字
     * @param fixedLength 数字的固定长度
     * @return String
     */
    public static String fixedZeroAtLeft(String number, int fixedLength) {

        if (fixedLength <= 0) {
            return number;
        }

        // 空不补
        if (ObjectEmptyUtil.isNullOrEmptyObj(number)) {
            return null;
        }
        number = number.trim();

        // 长度超过 无需补位
        if (fixedLength <= number.length()) {
            return number;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(number);
        while (sb.length() < fixedLength) {
            sb = sb.insert(0, 0);
        }
        return sb.toString();
    }

    /**
     * 给数字右侧补fixNumber,只补位，不截取
     *
     * @param number 被补位数字
     * @param fixedLength 数字的固定长度
     * @fixNumber 补位的数字 默认补0
     * @return String
     */
    public static String fixedNumberAtRight(String number, int fixedLength, String fixNumber) {

        if (fixedLength <= 0) {
            return number;
        }

        // 空不补
        if (ObjectEmptyUtil.isNullOrEmptyObj(number)) {
            return null;
        }
        number = number.trim();
        fixNumber = ObjectEmptyUtil.isNullOrEmptyObj(fixNumber) ? "0" : fixNumber;
        // 长度超过 无需补位
        if (fixedLength <= number.length()) {
            return number;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(number);
        while (sb.length() < fixedLength) {
            sb.append(fixNumber);
        }
        return sb.toString();
    }

    /**
     * 截取字符串 从前面开始截取.
     * 
     * @param str 被截取字符串
     * @param maxLength 字符串的最大长度
     * @return String
     */
    public static String captureString(String str, int maxLength) {

        // 字符串的最大长度必须大于等于0
        if (maxLength < 0) {
            return str;
        }
        // 空不补
        if (ObjectEmptyUtil.isNullOrEmptyObj(str)) {
            return null;
        }
        str = str.trim();

        // 长度刚刚好 无需补位
        if (maxLength >= str.length()) {
            return str;
        }

        return str.substring(0, maxLength);
    }

    /**
     * 去除字符串最后一位多余的符号
     * 
     * 
     * @param str 错误信息xxx;错误信息xxx;错误信息xxx;错误信息xxx;
     * @return 错误信息xxx;错误信息xxx;错误信息xxx;错误信息xxx
     */
    public static String captureLastNoUseChar(String str) {
        if (ObjectEmptyUtil.isNullOrEmptyObj(str)) {
            return str;
        }

        if (str.endsWith(";") || str.endsWith("；") || str.endsWith("，") || str.endsWith(",")) {
            return str.substring(0, str.length() - 1);

        }
        return str;

    }



    public static void main(String[] args) throws Exception {
        String number = "1";
        System.out.println(NumberUtils.fixedZeroAtLeft(number, 5));

        String str = "xxxsdaasdf75df5s7afsdasd";

        System.out.println(NumberUtils.captureString(str, 5));

        String chinese = "夕阳下，教师里";
        System.out.println(chinese.length());

        String eng = "we";
        System.out.println(eng.length());
    }
}
