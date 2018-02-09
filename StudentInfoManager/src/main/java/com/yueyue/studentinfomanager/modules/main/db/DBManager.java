package com.yueyue.studentinfomanager.modules.main.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yueyue.studentinfomanager.base.App;

/**
 * author : yueyue on 2018/2/8 13:59
 * desc   :
 */

public class DBManager {
    private static String TAG = DBManager.class.getSimpleName();

    private static DBManager sInstance;
    public static final String DB_NAME = "StudentInfo.db"; //数据库名字
    private SQLiteDatabase mDatabase;
    private Context context;


    private DBManager() {
    }

    public static DBManager getInstance() {
        return DBManagerHolder.sInstance;
    }

    private static final class DBManagerHolder {
        private static final DBManager sInstance = new DBManager();
    }

    public void openDatabase() {
        if (mDatabase == null) {
            this.mDatabase = new StudentSQLiteHelper(App.getAppContext(), DB_NAME, null, 1).getReadableDatabase();
        }
    }

    public SQLiteDatabase getDatabase() {
        if (mDatabase==null) openDatabase();
        return mDatabase;
    }


    public void closeDatabase() {
        if (this.mDatabase != null) {
            this.mDatabase.close();
            this.mDatabase = null;
        }
    }
}
