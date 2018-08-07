package com.miki.assistant.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miki.assistant.R;
import com.miki.assistant.base.BaseActivity;
import com.miki.assistant.manager.AppManager;

/**
 * 包名:      com.miki.assistant.ui
 * 文件名:     AppInfoActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/7 19:25
 * 描述:      应用详情
 */

public class AppInfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_app_icon;
    private TextView tv_app_name;
    private TextView tv_app_package_name;
    private TextView tv_app_uninstall;
    private ListView mListView;

    private ArrayAdapter<String> mAdapter;

    private String appName;
    private String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        initView();
    }

    private void initView() {

        iv_app_icon = (ImageView) findViewById(R.id.iv_app_icon);
        tv_app_name = (TextView) findViewById(R.id.tv_app_name);
        tv_app_package_name = (TextView) findViewById(R.id.tv_app_package_name);
        tv_app_uninstall = (TextView) findViewById(R.id.tv_app_uninstall);
        tv_app_uninstall.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.mListView);


        Intent intent = getIntent();
        appName = intent.getStringExtra("appName");
        packageName = intent.getStringExtra("packageName");

        if (!TextUtils.isEmpty(appName)) {
            getSupportActionBar().setTitle(appName);
            tv_app_name.setText(appName);
        } else {
            tv_app_name.setText("获取失败");
        }
        if (!TextUtils.isEmpty(packageName)) {
            tv_app_package_name.setText(packageName);
        } else {
            tv_app_package_name.setText("获取失败");
        }
        Drawable appIcon = AppManager.getInstance(this).getAppIcon(packageName);
        iv_app_icon.setBackground(appIcon);

        String[] appPermissions = AppManager.getInstance(this).getAppPermissions(packageName);
        if (appPermissions != null) {
            mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appPermissions);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_app_uninstall:
                AppManager.getInstance(this).uninstallApp(packageName);
                finish();
                break;
        }
    }
}
