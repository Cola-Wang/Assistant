package com.miki.assistant.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miki.assistant.R;
import com.miki.assistant.adapter.AppManagerAdapter;
import com.miki.assistant.manager.AppManager;
import com.miki.assistant.model.AppModel;
import com.miki.assistant.ui.AppInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     AppFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:01
 * 描述:      应用
 */
public class AppFragment extends Fragment implements TabLayout.OnTabSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AppManagerAdapter mAppManagerAdapter;

    private static final int REFRESH_TIME = 3000;
    private static final int H_REFRESH_WHAT = 0X22;
    private static final int H_GET_APP_LIST_WHAT = 0X33;
    //给适配器使用
    private List<AppModel> mList = new ArrayList<>();
    //所有的应用信息
    private List<AppModel> mAllList = new ArrayList<>();
    //当前列表  0:已安装  1:系统应用  2:全部
    private int currentTab = 0;

    private String[] mStrTitle = {"已安装", "系统应用", "全部"};

    private AppReceiver mAppReceiver;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case H_REFRESH_WHAT:
                    //刷新列表
                    parsingApp(currentTab);
                    break;
                case H_GET_APP_LIST_WHAT:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mAllList = AppManager.getInstance(getActivity()).getAllApp();
                            mHandler.sendEmptyMessage(H_REFRESH_WHAT);
                        }
                    }).start();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.mTabLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mAppManagerAdapter = new AppManagerAdapter(getActivity(), mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(mAppManagerAdapter);

        mAppManagerAdapter.setOnItemClickListener(new AppManagerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                AppModel appModel = mList.get(position);
                Intent intent = new Intent(getActivity(), AppInfoActivity.class);
                intent.putExtra("appName", appModel.getAppName());
                intent.putExtra("packageName", appModel.getPackageName());
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout = view.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        for (int i = 0; i < mStrTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mStrTitle[i]));
        }

        //设置TabLayout的选中监听
        mTabLayout.addOnTabSelectedListener(this);
        //请求下拉刷新
        mSwipeRefreshLayout.setRefreshing(true);
        //请求刷新列表
        mHandler.sendEmptyMessage(H_GET_APP_LIST_WHAT);

        initReceiver();
    }

    private void initReceiver() {
        mAppReceiver = new AppReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addDataScheme("package");
        getActivity().registerReceiver(mAppReceiver, filter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Toast.makeText(getActivity(), mStrTitle[tab.getPosition()], Toast.LENGTH_SHORT).show();
        parsingApp(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    //刷新的回调
    @Override
    public void onRefresh() {
        //捕捉到用户下拉的动作
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(H_REFRESH_WHAT);
            }
        }, REFRESH_TIME);
    }

    //解析数据
    private void parsingApp(int currentTab) {
        this.currentTab = currentTab;
        if (mList != null) {
            if (mList.size() > 0) {
                mList.clear();
            }
        }
        for (int i = 0; i < mAllList.size(); i++) {
            AppModel appModel = mAllList.get(i);
            switch (currentTab) {
                case 0:
                    if (!appModel.isSystem()) {
                        mList.add(appModel);
                    }
                    break;
                case 1:
                    if (appModel.isSystem()) {
                        mList.add(appModel);
                    }
                    break;
                case 2:
                    mList.addAll(mAllList);
                    break;
            }
        }
        //刷新适配器
        mAppManagerAdapter.notifyDataSetChanged();
        //停止下拉动画
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    class AppReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
                    mHandler.sendEmptyMessage(H_GET_APP_LIST_WHAT);
                } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
                    String packageName = intent.getData().getSchemeSpecificPart();
                    if (!TextUtils.isEmpty(packageName)) {
                        //判断当前的数据中是否存在这条数据
                        for (int i = 0; i < mAllList.size(); i++) {
                            AppModel model = mAllList.get(i);
                            if (model.getPackageName().equals(packageName)) {
                                mAllList.remove(i);
                                parsingApp(currentTab);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mAppReceiver);
    }
}
