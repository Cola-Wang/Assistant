package com.miki.projecttest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miki.projecttest.R;

/**
 * 包名:      com.miki.projecttest.fragment
 * 文件名:     ExampleFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/4 12:18
 * 描述:      扩展碎片
 */

public class Example2Fragment extends Fragment {

    private static final String TAG = "ExampleFragment";

    /**
     * 添加Fragment到Activity有两种方式
     * 1.XML
     * 2.代码插入
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        return view;
    }
}
