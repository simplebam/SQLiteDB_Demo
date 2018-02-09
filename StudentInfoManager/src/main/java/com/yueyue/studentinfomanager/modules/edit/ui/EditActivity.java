package com.yueyue.studentinfomanager.modules.edit.ui;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.yueyue.studentinfomanager.R;
import com.yueyue.studentinfomanager.base.BaseActivity;
import com.yueyue.studentinfomanager.common.utils.ToastUtil;
import com.yueyue.studentinfomanager.modules.main.db.DBManager;
import com.yueyue.studentinfomanager.modules.main.db.StudentDB;
import com.yueyue.studentinfomanager.modules.main.domain.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EditActivity extends BaseActivity {
    //控件
    @BindView(R.id.btn_edit_back)
    Button btnBack;
    @BindView(R.id.btn_edit_sure)
    Button btnSure;
    @BindView(R.id.et_edit_number)
    EditText etNumber;
    @BindView(R.id.et_edit_name)
    EditText etName;
    @BindView(R.id.et_edit_native_place)
    EditText etNativePlace;
    @BindView(R.id.et_edit_specialty)
    EditText etSpecialty;
    @BindView(R.id.et_edit_phone)
    EditText etPhone;
    @BindView(R.id.tv_edit_gender)
    TextView tvGender;
    @BindView(R.id.tr_edit_gender)
    TableRow trGender;
    @BindView(R.id.tv_edit_birth)
    TextView tvBirth;
    @BindView(R.id.tr_edit_birth)
    TableRow trBirth;
    @BindView(R.id.tv_edit_delete)
    TextView tvDelete;


    //变量与常量
    public static final int TYPE_ADD = 111;
    public static final int TYPE_EDIT = 222;
    private int currentType;//判断当前是需要增加还是修改
    private Person mPerson;

    public static void launch(Context context, int searchType, Person person) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("search_type", searchType);
        intent.putExtra("data", person);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiveType();
        initView();
        receiveInfo();
        DBManager.getInstance().openDatabase();//开启数据库
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_edit;
    }


    //获取当前的编辑类型，是增添或者编辑
    private void receiveType() {
        Intent intent = this.getIntent();
        currentType = intent.getIntExtra("type", TYPE_ADD);
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        tvDelete.setVisibility(currentType == TYPE_ADD ? View.GONE : View.VISIBLE);
        etNumber.setEnabled(currentType == TYPE_ADD);//编辑情况之下不可以修改number
    }

    private void receiveInfo() {

        //从数据库获取学生信息显示到界面上
        if (currentType == TYPE_EDIT) {
            Intent intent = this.getIntent();
            mPerson = intent.getParcelableExtra("data");

            if (mPerson != null) {
                etNumber.setText(mPerson.number);
                etName.setText(mPerson.name);
                tvGender.setText(mPerson.gender);
                tvBirth.setText(mPerson.birth);
                etNativePlace.setText(mPerson.nativePlace);
                etSpecialty.setText(mPerson.specialty);
                etPhone.setText(mPerson.phone);
            } else {
                mPerson = new Person();
            }

        }
    }

    @OnClick({R.id.btn_edit_back, R.id.btn_edit_sure, R.id.tr_edit_gender,
            R.id.tr_edit_birth, R.id.tv_edit_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_back:
                finish();
                break;
            case R.id.btn_edit_sure:
                btnSureAction();
                break;
            case R.id.tr_edit_gender:
                trGenderAction();
                break;
            case R.id.tr_edit_birth:
                trBirthAction();
                break;
            case R.id.tv_edit_delete:
                tvDeleteAction();
                break;
        }
    }

    private void btnSureAction() {
        String number = etNumber.getText().toString();

        if (currentType == TYPE_ADD && sameNumber(number)) {
            ToastUtil.showShort("添加失败,该学号的学生已经存在！");
            return;
        }

        if (currentType == TYPE_EDIT && !sameNumber(number)) {
            ToastUtil.showShort("修改失败,该学号的学生不存在！");
            return;
        }


        String name = etName.getText().toString();
        String gender = tvGender.getText().toString();
        String nativePlace = etNativePlace.getText().toString();
        String specialty = etSpecialty.getText().toString();
        String phone = etPhone.getText().toString();
        String birth = tvBirth.getText().toString();

        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("name", name);
        values.put("gender", gender);
        values.put("native_place", nativePlace);
        values.put("specialty", specialty);
        values.put("phone", phone);
        values.put("birth", birth);

        switch (currentType) {
            case TYPE_ADD:
                StudentDB.insert(DBManager.getInstance().getDatabase(), null, values);
                ToastUtil.showShort("添加数据成功");
                break;
            case TYPE_EDIT:
                StudentDB.update(DBManager.getInstance().getDatabase(),
                        values, "number=?", new String[]{number});
                ToastUtil.showShort("数据修改成功");
                break;
        }

        finish();
    }

    private void trGenderAction() {
        final String[] arrayGender = new String[]{"男", "女"};
        new AlertDialog.Builder(EditActivity.this)
                .setTitle("修改出生日期")
                .setItems(arrayGender, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvGender.setText(arrayGender[which]);
                    }
                })
                .show();
    }

    private void trBirthAction() {
        final DatePicker dpBirth = (DatePicker) getLayoutInflater()
                .inflate(R.layout.dialog_edit_birth, null);

        new AlertDialog.Builder(EditActivity.this)
                .setTitle("修改出生日期")
                .setView(dpBirth)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //将Activity中的textview显示AlertDialog中EditText中的内容
                        tvBirth.setText(dpBirth.getYear() + "年" + dpBirth.getMonth() + "月" + dpBirth.getDayOfMonth() + "日");
                    }
                })
                //由于“取消”的button我们没有设置点击效果，直接设为null就可以了
                .setNegativeButton("取消", null)
                .show();
    }

    private void tvDeleteAction() {
        final String number = etNumber.getText().toString().trim();
        new AlertDialog.Builder(EditActivity.this)
                .setTitle("修改出生日期")
                .setMessage("确认删除此学生信息？\n学号：" + number)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StudentDB.delete(DBManager.getInstance().getDatabase(),
                                "number=?", new String[]{number});
                        ToastUtil.showShort("该学生信息删除成功！");
                        finish();
                    }
                })
                //由于“取消”的button我们没有设置点击效果，直接设为null就可以了
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        DBManager.getInstance().closeDatabase();
        super.onDestroy();
    }

    //如果任何一个EditText中为空，就返回flase
    private boolean notNull(String number, String name, String gender, String nativePlace,
                            String specialty, String phone, String birth) {
        return !TextUtils.isEmpty(number) && !TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(gender) && !TextUtils.isEmpty(nativePlace) &&
                !TextUtils.isEmpty(specialty) && !TextUtils.isEmpty(phone) &&
                !TextUtils.isEmpty(birth);
    }

    private boolean sameNumber(String number) {
        List<Person> personList = StudentDB.query(DBManager.getInstance().getDatabase(),
                null, "number=?", new String[]{number}, null,
                null, null);
        return personList.size() > 0;
    }
}
