package com.miki.assistant.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 包名:      com.miki.assistant.utils
 * 文件名:     ProcessUtils.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/2 12:37
 * 描述:      进程封装
 */

public class ProcessUtils {

    //获取主进程
    public static String getTopProcess() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String processName = bf.readLine().trim();
            bf.close();
            return processName;
        } catch (Exception e) {
            return null;
        }
    }
}
