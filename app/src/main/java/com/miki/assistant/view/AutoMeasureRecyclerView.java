package com.miki.assistant.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 包名:      com.miki.assistant.view
 * 文件名:     AutoMeasureRecyclerView.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/7 11:23
 * 描述:      自动测量
 */

public class AutoMeasureRecyclerView extends RecyclerView {

    public AutoMeasureRecyclerView(Context context) {
        super(context);
    }

    public AutoMeasureRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMeasureRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 测量模式  精确模式  最大模式  未指定模式
     *
     * @param widthSpec
     * @param heightSpec
     */
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
