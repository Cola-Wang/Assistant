package com.miki.assistant.model;

/**
 * 包名:      com.miki.assistant.model
 * 文件名:     SelectModel.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 15:40
 * 描述:      精选的实体类
 */

public class SelectModel {

    //编号
    private String hp_title;
    //署名
    private String hp_author;
    //内容
    private String hp_content;
    //更新时间
    private String last_update_date;
    //封面地址
    private String hp_img_url;
    //链接地址
    private String web_url;

    public String getHp_title() {
        return hp_title;
    }

    public void setHp_title(String hp_title) {
        this.hp_title = hp_title;
    }

    public String getHp_author() {
        return hp_author;
    }

    public void setHp_author(String hp_author) {
        this.hp_author = hp_author;
    }

    public String getHp_content() {
        return hp_content;
    }

    public void setHp_content(String hp_content) {
        this.hp_content = hp_content;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public String getHp_img_url() {
        return hp_img_url;
    }

    public void setHp_img_url(String hp_img_url) {
        this.hp_img_url = hp_img_url;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }
}
