package com.miki.assistant.utils;

import android.os.Build;

/**
 * 包名:      com.miki.assistant.utils
 * 文件名:     VersionUtils.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/2 11:31
 * 描述:      版本管理
 */

public class VersionUtils {
    //获取当前系统版本
    public static int getVersion() {
        return Build.VERSION.SDK_INT;
    }

    //是否大于5.0
    public static boolean isLollipop() {
        if (getVersion() >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        }
        return false;
    }

    //是否大于6.0
    public static boolean isM() {
        if (getVersion() >= Build.VERSION_CODES.M) {
            return true;
        }
        return false;
    }
}
