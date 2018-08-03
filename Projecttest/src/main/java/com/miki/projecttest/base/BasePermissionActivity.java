package com.miki.projecttest.base;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.miki.projecttest.listener.OnRequestPermissionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.projecttest.base
 * 文件名:     BasePermissionActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/3 14:30
 * 描述:      权限封装
 */

public class BasePermissionActivity extends AppCompatActivity {

    private static final String TAG = "wangzh";

    /**
     * 1.请求权限
     * checkPermissions: 权限组 回调Code  知道结果(回调)
     * 2.处理权限
     */

    private int permissionsRequestCode;
    private List<String> mListPermissions = new ArrayList<>();
    private OnRequestPermissionListener listener;

    protected void checkPermissions(String[] permissions, int requestCode, OnRequestPermissionListener listener) {
        //判断不能为空
        if (permissions != null && permissions.length != 0) {
            permissionsRequestCode = requestCode;
            this.listener = listener;
            //先去遍历权限 相机，电话，SD卡，  List装载未申请的权限
            for (int i = 0; i < permissions.length; i++) {
                //判断未申请，就装起来
                if (!checkSelfPermissions(permissions[i])) {
                    mListPermissions.add(permissions[i]);
                }
            }
            Log.d(TAG, "checkPermissions: 遍历完成");
            //遍历完之后申请
            applyPermissions();
        } else {
            Log.d(TAG, "checkPermissions: 权限数组为空");
            listener.onNullPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == permissionsRequestCode) {
            Log.d(TAG, "onRequestPermissionsResult: 接收到回调");
            if (grantResults.length > 0) {
                listener.onSuccessful(grantResults);
            } else {
                listener.onFailure();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //检查权限
    private boolean checkSelfPermissions(String permissions) {
        if (ActivityCompat.checkSelfPermission(this, permissions) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    //申请权限
    private void applyPermissions() {
        //判断权限集合是否为空
        if (!mListPermissions.isEmpty()) {
            //申请权限
            int size = mListPermissions.size();
            ActivityCompat.requestPermissions(this, mListPermissions.toArray(new String[size]), permissionsRequestCode);
            Log.d(TAG, "applyPermissions: 请求权限");
            mListPermissions.clear();
        }
    }
}
