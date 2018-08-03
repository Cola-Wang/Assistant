package com.miki.projecttest.listener;

/**
 * 包名:      com.miki.projecttest.listener
 * 文件名:     OnRequestPermissionListener.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/3 14:34
 * 描述:      权限的回调
 */

public interface OnRequestPermissionListener {

    //成功
    void onSuccessful(int[] grantResults);
    //失败
    void onFailure();
    //权限为空
    void onNullPermission();
}
