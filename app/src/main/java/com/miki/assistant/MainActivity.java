package com.miki.assistant;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolBar;
    private NavigationView main_nav_view;
    private DrawerLayout main_drawer_layout;
    private ImageView iv_header_photo;

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
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_home:
                break;
            case R.id.nav_menu_wallpaper:
                break;
            case R.id.nav_menu_app:
                break;
            case R.id.nav_menu_system:
                break;
            case R.id.nav_menu_time:
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
}
