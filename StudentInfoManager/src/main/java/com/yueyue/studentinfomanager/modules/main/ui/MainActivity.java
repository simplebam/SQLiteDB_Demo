package com.yueyue.studentinfomanager.modules.main.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.base.BaseActivity;
import com.yueyue.studentinfomanager.common.C;
import com.yueyue.studentinfomanager.common.Config;
import com.yueyue.studentinfomanager.common.utils.DoubleClickExit;
import com.yueyue.studentinfomanager.common.utils.SpUtils;
import com.yueyue.studentinfomanager.common.utils.ToastUtil;
import com.yueyue.studentinfomanager.modules.edit.ui.EditActivity;
import com.yueyue.studentinfomanager.modules.login.utils.ValidateUtil;
import com.yueyue.studentinfomanager.modules.main.adapter.MainAdapter;
import com.yueyue.studentinfomanager.modules.main.db.DBManager;
import com.yueyue.studentinfomanager.modules.main.domain.Person;
import com.yueyue.studentinfomanager.modules.main.test.TestData;
import com.yueyue.studentinfomanager.modules.personal.ui.PersonalActivity;
import com.yueyue.studentinfomanager.modules.search.ui.SearchActivity;

import java.util.ArrayList;

public class MainActivity
        extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //控件
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RecyclerView mRvMain;
    private FloatingActionButton mFabAdd;
    private Toolbar mToolbar;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();

    }


    @Override
    protected void onStart() {
        super.onStart();
        refreshRecyclerView();
    }


    private void initData() {
        Boolean isFirstStart = SpUtils.getBoolean(C.IS_FIRST_START, true);
        if (isFirstStart) {
            TestData.create();
            SpUtils.putBoolean(C.IS_FIRST_START, false);
        }

    }


    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //将状态栏颜色设置为与toolbar一致
            getWindow().setStatusBarColor(getResources().getColor(R.color.normal_blue));
        }

        setToolBar();
        setNavigationView();

        mRvMain = (RecyclerView) findViewById(R.id.rv_main);
        mRvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        mFabAdd = (FloatingActionButton) findViewById(R.id.fabtn_main_add);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.launch(MainActivity.this, EditActivity.TYPE_ADD, null);
            }
        });

    }

    private void setToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mToolbar.setTitle("学生信息");
        if (getSupportActionBar() != null) setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
    }

    private void setNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_main_menu);

        mNavigationView.setNavigationItemSelectedListener(this);
        //对应xml文件中属性  app:headerLayout="@layout/header_navigation_view"
        mNavigationView.inflateHeaderView(R.layout.header_navigation_view);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        mDrawerLayout,
                        mToolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_my_info:
                PersonalActivity.launch(MainActivity.this);
                break;
            case R.id.nav_password:
                changePasswordDialog();
                break;
            case R.id.nav_search:
                searchAction();
                break;
            case R.id.nav_add:
                EditActivity.launch(MainActivity.this, EditActivity.TYPE_ADD, null);
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    private void changePasswordDialog() {
        final TableLayout tlPassword = (TableLayout) getLayoutInflater().inflate(R.layout.dialog_main_password, null);
        final EditText etOldPassword = (EditText) tlPassword.findViewById(R.id.et_main_old_password);
        final EditText etNewPassword = (EditText) tlPassword.findViewById(R.id.et_main_new_password);

        final String oldPassword = etOldPassword.getText().toString().trim();
        final String newPassword = etNewPassword.getText().toString().trim();

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("修改登录密码")
                .setView(tlPassword)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updatePassword(oldPassword, newPassword);
                    }
                })
                //由于“取消”的button我们没有设置点击效果，直接设为null就可以了
                .setNegativeButton("取消", null)
                .show();
    }

    private void updatePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(Config.getCachePassword(MainActivity.this))) {
            ToastUtil.showShort("原密码错误！");
            return;
        }

        if (ValidateUtil.password(newPassword)) {
            Config.cachePassword(MainActivity.this, newPassword);
            ToastUtil.showShort("修改密码成功");
        } else {
            ToastUtil.showShort("新密码必要要6位以上");
        }
    }


    private void searchAction() {
        final String[] arrayGender = new String[]{"学号", "姓名"};
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("搜索类型")
                .setItems(arrayGender, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int searchType = which == 0 ?
                                SearchActivity.TYPE_SEARCH_NUMBER : SearchActivity.TYPE_SEARCH_NAME;
                        SearchActivity.launch(MainActivity.this, searchType);
                    }
                })
                .show();
    }

    private void refreshRecyclerView() {
        ArrayList<Person> personList = new ArrayList<>();
        Cursor cursor = DBManager.getInstance().query("student");
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Person person = new Person();
                person.name = cursor.getString(cursor.getColumnIndex("name"));
                person.number = cursor.getString(cursor.getColumnIndex("number"));
                person.gender = cursor.getString(cursor.getColumnIndex("gender"));
                person.birth = cursor.getString(cursor.getColumnIndex("birth"));
                person.nativePlace = cursor.getString(cursor.getColumnIndex("native_place"));
                person.specialty = cursor.getString(cursor.getColumnIndex("specialty"));
                person.phone = cursor.getString(cursor.getColumnIndex("phone"));
                personList.add(person);

                //最多查询50条
                if (personList.size() >= 50) {
                    break;
                }
            }
            cursor.close();
        }

        mRvMain.setAdapter(new MainAdapter(this, personList));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (item.getItemId() == R.id.tb_search) {
            searchAction();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (!DoubleClickExit.check()) {
            ToastUtil.showShort(getString(R.string.double_exit));
        } else {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        DBManager.getInstance().close();//关闭数据库
        super.onDestroy();
    }


}
