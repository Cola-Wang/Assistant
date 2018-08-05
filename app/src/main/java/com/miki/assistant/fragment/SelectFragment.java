package com.miki.assistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miki.assistant.R;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     SelectFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:34
 * 描述:      精选
 */

public class SelectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, null);
        return view;
    }
}
