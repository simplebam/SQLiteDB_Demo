package com.yueyue.studentinfomanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * author : yueyue on 2018/2/8 13:49
 * desc   :
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        ButterKnife.bind(this);
    }

    protected abstract int initLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
