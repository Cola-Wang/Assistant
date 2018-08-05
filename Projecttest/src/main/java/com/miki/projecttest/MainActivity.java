package com.miki.projecttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miki.projecttest.base.BasePermissionActivity;
import com.miki.projecttest.ui.FragmentTestActivity;
import com.miki.projecttest.ui.OkHttpActivity;
import com.miki.projecttest.ui.PermissionActivity;

/**
 * 工程测试用例
 */

public class MainActivity extends BasePermissionActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = "wangzh";

    private String[] mStr = {"运行时权限", "Fragment", "OkHttp"};
    private ArrayAdapter<String> mArrayAdapter;
    private ListView mListView;

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
                startActivity(new Intent(this, PermissionActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, FragmentTestActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
        }
    }
}
