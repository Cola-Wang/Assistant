package com.miki.assistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miki.assistant.R;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     TimeFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:02
 * 描述:      时光
 */

public class TimeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, null);
        return view;
    }
}
