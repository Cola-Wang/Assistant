package com.miki.assistant.model;

import java.util.List;

/**
 * 包名:      com.miki.assistant.model
 * 文件名:     MusicListModel.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 21:08
 * 描述:      音乐列表
 */

public class MusicListModel {


    /**
     * res : 0
     * data : ["2730","2727","2728","2726","2725","2724","2723","2722","2721","2720"]
     */

    private int res;
    private List<String> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
