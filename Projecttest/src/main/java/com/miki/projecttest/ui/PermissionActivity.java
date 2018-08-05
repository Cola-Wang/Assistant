package com.miki.projecttest.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.miki.projecttest.R;
import com.miki.projecttest.base.BasePermissionActivity;
import com.miki.projecttest.listener.OnRequestPermissionListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 包名:      com.miki.projecttest.ui
 * 文件名:     PermissionActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/4 12:07
 * 描述:      运行时权限
 */

public class PermissionActivity extends BasePermissionActivity implements View.OnClickListener {

    private static final String TAG = "wangzh";

    private Button button1;
    private Button button2;
    private Button button3;

    /**
     * 1. 动态权限
     * <p>
     * 确定权限
     * 判断权限
     * 申请权限
     * 处理权限
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_activity);
        initView();
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                requestPermissions();
                break;
            case R.id.button2:
                RxPermiss();
                break;
            case R.id.button3:
                checkPermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, 200, new OnRequestPermissionListener() {
                    @Override
                    public void onSuccessful(int[] grantResults) {
                        for (int i = 0; i < grantResults.length; i++) {
                            Log.d(TAG, "onSuccessful: " + grantResults[i]);
                        }
                    }

                    @Override
                    public void onFailure() {

                    }

                    @Override
                    public void onNullPermission() {

                    }
                });
                break;
        }
    }

    //打电话
    private void callPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    //RxPermissions
    private void RxPermiss() {
        RxPermissions rxPermissions = new RxPermissions(this);
        //请求权限
        rxPermissions.request(Manifest.permission.CALL_PHONE
                , Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        Log.d(TAG, "结果：" + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //普通的请求权限
    private void requestPermissions() {
        //判断电话权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            //判断用户是否拒绝过该权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                //说明已经拒绝过，需要解释
                Toast.makeText(this, "已经拒绝过权限", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
            } else {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
            }
        } else {
            callPhone("10086");
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        Log.i("test", "permissions:" + permissions.length + "grantResults:" + grantResults.length);
//
//        if (requestCode == 100) {
//            if (grantResults.length > 0) {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    callPhone("10086");
//                } else {
//                    Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
}
