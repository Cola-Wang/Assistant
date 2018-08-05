package com.miki.assistant.utils;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 包名:      com.miki.assistant.utils
 * 文件名:     OkHttpUtils.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 12:32
 * 描述:      OkHttp封装
 * <p>
 * - get
 * - post json
 * - post map
 * - download
 */

public class OkHttpUtils {

    private static Handler mHandler = new Handler();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void get(String url, final onOkHttpCallback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccessful(json);
                        }
                    });
                } else {
                    callback.onFailure("response not isSuccessful");
                }
            }
        });
    }

    public static void postJson(String url, String json, final onOkHttpCallback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccessful(json);
                        }
                    });
                } else {
                    callback.onFailure("response not isSuccessful");
                }
            }
        });
    }

    public static void postMap(String url, Map<String, String> params, final onOkHttpCallback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccessful(json);
                        }
                    });
                } else {
                    callback.onFailure("response not isSuccessful");
                }
            }
        });
    }

    public static void download() {

    }


    public interface onOkHttpCallback {
        //失败
        void onFailure(IOException e);

        //请求成功，但是没有结果
        void onFailure(String msg);

        //成功
        void onSuccessful(String json);
    }
}
