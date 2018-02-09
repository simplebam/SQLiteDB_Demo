package com.yueyue.studentinfomanager.modules.personal.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalActivity extends BaseActivity {

    @BindView(R.id.btn_my_info_back)
    Button btnBack;


    public static void launch(Context context) {
        context.startActivity(new Intent(context, PersonalActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_personal;
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @OnClick(R.id.btn_my_info_back)
    public void onClick(View view) {
        finish();
    }
}
