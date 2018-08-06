package com.miki.assistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.miki.assistant.R;
import com.miki.assistant.adapter.MovieListAdapter;
import com.miki.assistant.bean.MovieListBean;
import com.miki.assistant.entity.Constant;
import com.miki.assistant.model.MovieListModel;
import com.miki.assistant.ui.MovieMoreActivity;
import com.miki.assistant.utils.LogUtils;
import com.miki.assistant.utils.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     MovieFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:35
 * 描述:      电影
 */

public class MovieFragment extends Fragment {

    private RecyclerView mMovieRecyclerView;
    private MovieListAdapter mMovieListAdapter;
    private List<MovieListBean> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mMovieRecyclerView = (RecyclerView) view.findViewById(R.id.mMovieRecyclerView);
        mMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMovieListAdapter = new MovieListAdapter(getActivity(), mList);

        mMovieRecyclerView.setAdapter(mMovieListAdapter);
        mMovieListAdapter.setOnItemClickListener(new MovieListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                MovieListBean bean = mList.get(position);
                Intent i = new Intent(getActivity(), MovieMoreActivity.class);
                i.putExtra("title", bean.getTitle());
                i.putExtra("id", bean.getId());
                startActivity(i);
            }
        });
        initMovieData();
    }

    //请求电影列表数据
    private void initMovieData() {
        OkHttpUtils.get(Constant.HTTP_MOVIE_LIST, new OkHttpUtils.onOkHttpCallback() {
            @Override
            public void onFailure(IOException e) {
                LogUtils.e(e.toString());
            }

            @Override
            public void onFailure(String msg) {
                LogUtils.e(msg);
            }

            @Override
            public void onSuccessful(String json) {
                if (!TextUtils.isEmpty(json)) {
                    LogUtils.d("电影数据：" + json);
                    loadMovieData(json);
                } else {
                    LogUtils.d("数据为空");
                }
            }
        });
    }

    private void loadMovieData(String json) {
        MovieListModel model = new Gson().fromJson(json, MovieListModel.class);
        for (int i = 0; i < model.getData().size(); i++) {
            MovieListBean bean = new MovieListBean();
            MovieListModel.DataBean dataBean = model.getData().get(i);
            bean.setTitle(dataBean.getTitle());
            bean.setSubTitle(dataBean.getSubtitle());
            bean.setId(dataBean.getItem_id());
            bean.setImg_url(dataBean.getImg_url());
            mList.add(bean);
        }
        mMovieListAdapter.notifyDataSetChanged();
    }
}
