package com.miki.projecttest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.miki.projecttest.R;
import com.miki.projecttest.impl.SelectService;
import com.miki.projecttest.model.SelectModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 包名:      com.miki.projecttest.ui
 * 文件名:     RetrofitActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/6 15:39
 * 描述:      Retrofit
 */

public class RetrofitActivity extends AppCompatActivity {
    private TextView tv;

    //精选接口 http://v3.wufazhuce.com:8000/api/hp/more/0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SelectService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SelectService service = retrofit.create(SelectService.class);

//        Call<ResponseBody> call = service.getSelectData();
//        //同步
//        //call.execute();
//        //异步
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        String json = response.body().string();
//                        tv.setText(json);
//                    } catch (IOException e) {
//                        tv.setText(e.toString());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                tv.setText(t.toString());
//            }
//        });

        Call<SelectModel> call = service.getSelectData();
        call.enqueue(new Callback<SelectModel>() {
            @Override
            public void onResponse(Call<SelectModel> call, Response<SelectModel> response) {
                if (response.isSuccessful()) {
                    SelectModel model = response.body();
                    for (int i = 0; i < model.getData().size(); i++) {
                        tv.append(model.getData().get(i).getHp_author() + "\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<SelectModel> call, Throwable t) {

            }
        });
    }
}
