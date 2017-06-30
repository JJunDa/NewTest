package com.example.yls.newtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by yls on 2017/6/27.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        initView();
        initListener();
        initData();

    }
    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    private Toast mToast;

    public void showToast(String str){
        if(mToast == null){
            mToast = Toast.makeText(this,"",Toast.LENGTH_LONG);
        }
        mToast.setText(str);
        mToast.show();
    }

}
