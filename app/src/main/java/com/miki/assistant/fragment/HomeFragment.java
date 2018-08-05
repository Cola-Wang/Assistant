package com.miki.assistant.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.miki.assistant.R;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     HomeFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:00
 * 描述:      主页
 */

public class HomeFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;

    private SelectFragment selectFragment;
    private MusicFragment musicFragment;
    private MovieFragment movieFragment;
    private MeFragment meFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBottomNavigationView = (BottomNavigationView) view.findViewById(R.id.mBottomNavigationView);
        //设置监听
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        //去掉渲染
        mBottomNavigationView.setItemIconTintList(null);
        //显示默认的fragment
        showSelectFragment();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_select:
                showSelectFragment();
                break;
            case R.id.menu_music:
                showMusicFragment();
                break;
            case R.id.menu_movie:
                showMovieFragment();
                break;
            case R.id.menu_me:
                showMeFragment();
                break;
        }
        return true;
    }

    private void showSelectFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (selectFragment == null) {
            selectFragment = new SelectFragment();
            ft.add(R.id.home_content_layout, selectFragment);
        }
        hideAllFragment(ft);
        ft.show(selectFragment);
        ft.commit();
    }

    private void showMusicFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (musicFragment == null) {
            musicFragment = new MusicFragment();
            ft.add(R.id.home_content_layout, musicFragment);
        }
        hideAllFragment(ft);
        ft.show(musicFragment);
        ft.commit();
    }

    private void showMovieFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (movieFragment == null) {
            movieFragment = new MovieFragment();
            ft.add(R.id.home_content_layout, movieFragment);
        }
        hideAllFragment(ft);
        ft.show(selectFragment);
        ft.commit();
    }

    private void showMeFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (meFragment == null) {
            meFragment = new MeFragment();
            ft.add(R.id.home_content_layout, meFragment);
        }
        hideAllFragment(ft);
        ft.show(meFragment);
        ft.commit();
    }

    //隐藏全部的fragment
    private void hideAllFragment(FragmentTransaction ft) {
        if (selectFragment != null) {
            ft.hide(selectFragment);
        }
        if (musicFragment != null) {
            ft.hide(musicFragment);
        }
        if (movieFragment != null) {
            ft.hide(movieFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
    }
}
