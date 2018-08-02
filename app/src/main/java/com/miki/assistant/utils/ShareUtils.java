package com.miki.assistant.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 包名:      com.miki.assistant.utils
 * 文件名:     ShareUtils.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/2 11:43
 * 描述:      封装SharedPreferences
 */

public class ShareUtils {

    private static final String SP_NAME = "config";

    //存储一个String类型的值
    public static void putString(Context context, String key, String values) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, values);
        editor.apply();
    }

    //获取一个String类型的值
    public static String getString(Context context, String key, String defValues) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValues);
    }

    //存储一个int类型的值
    public static void putInt(Context context, String key, int values) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, values);
        editor.apply();
    }

    //获取一个int类型的值
    public static int getInt(Context context, String key, int defValues) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValues);
    }

    //存储一个boolean类型的值
    public static void putBoolean(Context context, String key, boolean values) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, values);
        editor.apply();
    }

    //获取一个boolean类型的值
    public static boolean getBoolean(Context context, String key, boolean defValues) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValues);
    }

    //删除单个
    public static void deleKey(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    //删除全部
    public static void deleAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

}
