package com.yueyue.studentinfomanager.modules.main.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author : yueyue on 2018/2/8 13:57
 * desc   :
 */

public class StudentSQLiteHelper extends SQLiteOpenHelper {

    public static final String CreateStudentInfo = "create table student ("
            + "number integer primary key, "
            + "gender text , "
            + "name text,"
            + "birth text,"
            + "native_place text,"
            + "specialty text,"
            + "phone text)";

    public StudentSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateStudentInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
