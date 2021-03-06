package com.yueyue.studentinfomanager.modules.search.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.base.BaseActivity;
import com.yueyue.studentinfomanager.modules.main.db.DBManager;
import com.yueyue.studentinfomanager.modules.main.db.StudentDB;
import com.yueyue.studentinfomanager.modules.main.domain.Person;
import com.yueyue.studentinfomanager.modules.search.adapter.SearchAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    //控件
    @BindView(R.id.btn_search_front)
    Button btnFront;

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.rv_search)
    RecyclerView rvSearch;

    //变量与常量
    public static final int TYPE_SEARCH_NUMBER = 11;
    public static final int TYPE_SEARCH_NAME = 22;
    private int currentSearchType;


    public static void launch(Context context, int searchType) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("search_type", searchType);
        context.startActivity(intent);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        receiveSearchType();
        DBManager.getInstance().openDatabase();//关闭数据库
    }


    private void receiveSearchType() {
        Intent intent = this.getIntent();
        currentSearchType = intent.getIntExtra("search_type", TYPE_SEARCH_NUMBER);
    }


    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //将状态栏颜色设置为与toolbar一致
            getWindow().setStatusBarColor(getResources().getColor(R.color.normal_blue));
        }

        String hintStr = currentSearchType == TYPE_SEARCH_NUMBER ?
                "请输入你要搜索的学生学号" : "请输入你要搜索的学生姓名";
        etSearch.setHint(hintStr);


        rvSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        etSearch.addTextChangedListener(myTextWatcher);
    }

    private void refreshRecyclerView(String s) {

        String selectionStr = currentSearchType == TYPE_SEARCH_NUMBER ? "number" : "name";

        List<Person> personList = StudentDB.query(DBManager.getInstance().getDatabase(), null,
                selectionStr + " like ?", new String[]{"%" + s + "%"},
                null, null, null);


        rvSearch.setAdapter(new SearchAdapter(this, personList));


    }

    @OnClick(R.id.btn_search_front)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_front:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (myTextWatcher != null) etSearch.removeTextChangedListener(myTextWatcher);
        DBManager.getInstance().closeDatabase();//关闭数据库
        super.onDestroy();
    }

    TextWatcher myTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            refreshRecyclerView(s + "");
        }
    };

}
