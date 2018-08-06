package com.miki.assistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.miki.assistant.R;
import com.miki.assistant.adapter.MusicListAdapter;
import com.miki.assistant.bean.MusicListBean;
import com.miki.assistant.entity.Constant;
import com.miki.assistant.model.MusicListModel;
import com.miki.assistant.model.MusicMoreModel;
import com.miki.assistant.ui.MusicMoreActivity;
import com.miki.assistant.utils.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     MusicFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:35
 * 描述:      音乐
 */
public class MusicFragment extends Fragment {

    private RecyclerView mMusicRecyclerView;
    private MusicListAdapter mMusicListAdapter;
    private List<MusicListBean> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mMusicRecyclerView = (RecyclerView) view.findViewById(R.id.mMusicRecyclerView);
        mMusicRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mMusicListAdapter = new MusicListAdapter(getActivity(), mList);
        mMusicRecyclerView.setAdapter(mMusicListAdapter);

        mMusicListAdapter.setOnItemClickListener(new MusicListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                MusicListBean bean = mList.get(position);
                Intent i = new Intent(getActivity(), MusicMoreActivity.class);
                i.putExtra("title", bean.getTitle());
                i.putExtra("id", bean.getId());
                startActivity(i);
            }
        });

        initMusicList();
    }

    private void initMusicList() {
        OkHttpUtils.get(Constant.HTTP_MUSIC_LIST, new OkHttpUtils.onOkHttpCallback() {
            @Override
            public void onFailure(IOException e) {

            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onSuccessful(String json) {
                if (!TextUtils.isEmpty(json)) {
                    parsingMusicListJson(json);
                }
            }
        });
    }

    //解析音乐列表
    private void parsingMusicListJson(String json) {
        MusicListModel musicListModel = new Gson().fromJson(json, MusicListModel.class);
        for (int i = 0; i < musicListModel.getData().size(); i++) {
            String musicId = musicListModel.getData().get(i);
            OkHttpUtils.get(Constant.HTTP_MUSIC_MORE + musicId, new OkHttpUtils.onOkHttpCallback() {
                @Override
                public void onFailure(IOException e) {

                }

                @Override
                public void onFailure(String msg) {

                }

                @Override
                public void onSuccessful(String json) {
                    if (!TextUtils.isEmpty(json)) {
                        parsingMusicMoreJson(json);
                    }
                }
            });
        }
    }

    //解析音乐详情
    private void parsingMusicMoreJson(String json) {
        MusicMoreModel musicMoreModel = new Gson().fromJson(json, MusicMoreModel.class);
        String cover = musicMoreModel.getData().getCover();
        String id = musicMoreModel.getData().getId();
        String title = musicMoreModel.getData().getTitle();

        MusicListBean bean = new MusicListBean();
        bean.setUrl(cover);
        bean.setId(id);
        bean.setTitle(title);

        mList.add(bean);

        //刷新一下
        mMusicListAdapter.notifyDataSetChanged();
    }
}
