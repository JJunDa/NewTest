package com.example.yls.newtest;

import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by yls on 2017/6/27.
 */

public class StartActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(1500);
                gotoGuideActivity();
            }
        }.start();
    }

    public void gotoMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void gotoGuideActivity(){
        Intent intent = new Intent(this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

}
