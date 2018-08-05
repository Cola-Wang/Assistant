package com.miki.assistant;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.miki.assistant.fragment.AppFragment;
import com.miki.assistant.fragment.HomeFragment;
import com.miki.assistant.fragment.SystemFragment;
import com.miki.assistant.fragment.TimeFragment;
import com.miki.assistant.fragment.WallpaperFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolBar;
    private NavigationView main_nav_view;
    private DrawerLayout main_drawer_layout;

    private HomeFragment homeFragment;
    private WallpaperFragment wallpaperFragment;
    private AppFragment appFragment;
    private SystemFragment systemFragment;
    private TimeFragment timeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initView();
        initHeaderView();
    }

    private void initHeaderView() {
        View view = main_nav_view.getHeaderView(0);
        TextView tv_header_title = (TextView) view.findViewById(R.id.tv_header_title);
        tv_header_title.setText("Cola");
        TextView tv_header_content = (TextView) view.findViewById(R.id.tv_header_content);
        tv_header_content.setText("未必会来，未必会走");
    }

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.mToolBar);
        setSupportActionBar(mToolBar);
    }

    private void initView() {

        main_drawer_layout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        main_nav_view = (NavigationView) findViewById(R.id.main_nav_view);
        //设置监听
        main_nav_view.setNavigationItemSelectedListener(this);
        //去掉渲染
        main_nav_view.setItemIconTintList(null);
        ActionBarDrawerToggle toggle
                = new ActionBarDrawerToggle(this,
                main_drawer_layout, mToolBar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        //切换菜单的动画
        main_drawer_layout.addDrawerListener(toggle);
        //同步状态
        toggle.syncState();

        //显示默认的fragment
        showHomeFragment();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_home:
                showHomeFragment();
                break;
            case R.id.nav_menu_wallpaper:
                showWallpaperFragment();
                break;
            case R.id.nav_menu_app:
                showAppFragment();
                break;
            case R.id.nav_menu_system:
                showSystemFragment();
                break;
            case R.id.nav_menu_time:
                showTimeFragment();
                break;
            case R.id.nav_menu_share:
                break;
            case R.id.nav_menu_setting:
                break;
        }
        //恢复
        main_drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_http:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showHomeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            ft.add(R.id.main_content_layout, homeFragment);
        }
        hideAllFragment(ft);
        ft.show(homeFragment);
        ft.commit();
    }

    private void showWallpaperFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (wallpaperFragment == null) {
            wallpaperFragment = new WallpaperFragment();
            ft.add(R.id.main_content_layout, wallpaperFragment);
        }
        hideAllFragment(ft);
        ft.show(wallpaperFragment);
        ft.commit();
    }

    private void showAppFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (appFragment == null) {
            appFragment = new AppFragment();
            ft.add(R.id.main_content_layout, appFragment);
        }
        hideAllFragment(ft);
        ft.show(appFragment);
        ft.commit();
    }

    private void showSystemFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (systemFragment == null) {
            systemFragment = new SystemFragment();
            ft.add(R.id.main_content_layout, systemFragment);
        }
        hideAllFragment(ft);
        ft.show(systemFragment);
        ft.commit();
    }

    private void showTimeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (timeFragment == null) {
            timeFragment = new TimeFragment();
            ft.add(R.id.main_content_layout, timeFragment);
        }
        hideAllFragment(ft);
        ft.show(timeFragment);
        ft.commit();
    }

    //隐藏全部的fragment
    private void hideAllFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (wallpaperFragment != null) {
            ft.hide(wallpaperFragment);
        }
        if (appFragment != null) {
            ft.hide(appFragment);
        }
        if (systemFragment != null) {
            ft.hide(systemFragment);
        }
        if (timeFragment != null) {
            ft.hide(timeFragment);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (homeFragment == null && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        } else if (wallpaperFragment == null && fragment instanceof WallpaperFragment) {
            wallpaperFragment = (WallpaperFragment) fragment;
        } else if (appFragment == null && fragment instanceof AppFragment) {
            appFragment = (AppFragment) fragment;
        } else if (systemFragment == null && fragment instanceof SystemFragment) {
            systemFragment = (SystemFragment) fragment;
        } else if (timeFragment == null && fragment instanceof TimeFragment) {
            timeFragment = (TimeFragment) fragment;
        }
    }
}
