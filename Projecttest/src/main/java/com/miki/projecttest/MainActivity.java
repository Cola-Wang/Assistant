package com.miki.projecttest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 工程测试用例
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = "wangzh";

    private String[] mStr = {"请求权限","RxPermissions"};
    private ArrayAdapter<String> mArrayAdapter;
    private ListView mListView;

    /**
     * 1. 动态权限
     * <p>
     * 确定权限
     * 判断权限
     * 申请权限
     * 处理权限
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStr);
        mListView.setAdapter(mArrayAdapter);

        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                requestPermissions();
                break;
            case 1:
                RxPermiss();
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
    private void RxPermiss(){
        RxPermissions rxPermissions = new RxPermissions(this);
        //请求权限
        rxPermissions.request(Manifest.permission.CALL_PHONE
                ,Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        Log.d(TAG,"结果：" + value);
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
    private void requestPermissions(){
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i("test", "permissions:" + permissions.length + "grantResults:" + grantResults.length);

        if (requestCode == 100) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone("10086");
                } else {
                    Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
