package com.yueyue.studentinfomanager.modules.login.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.base.BaseActivity;
import com.yueyue.studentinfomanager.common.Config;
import com.yueyue.studentinfomanager.common.utils.KeyboardUtil;
import com.yueyue.studentinfomanager.common.utils.ToastUtil;
import com.yueyue.studentinfomanager.modules.login.utils.ValidateUtil;
import com.yueyue.studentinfomanager.modules.main.ui.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * quote : 使用TextInputLayout创建一个登陆界面 - 泡在网上的日子
 * http://www.jcodecraeer.com/a/basictutorial/2015/0821/3338.html
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.usernameWrapper)
    TextInputLayout mUsernameWrapper;

    @BindView(R.id.passwordWrapper)
    TextInputLayout mPasswordWrapper;

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @BindView(R.id.btn_register)
    Button mBtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    private void initView() {
        //已经在xml文件设置了
//        mUsernameWrapper.setHint("Username");
//        mPasswordWrapper.setHint("Password");

    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                doRegister();
                break;

            case R.id.btn_login:
                KeyboardUtil.hideKeyboard(LoginActivity.this);
                doCheck();
                break;

            default:
                break;
        }
    }

    private void doRegister() {
        ToastUtil.showShort("暂时没有开通!!!");

        final TableLayout tlPassword = (TableLayout) getLayoutInflater().inflate(R.layout.dialog_register_user, null);
        final EditText etUsername = (EditText) tlPassword.findViewById(R.id.et_username);
        final EditText etPassword = (EditText) tlPassword.findViewById(R.id.et_password);

        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("修改登录密码")
                .setView(tlPassword)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        registerUser(username, password);
                    }
                })
                //由于“取消”的button我们没有设置点击效果，直接设为null就可以了
                .setNegativeButton("取消", null)
                .show();
    }


    private void registerUser(String username, String password) {
        if (ValidateUtil.userName(username)) {
            ToastUtil.showShort("账号只能为数字");
            return;
        }

        if (!ValidateUtil.password(password)) {
            ToastUtil.showShort("密码必须要6位以上");
            return;
        }

        Config.cacheUserName(LoginActivity.this, username);
        Config.cachePassword(LoginActivity.this, password);
    }


    private void doCheck() {
        String userName = mUsernameWrapper.getEditText().getText().toString();
        String password = mPasswordWrapper.getEditText().getText().toString();
        if (!ValidateUtil.userName(userName)) {
            mUsernameWrapper.setError("账号必须为数字!");
        } else if (!ValidateUtil.password(password)) {
            mPasswordWrapper.setError("密码长度必须要6位以上");
        } else {
            mUsernameWrapper.setErrorEnabled(false);
            mPasswordWrapper.setErrorEnabled(false);
            doLogin(userName, password);
        }
    }

    private void doLogin(String userName, String password) {
        //从本地获取到密码，如果没有设置过密码
        String oldUserName = Config.getCacheUserName(this);
        String oldPassword = Config.getCachePassword(this);

        if (oldUserName.equals(userName) && oldPassword.equals(password)) {
            MainActivity.launch(this);
            finish();
            ToastUtil.showShort("登录成功！");
        } else {
            ToastUtil.showShort("账号或者密码不正确,请检查清楚");
        }

    }


}
