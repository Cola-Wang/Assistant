package com.miki.assistant.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * 包名:      com.miki.assistant.utils
 * 文件名:     ScreenUtils.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/2 12:26
 * 描述:      屏幕封装
 */

public class ScreenUtils {

    public static int width;
    public static int height;

    public static void init(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        if (VersionUtils.isJELLY_BEAN_MR1()) {
            display.getRealSize(point);
        } else {
            display.getSize(point);
        }
        width = point.x;
        height = point.y;
    }
}
