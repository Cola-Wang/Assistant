package com.miki.assistant.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.miki.assistant.MainActivity;
import com.miki.assistant.R;
import com.miki.assistant.base.BasePermissionActivity;
import com.miki.assistant.entity.Constant;
import com.miki.assistant.listener.OnRequestPermissionListener;
import com.miki.assistant.utils.ShareUtils;
import com.miki.assistant.utils.VersionUtils;

/**
 * 包名:      com.miki.assistant.ui
 * 文件名:     IndexActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/3 15:24
 * 描述:      闪屏页
 */

public class IndexActivity extends BasePermissionActivity {

    private String[] strPermissions = {Manifest.permission.CALL_PHONE};

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.HANDLER_TO_MAIN:
                    //判断进入哪个页面
                    if (isFirstRunApp()) {
                        startActivity(new Intent(IndexActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(IndexActivity.this, MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        initView();
    }

    private void initView() {
        //申请权限
        if (VersionUtils.isM()) {
            checkPermissions(strPermissions, 100, new OnRequestPermissionListener() {
                @Override
                public void onSuccessful(int[] grantResults) {
                    //只要有结果就可以
                    mHandler.sendEmptyMessageDelayed(Constant.HANDLER_TO_MAIN, 2000);
                }

                @Override
                public void onFailure() {

                }

                @Override
                public void onNullPermission() {
                    mHandler.sendEmptyMessageDelayed(Constant.HANDLER_TO_MAIN, 2000);
                }
            });
        } else {
            mHandler.sendEmptyMessageDelayed(Constant.HANDLER_TO_MAIN, 2000);
        }
    }

    //判断是否第一次运行
    private boolean isFirstRunApp() {
        boolean isFirst = ShareUtils.getBoolean(this, "isFirst", true);
        if (isFirst) {
            ShareUtils.putBoolean(this, "isFirst", false);
            return true;
        } else {
            return false;
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
