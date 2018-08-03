package com.miki.assistant.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 包名:      com.miki.assistant.base
 * 文件名:     BasePagerAdapter.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/3 16:55
 * 描述:      Viewpager通用适配器
 */

public class BasePagerAdapter extends PagerAdapter {

    private List<View> mList;

    public BasePagerAdapter(List<View> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }
}
