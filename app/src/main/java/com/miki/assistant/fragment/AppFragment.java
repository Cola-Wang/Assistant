package com.miki.assistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miki.assistant.R;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     AppFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:01
 * 描述:      应用
 */
public class AppFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, null);
        return view;
    }
}
