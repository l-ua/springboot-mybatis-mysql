package com.study.springboot.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <b>System：</b>ncc<br/>
 * <b>Title：</b>PageHelpUtils.java<br/>
 * <b>Description：</b> 对功能点的描述<br/>
 * <b>@author： </b>raoluping<br/>
 * <b>@date：</b>2018/2/6 19:07<br/>
 * <b>@version：</b> 1.0.0.0 <br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
public class PageHelpUtils {
    /**
     * 
     * @param list 对象全集，集合必须是有序的
     * @param pageNum 第几页
     * @param pageSize 一页的数据个数
     * @return
     */
    public static PageInfo page(List list, Integer pageNum, Integer pageSize) {

        if (ObjectEmptyUtil.isNullOrEmptyObj(list)) {
            return new PageInfo<>();
        }

        pageNum = (pageNum == null || pageNum == 0) ? 1 : pageNum;
        pageSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        int startIndex = (pageNum - 1) * pageSize;

        if (startIndex >= list.size() || startIndex < 0) {
            return new PageInfo<>();
        }

        int endIndex = (pageNum + 1) * pageSize;
        endIndex = endIndex >= list.size() ? list.size() : endIndex;


        PageInfo pageInfo = new PageInfo<>();
        pageInfo.setTotal(list.size());
        pageInfo.setIsFirstPage(1 == pageNum);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setHasNextPage((pageNum + 1) * pageSize > list.size());
        pageInfo.setHasPreviousPage((pageNum - 1) * pageSize >= 0);
        pageInfo.setIsLastPage((pageNum + 1) * pageSize > list.size());
        pageInfo.setList(list.subList(startIndex, endIndex));

        return pageInfo;

    }

}
