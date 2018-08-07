package com.miki.assistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.miki.assistant.R;
import com.miki.assistant.adapter.LifeStyleAdapter;
import com.miki.assistant.bean.LifeStyleBean;
import com.miki.assistant.entity.Constant;
import com.miki.assistant.impl.WeatherApi;
import com.miki.assistant.model.LifeStyleModel;
import com.miki.assistant.model.NowWeatherModel;
import com.miki.assistant.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 包名:      com.miki.assistant.fragment
 * 文件名:     MeFragment.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 11:36
 * 描述:      我的
 */

public class MeFragment extends Fragment {

    private Retrofit retrofit;
    private TextView tv_city;
    private TextView tv_cond_txt_tmp;
    private TextView tv_wind_dir_sc;

    private RecyclerView mLifeStyleRecyclerView;
    private LifeStyleAdapter mLifeStyleAdapter;
    private List<LifeStyleBean> mList = new ArrayList<>();

    private ScrollView mScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);

        initView(view);
        return view;
    }

    private void initView(View view) {

        //init all view
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_cond_txt_tmp = (TextView) view.findViewById(R.id.tv_cond_txt_tmp);
        tv_wind_dir_sc = (TextView) view.findViewById(R.id.tv_wind_dir_sc);
        mLifeStyleRecyclerView = (RecyclerView) view.findViewById(R.id.mLifeStyleRecyclerView);
        mLifeStyleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLifeStyleAdapter = new LifeStyleAdapter(getActivity(), mList);
        mLifeStyleRecyclerView.setAdapter(mLifeStyleAdapter);

        mScrollView = view.findViewById(R.id.mScrollView);

        initWeatherApi();
    }

    private void initWeatherApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //获取实时天气
        WeatherApi api = retrofit.create(WeatherApi.class);
        api.getNowWeather(Constant.WEATHER_CITY, Constant.WEATHER_KEY)
                .enqueue(new Callback<NowWeatherModel>() {
                    @Override
                    public void onResponse(Call<NowWeatherModel> call, Response<NowWeatherModel> response) {
                        if (response.isSuccessful()) {
                            NowWeatherModel model = response.body();
                            parsingNowWeather(model);
                        }
                    }

                    @Override
                    public void onFailure(Call<NowWeatherModel> call, Throwable t) {

                    }
                });

        //获取生活指数
        api.getLifeStyle(Constant.WEATHER_CITY, Constant.WEATHER_KEY)
                .enqueue(new Callback<LifeStyleModel>() {
                    @Override
                    public void onResponse(Call<LifeStyleModel> call, Response<LifeStyleModel> response) {
                        if (response.isSuccessful()) {
                            LifeStyleModel model = response.body();
                            parsingLifeStyle(model);
                        }
                    }

                    @Override
                    public void onFailure(Call<LifeStyleModel> call, Throwable t) {

                    }
                });
    }

    //生活指数
    private void parsingLifeStyle(LifeStyleModel model) {
        List<LifeStyleModel.HeWeather6Bean.LifestyleBean> lifestyle = model.getHeWeather6().get(0).getLifestyle();
        for (int i = 0; i < lifestyle.size(); i++) {
            LifeStyleBean bean = new LifeStyleBean();

            LifeStyleModel.HeWeather6Bean.LifestyleBean lifestyleBean = lifestyle.get(i);
            bean.setType(lifestyleBean.getType());
            bean.setBrf(lifestyleBean.getBrf());
            bean.setTxt(lifestyleBean.getTxt());

            mList.add(bean);
        }
        mLifeStyleAdapter.notifyDataSetChanged();
    }

    //今天的天气
    private void parsingNowWeather(NowWeatherModel model) {
        NowWeatherModel.HeWeather6Bean bean = model.getHeWeather6().get(0);
        LogUtils.d(bean.getBasic().getCnty() + "");
        tv_city.setText(bean.getBasic().getLocation());
        tv_cond_txt_tmp.setText(bean.getNow().getCond_txt() + " | " + bean.getNow().getTmp() + "°C");
        tv_wind_dir_sc.setText(bean.getNow().getWind_dir() + " | " + bean.getNow().getWind_spd() + "级");
    }

    //回到最上面
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (mScrollView != null) {
                mScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
            }
        }
        super.onHiddenChanged(hidden);
    }
}
