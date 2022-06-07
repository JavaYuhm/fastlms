package com.javayuhm.fastlms.course.controller;

import com.javayuhm.fastlms.util.PageUtil;

public class BaseController {

    public String getPagerHtml(long totalCount, long pageSize, long pageIndex, String queryString){
        PageUtil pageUtil = new PageUtil(totalCount, pageSize,pageIndex,queryString);
        return pageUtil.pager();
    }

}
