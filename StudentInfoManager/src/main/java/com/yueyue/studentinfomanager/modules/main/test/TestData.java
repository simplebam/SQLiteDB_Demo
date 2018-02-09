package com.yueyue.studentinfomanager.modules.main.test;

import android.content.ContentValues;

import com.yueyue.studentinfomanager.modules.main.db.DBManager;
import com.yueyue.studentinfomanager.modules.main.db.StudentDB;

import java.util.ArrayList;

/**
 * author : yueyue on 2018/2/8 14:09
 * desc   :
 */

public class TestData {

    //预置学生信息
    public static void create() {
        ArrayList<String> numberList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        String gender = "男";
        String nativePlace = "湖北武汉";
        String specialty = "计算机";
        String phone = "15612345678";
        String birth = "1996年1月22日";

        numberList.add("206966880000");
        nameList.add("a小红红");
        numberList.add("207977880001");
        nameList.add("a小蓝色");
        numberList.add("207977880001");
        nameList.add("ab小黄色");
        numberList.add("207977880022");
        nameList.add("aab小红黄");
        numberList.add("307977880000");
        nameList.add("bc大绿红");
        numberList.add("207777770000");
        nameList.add("ac大绿蓝");
        numberList.add("204944880006");
        nameList.add("c大蓝黄");
        numberList.add("202977880006");
        nameList.add("abc大蓝紫");
        numberList.add("207977880001");
        nameList.add("ca大紫色");


        for (int i = 0; i < numberList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("number", numberList.get(i));
            values.put("name", nameList.get(i));
            values.put("gender", gender);
            values.put("native_place", nativePlace);
            values.put("specialty", specialty);
            values.put("phone", phone);
            values.put("birth", birth);

            StudentDB.insert(DBManager.getInstance().getDatabase(), null, values);
        }
    }
}
