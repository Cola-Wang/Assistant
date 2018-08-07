package com.miki.assistant.impl;

import com.miki.assistant.model.LifeStyleModel;
import com.miki.assistant.model.NowWeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 包名:      com.miki.assistant.impl
 * 文件名:     WeatherApi.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 20:18
 * 描述:      天气接口
 */

public interface WeatherApi {

    //https://free-api.heweather.com/s6/weather/now?location=成都&key=0ffd9e20ded64e8ba01e3cb2b8a994d3

    //实况天气
    @GET("s6/weather/now?")
    Call<NowWeatherModel> getNowWeather(@Query("location") String location, @Query("key") String key);

    //https://free-api.heweather.com/s6/weather/lifestyle?location=成都&key=0ffd9e20ded64e8ba01e3cb2b8a994d3
    //生活指数
    @GET("s6/weather/lifestyle?")
    Call<LifeStyleModel> getLifeStyle(@Query("location") String location, @Query("key") String key);
}
