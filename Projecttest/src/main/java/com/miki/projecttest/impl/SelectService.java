package com.miki.projecttest.impl;

import com.miki.projecttest.model.SelectModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 包名:      com.miki.projecttest.impl
 * 文件名:     SelectService.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 15:42
 * 描述:      TODO
 */

public interface SelectService {

    //精选接口 http://v3.wufazhuce.com:8000/api/hp/more/0

    public static final String BaseURL = "http://v3.wufazhuce.com:8000/";

    @GET("api/hp/more/0")
    Call<SelectModel> getSelectData();
}
