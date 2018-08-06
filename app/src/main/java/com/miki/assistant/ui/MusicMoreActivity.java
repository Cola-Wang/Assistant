package com.miki.assistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.miki.assistant.R;
import com.miki.assistant.base.BaseActivity;
import com.miki.assistant.entity.Constant;
import com.miki.assistant.model.MusicMoreModel;
import com.miki.assistant.utils.GlideUtils;
import com.miki.assistant.utils.OkHttpUtils;

import java.io.IOException;

/**
 * 包名:      com.miki.assistant.ui
 * 文件名:     MusicMoreActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 10:46
 * 描述:      音乐详情
 */

public class MusicMoreActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_cover;
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_lyric;
    private TextView tv_info;
    private TextView tv_charge_edit;

    private String title;
    private String id;
    private MusicMoreModel.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_more);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");

        iv_cover = (ImageView) findViewById(R.id.iv_cover);
        iv_cover.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_lyric = (TextView) findViewById(R.id.tv_lyric);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_charge_edit = (TextView) findViewById(R.id.tv_charge_edit);

        if (!TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        }

        if (!TextUtils.isEmpty(id)) {
            initMusicMore();
        }
    }

    //初始化音乐详情接口
    private void initMusicMore() {
        OkHttpUtils.get(Constant.HTTP_MUSIC_MORE + id, new OkHttpUtils.onOkHttpCallback() {
            @Override
            public void onFailure(IOException e) {

            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onSuccessful(String json) {
                if (!TextUtils.isEmpty(json)) {
                    parsingMusicMoreJson(json);
                }
            }
        });
    }

    //解析音乐详情的数据
    private void parsingMusicMoreJson(String json) {
        MusicMoreModel model = new Gson().fromJson(json, MusicMoreModel.class);
        bean = model.getData();

        GlideUtils.loadImageViewCenterCrop(this, bean.getCover(), iv_cover);
        tv_title.setText(bean.getStory_title());
        tv_content.setText(Html.fromHtml(bean.getStory()));
        tv_lyric.setText(bean.getLyric());
        tv_info.setText(bean.getInfo());
        tv_charge_edit.setText(bean.getCharge_edt());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cover:
                if (bean != null) {
                    Intent i = new Intent(this, WebViewActivity.class);
                    i.putExtra("title", bean.getStory_title());
                    i.putExtra("url", bean.getWeb_url());
                    startActivity(i);
                }
                break;
        }
    }
}
