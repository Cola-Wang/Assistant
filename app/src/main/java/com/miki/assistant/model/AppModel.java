package com.miki.assistant.model;

import android.graphics.drawable.Drawable;

/**
 * 包名:      com.miki.assistant.model
 * 文件名:     AppModel.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/7 15:20
 * 描述:      App的信息
 */

public class AppModel {

    private Drawable icon;

    private String appName;

    private String appSize;
    //是否系统应用
    private boolean isSystem = false;
    //包名
    private String packageName;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
