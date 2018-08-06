package com.miki.assistant.bean;

/**
 * 包名:      com.miki.assistant.bean
 * 文件名:     MovieListBean.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 11:39
 * 描述:      MovieListBean
 */

public class MovieListBean {

    private String subTitle;
    private String title;
    private String img_url;
    private String id;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
