package com.example.yls.newtest;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import fragment.MainFragment1;
import fragment.MainFragment2;
import fragment.MainFragment3;
import fragment.MainFragment4;
import fragment.MainFragment5;

import static com.example.yls.newtest.R.id.toolbar;

public class MainActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initViewPager();
        initNavigationView();
        initToolBar();
        initDrawerLayout();
    }
    // 使用toolBar代替ActionBar,并设置相应属性
    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(mToolbar);// 使用toolbar代替ActionBar
        mToolbar.setTitle("广交实训新闻");
        mToolbar.setTitleTextColor(Color.RED);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示左上角的返回按钮

        // 点击toolbar导航栏左上角的图标后，退出当前界面
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mDrawerLayout.openDrawer(mNavigationView);
            }
        });

    }

    //================Toolbar右上角弹出菜单(begin)=======================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_01){
            showToast(""+item.getTitle());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //================Toolbar右上角弹出菜单(end)=========================


    /*右上角图标改为三横，绑定ToolBar和DrawerLayout*/
    private ActionBarDrawerToggle toggle;
    private void initDrawerLayout() {
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);

        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();     // 同步drawerLayout和toolbar的状态
    }


    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showToast(""+item.getTitle());
                mDrawerLayout.closeDrawers();

                return false;
            }
        });
    }

    private void initViewPager() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment1());
        fragments.add(new MainFragment2());
        fragments.add(new MainFragment3());
        fragments.add(new MainFragment4());
        fragments.add(new MainFragment5());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

    }

    @Override
    protected void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_01:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:
                        mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mRadioGroup.check(R.id.rb_01);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_02);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.rb_03);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.rb_04);
                        break;
                    case 4:
                        mRadioGroup.check(R.id.rb_05);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
