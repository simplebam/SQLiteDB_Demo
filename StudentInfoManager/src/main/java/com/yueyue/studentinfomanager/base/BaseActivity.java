package com.yueyue.studentinfomanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * author : yueyue on 2018/2/8 13:49
 * desc   :
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: "+this.getClass().getSimpleName());
        setContentView(initLayoutId());
        ButterKnife.bind(this);
    }

    protected abstract int initLayoutId();


    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: "+this.getClass().getSimpleName());
        super.onDestroy();
    }


}
