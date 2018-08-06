package com.miki.assistant.entity;

/**
 * 包名:      com.miki.assistant.entity
 * 文件名:     Constant.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/3 15:35
 * 描述:      常量
 */

public class Constant {
    //进入判断页
    public static final int HANDLER_TO_MAIN = 1000;
    //精选的网页
    public static final String HTTP_SELECT_URL = "http://v3.wufazhuce.com:8000/api/hp/more/0";
    //音乐列表
    public static final String HTTP_MUSIC_LIST = "http://v3.wufazhuce.com:8000/api/music/idlist/0";
    //歌曲详情
    public static final String HTTP_MUSIC_MORE = "http://v3.wufazhuce.com:8000/api/music/detail/";
    //
    public static final String HTTP_MOVIE_LIST = "http://v3.wufazhuce.com:8000/api/channel/movie/more/0?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
}
