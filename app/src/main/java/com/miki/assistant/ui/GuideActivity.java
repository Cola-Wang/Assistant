package com.miki.assistant.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.miki.assistant.MainActivity;
import com.miki.assistant.R;
import com.miki.assistant.base.BasePagerAdapter;
import com.miki.assistant.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.assistant.ui
 * 文件名:     GuideActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/3 15:44
 * 描述:      引导页
 */

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private ViewPager mViewpager;

    /**
     * ViewPager滑动
     * 在ViewPager尾页 右滑监听 MainActivity
     */

    private View viewOne, viewTwo, viewThree;
    private List<View> mList = new ArrayList<>();

    //当前Item
    private int currentItem;

    private int startX, endX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initData();
        initView();
    }

    private void initData() {
        //加载三个View
        viewOne = View.inflate(this, R.layout.layout_guide_view_one_item, null);
        viewTwo = View.inflate(this, R.layout.layout_guide_view_two_item, null);
        viewThree = View.inflate(this, R.layout.layout_guide_view_three_item, null);
        mList.add(viewOne);
        mList.add(viewTwo);
        mList.add(viewThree);
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.mViewpager);

        //设置适配器
        mViewpager.setAdapter(new BasePagerAdapter(mList));
        //监听滑动事件
        mViewpager.addOnPageChangeListener(this);
        //监听触摸事件
        mViewpager.setOnTouchListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //状态改变
        currentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 触摸事件
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        /**
         * 1.尾页
         * 2.右滑
         *
         * 按下，抬起，移动
         */
        switch (event.getAction()) {
            //按下
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                break;
            //抬起
            case MotionEvent.ACTION_UP:
                endX = (int) event.getX();

                //判断是否是最后一页
                if (currentItem == (mList.size() - 1)) {
                    //说明有滑动距离
                    if (startX - endX > 0) {
                        //是左滑
                        if (startX - endX >= ScreenUtils.width / 4) {
                            startActivity(new Intent(this, MainActivity.class));
                            finish();
                        }
                    }
                }
                break;
        }
        return false;
    }
}
