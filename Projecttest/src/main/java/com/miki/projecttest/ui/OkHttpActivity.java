package com.miki.projecttest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.miki.projecttest.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 包名:      com.miki.projecttest.ui
 * 文件名:     OkHttpActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 12:14
 * 描述:      OkHttp
 */

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "OkHttpActivity";
    public static final String HTTP_SELECT_URL = "http://v3.wufazhuce.com:8000/api/hp/more/0";

    private Button btn_get;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
    }

    private void initView() {
        btn_get = (Button) findViewById(R.id.btn_get);
        tv_result = (TextView) findViewById(R.id.tv_result);

        btn_get.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                get();
                break;
        }
    }

    private void get() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(HTTP_SELECT_URL).build();
        Call call = okHttpClient.newCall(request);
        //同步请求
        //Response res = call.execute();
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Log.d(TAG, "onResponse: " + json);
                }
            }
        });

    }
}
