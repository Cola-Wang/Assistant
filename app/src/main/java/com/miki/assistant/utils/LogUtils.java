package com.miki.assistant.utils;

import android.util.Log;

/**
 * 包名:      com.miki.assistant.utils
 * 文件名:     LogUtils.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/2 11:13
 * 描述:      log封装
 */

public class LogUtils {
    //开关
    private static final boolean DEBUG = true;
    //标记
    private static final String TAG = "wangzh";

    //D I W E F
    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void d(String tag, String text) {
        if (DEBUG) {
            Log.d(tag, text);
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void i(String tag, String text) {
        if (DEBUG) {
            Log.i(tag, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void w(String tag, String text) {
        if (DEBUG) {
            Log.w(tag, text);
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }

    public static void e(String tag, String text) {
        if (DEBUG) {
            Log.e(tag, text);
        }
    }
}
