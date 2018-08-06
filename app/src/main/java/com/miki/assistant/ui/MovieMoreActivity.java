package com.miki.assistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miki.assistant.R;
import com.miki.assistant.base.BaseActivity;
import com.miki.assistant.model.MovieMoreModel;
import com.miki.assistant.utils.GlideUtils;
import com.miki.assistant.utils.LogUtils;
import com.miki.assistant.utils.OkHttpUtils;
import com.miki.assistant.view.AutoViewPager;

import java.io.IOException;

/**
 * 包名:      com.miki.assistant.ui
 * 文件名:     MovieMoreActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 12:05
 * 描述:      电影详情
 */

public class MovieMoreActivity extends BaseActivity {

    private String title;
    private String id;
    private ImageView iv_detail_cover;
    private TextView tv_official_story;
    private TextView tv_info;

    private AutoViewPager mAutoViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_more);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        id = intent.getStringExtra("id");

        if (!TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        }

        iv_detail_cover = (ImageView) findViewById(R.id.iv_detail_cover);
        tv_official_story = (TextView) findViewById(R.id.tv_official_story);
        tv_info = (TextView) findViewById(R.id.tv_info);

        mAutoViewPager = (AutoViewPager) findViewById(R.id.mAutoViewPager);
        mAutoViewPager.setAutoTime(2000);
        mAutoViewPager.setStartAuto(true);

        initData(id);
    }

    //加载数据
    private void initData(String id) {
        String url = "http://v3.wufazhuce.com:8000/api/movie/detail/"
                + id + "?channel=wdj&source=channel_movie&source_id=9240&version=4.0." +
                "2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
        OkHttpUtils.get(url, new OkHttpUtils.onOkHttpCallback() {
            @Override
            public void onFailure(IOException e) {
                LogUtils.e(e.toString());
            }

            @Override
            public void onFailure(String msg) {
                LogUtils.e(msg);
            }

            @Override
            public void onSuccessful(String json) {
                if (!TextUtils.isEmpty(json)) {
                    LogUtils.d(json);
                    loadMovieMoreData(json);
                }
            }
        });
    }

    //解析数据
    private void loadMovieMoreData(String json) {
        MovieMoreModel model = new Gson().fromJson(json, MovieMoreModel.class);

        GlideUtils.loadImageViewCenterCrop(this, model.getData().getDetailcover(), iv_detail_cover);
        tv_official_story.setText(model.getData().getOfficialstory());
        tv_info.setText(model.getData().getInfo());

        for (int i = 0; i < model.getData().getPhoto().size(); i++) {
            View view = View.inflate(this, R.layout.layout_auto_view_pager_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv_bg);
            GlideUtils.loadImageViewCenterCrop(this, model.getData().getPhoto().get(i), iv);

            mAutoViewPager.setView(view);
        }
    }
}
