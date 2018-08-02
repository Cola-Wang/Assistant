package com.miki.assistant.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.miki.assistant.utils.VersionUtils;

/**
 * 包名:      com.miki.assistant.base
 * 文件名:     BaseActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/2 12:48
 * 描述:      BaseActivity
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //去掉阴影
        if (VersionUtils.isLollipop()) {
            getSupportActionBar().setElevation(0);
        }
    }

    //菜单栏操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
