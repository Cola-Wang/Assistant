package com.miki.assistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miki.assistant.R;
import com.miki.assistant.base.BasePagerAdapter;
import com.miki.assistant.entity.Constant;
import com.miki.assistant.model.SelectModel;
import com.miki.assistant.utils.LogUtils;
import com.miki.assistant.utils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     SelectFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:34
 * 描述:      精选
 */

public class SelectFragment extends Fragment {

    private ViewPager mSelectViewPager;
    private List<SelectModel> mListModel = new ArrayList<>();
    private List<View> mListPagerView = new ArrayList<>();
    private TextView tv_now_count;
    private TextView tv_all_count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSelectViewPager = (ViewPager) view.findViewById(R.id.mSelectViewPager);

        tv_now_count = (TextView) view.findViewById(R.id.tv_now_count);
        tv_all_count = (TextView) view.findViewById(R.id.tv_all_count);

        initSelectData();
        initListener();
    }

    //初始化监听
    private void initListener() {
        mSelectViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCount(position + 1, mListPagerView.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化精选的数据
    private void initSelectData() {
        OkHttpUtils.get(Constant.HTTP_SELECT_URL, new OkHttpUtils.onOkHttpCallback() {
            @Override
            public void onFailure(IOException e) {
                LogUtils.e("error:" + e.toString());
            }

            @Override
            public void onFailure(String msg) {
                LogUtils.e(msg);
            }

            @Override
            public void onSuccessful(String json) {
                if (!TextUtils.isEmpty(json)) {
                    initViewPager(json);
                }
            }
        });
    }

    /**
     * 使用的解析方法  1 R+G 2 O+G 3 O+J
     *
     * @param json
     */
    //初始化分页
    private void initViewPager(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = (JSONObject) jsonArray.get(i);
                String hp_title = jsonData.getString("hp_title");
                String hp_author = jsonData.getString("hp_author");
                String hp_content = jsonData.getString("hp_content");
                String last_update_date = jsonData.getString("last_update_date");
                String hp_img_url = jsonData.getString("hp_img_url");
                String web_url = jsonData.getString("web_url");

                SelectModel model = new SelectModel();
                model.setHp_title(hp_title);
                model.setHp_author(hp_author);
                model.setHp_content(hp_content);
                model.setLast_update_date(last_update_date);
                model.setHp_img_url(hp_img_url);
                model.setWeb_url(web_url);

                mListModel.add(model);
            }

            //创建分页
            for (int i = 0; i < mListModel.size(); i++) {
                View view = View.inflate(getActivity(), R.layout.layout_select_pager_item, null);
                //init
                ImageView iv_img = (ImageView) view.findViewById(R.id.iv_img);
                TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                TextView tv_author = (TextView) view.findViewById(R.id.tv_author);
                TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
                TextView tv_last_update_date = (TextView) view.findViewById(R.id.tv_last_update_date);

                SelectModel model = mListModel.get(i);
                tv_title.setText(model.getHp_title());
                tv_author.setText(model.getHp_author());
                tv_content.setText(model.getHp_content());
                tv_last_update_date.setText(model.getLast_update_date());

                mListPagerView.add(view);

            }

            //预加载
            mSelectViewPager.setOffscreenPageLimit(mListModel.size());
            mSelectViewPager.setAdapter(new BasePagerAdapter(mListPagerView));

            setCount(1, mListPagerView.size());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //监听下标
    private void setCount(int now, int all) {
        tv_now_count.setText("" + now);
        tv_all_count.setText("" + all);
    }
}
