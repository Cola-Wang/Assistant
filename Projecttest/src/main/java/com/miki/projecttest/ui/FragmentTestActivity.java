package com.miki.projecttest.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.miki.projecttest.R;
import com.miki.projecttest.fragment.FourFragment;
import com.miki.projecttest.fragment.OneFragment;
import com.miki.projecttest.fragment.ThreeFragment;
import com.miki.projecttest.fragment.TwoFragment;

/**
 * 包名:      com.miki.projecttest.ui
 * 文件名:     FragmentTestActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/4 12:15
 * 描述:      FragmentTest
 */

public class FragmentTestActivity extends AppCompatActivity implements View.OnClickListener {

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FrameLayout fragment_contain;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private FragmentTransaction fragmentTransaction1;
    private FragmentTransaction fragmentTransaction2;
    private FragmentTransaction fragmentTransaction3;
    private FragmentTransaction fragmentTransaction4;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initView();
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_contain, fragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        fragment_contain = (FrameLayout) findViewById(R.id.fragment_contain);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);

        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction3 = fragmentManager.beginTransaction();
        fragmentTransaction4 = fragmentManager.beginTransaction();
        //add
        fragmentTransaction1.add(R.id.fragment_contain, oneFragment);
        fragmentTransaction2.add(R.id.fragment_contain, twoFragment);
        fragmentTransaction3.add(R.id.fragment_contain, threeFragment);
        fragmentTransaction4.add(R.id.fragment_contain, fourFragment);
        fragmentTransaction1.commit();
        fragmentTransaction2.commit();
        fragmentTransaction3.commit();
        fragmentTransaction4.commit();
        //showFragment(oneFragment);
        showHideFragment(oneFragment);
    }

    private void showHideFragment(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideAllFragment(ft);
        ft.show(fragment);
        ft.commit();
    }

    //隐藏全部的fragment
    private void hideAllFragment(FragmentTransaction ft) {
        if (oneFragment != null) {
            ft.hide(oneFragment);
        }
        if (twoFragment != null) {
            ft.hide(twoFragment);
        }
        if (threeFragment != null) {
            ft.hide(threeFragment);
        }
        if (fourFragment != null) {
            ft.hide(fourFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                showHideFragment(oneFragment);
                break;
            case R.id.btnTwo:
                showHideFragment(twoFragment);
                break;
            case R.id.btnThree:
                showHideFragment(threeFragment);
                break;
            case R.id.btnFour:
                showHideFragment(fourFragment);
                break;
        }
    }
}
