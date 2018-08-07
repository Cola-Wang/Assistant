package com.miki.assistant.manager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.miki.assistant.model.AppModel;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.assistant.manager
 * 文件名:     AppManager.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/7 15:18
 * 描述:      包管理
 */

public class AppManager {

    private static AppManager mInstance = null;

    private Context context;
    private PackageManager pm;
    private List<PackageInfo> mListAllApp;

    private AppManager(Context context) {
        this.context = context;

        initAppManager();

    }

    private void initAppManager() {
        pm = context.getPackageManager();
        mListAllApp = pm.getInstalledPackages(0);
    }

    public static AppManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager(context);
                }
            }
        }
        return mInstance;
    }

    //获取所有应用
    public List<AppModel> getAllApp() {
        List<AppModel> mList = new ArrayList<>();
        //遍历
        for (PackageInfo info : mListAllApp) {
            AppModel model = new AppModel();
            ApplicationInfo applicationInfo = info.applicationInfo;
            model.setIcon(applicationInfo.loadIcon(pm));
            model.setAppName(pm.getApplicationLabel(applicationInfo).toString());
            model.setPackageName(applicationInfo.packageName);
            //应用大小
            File file = new File(applicationInfo.sourceDir);
            model.setAppSize(formatData(file));
            //是否是系统应用
            int flag = applicationInfo.flags;
            if ((flag & ApplicationInfo.FLAG_SYSTEM) != 0) {
                model.setSystem(true);
            }
            mList.add(model);
        }
        return mList;
    }

    /**
     * 计算传入的file大小
     *
     * @param file
     * @return
     */
    private static String formatData(File file) {
        float fileSize = file.length() / 1024 / 1024;
        DecimalFormat df = new DecimalFormat("0.00");
        if (fileSize > 1024) {
            float sizes = fileSize / 1024;
            return df.format(sizes) + "GB";
        } else {
            return df.format(fileSize) + "MB";
        }
    }

    //获取指定包名的版本号
    public String getAppVersion(String packageName) {
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return "版本号：" + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "版本号：获取失败";
        }
    }

    //获取指定包名的icon
    public Drawable getAppIcon(String packageName) {
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            Drawable drawable = info.applicationInfo.loadIcon(pm);
            return drawable;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取指定包名的权限
    public String[] getAppPermissions(String packageName) {
        try {
            PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            String[] permissions = info.requestedPermissions;
            return permissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //卸载指定包名的应用
    public void uninstallApp(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri uri = Uri.parse("package:" + packageName);
        intent.setData(uri);
        context.startActivity(intent);
    }

}
