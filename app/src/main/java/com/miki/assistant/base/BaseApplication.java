package com.miki.assistant.base;

import android.app.Application;

import com.miki.assistant.utils.ProcessUtils;
import com.miki.assistant.utils.ScreenUtils;

/**
 * 包名:      com.miki.assistant.base
 * 文件名:     BaseApplication.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/2 12:35
 * 描述:      基类
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //主进程初始化
        if (getApplicationInfo().packageName.equals(ProcessUtils.getTopProcess())) {
            init();
        }
    }

    private void init() {
        ScreenUtils.init(this);
    }
}
