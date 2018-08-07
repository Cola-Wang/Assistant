package com.miki.assistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miki.assistant.R;
import com.miki.assistant.entity.Constant;
import com.miki.assistant.utils.OkHttpUtils;

import java.io.IOException;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     WallpaperFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:01
 * 描述:      壁纸
 */

public class WallpaperFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallpaper, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        initWallpaper();
    }

    //初始化壁纸
    private void initWallpaper() {
        OkHttpUtils.get(Constant.WALLPAPER_URL, new OkHttpUtils.onOkHttpCallback() {
            @Override
            public void onFailure(IOException e) {

            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onSuccessful(String json) {
                if (!TextUtils.isEmpty(json)) {
                    parsingWallpaperJson(json);
                }
            }
        });
    }

    //解析壁纸的json
    private void parsingWallpaperJson(String json) {

    }
}
