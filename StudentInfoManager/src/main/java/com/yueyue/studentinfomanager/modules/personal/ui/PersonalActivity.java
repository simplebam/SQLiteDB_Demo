package com.yueyue.studentinfomanager.modules.personal.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.modules.main.ui.MainActivity;

import butterknife.BindView;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.btn_my_info_back)
    Button btnBack;


    public static void launch(Context context) {
        context.startActivity(new Intent(context, PersonalActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
