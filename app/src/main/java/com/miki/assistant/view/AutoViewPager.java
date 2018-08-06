package com.miki.assistant.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.miki.assistant.base.BasePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.assistant.view
 * 文件名:     AutoViewPager.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 14:59
 * 描述:      轮播
 */

public class AutoViewPager extends ViewPager {

    private static final int AUTO_WANT = 0X11;

    //数据源
    private List<View> mList = new ArrayList<>();
    //轮播开关
    private boolean isStartAuto = false;
    //轮播间隔
    private int autoTime = 3000;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTO_WANT:
                    if (isStartAuto) {
                        int index = getCurrentItem();
                        //如果当前item超过了最大的页数
                        if (index >= mList.size() - 1) {
                            setCurrentItem(0);
                        } else {
                            setCurrentItem(index + 1);
                        }
                        mHandler.sendEmptyMessageDelayed(AUTO_WANT, autoTime);
                    }
                    break;
            }
        }
    };

    public AutoViewPager(Context context) {
        super(context);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isStartAuto() {
        return isStartAuto;
    }

    public void setStartAuto(boolean startAuto) {
        isStartAuto = startAuto;
        if (startAuto) {
            mHandler.sendEmptyMessage(AUTO_WANT);
        } else {
            mHandler.removeMessages(AUTO_WANT);
        }
    }

    public int getAutoTime() {
        return autoTime;
    }

    public void setAutoTime(int autoTime) {
        this.autoTime = autoTime;
    }

    //设置view
    public void setView(View view) {
        mList.add(view);
        setAdapter(new BasePagerAdapter(mList));
    }

}
