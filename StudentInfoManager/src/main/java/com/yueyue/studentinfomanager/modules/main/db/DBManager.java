package com.yueyue.studentinfomanager.modules.main.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


    private DBManager(String dbName) {
        mDatabase = new StudentDB(App.getAppContext(), dbName, null, 1).getReadableDatabase();
    }

    public static DBManager getInstance() {
        if (sInstance == null) {
            synchronized (DBManager.class) {
                if (sInstance == null) {
                    sInstance = new DBManager(DB_NAME);
                }
            }
        }
        return sInstance;
    }


    public long insert(String table, String nullColumnHack, ContentValues values) {
        return mDatabase.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs,
                        String groupBy, String having, String orderBy) {
        return mDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public Cursor query(String table) {
        return query(table, null, null,
                null, null, null, null);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return mDatabase.update(table, values, whereClause, whereArgs);
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        return mDatabase.delete(table, whereClause, whereArgs);
    }

    public void close() {
        if (mDatabase != null) mDatabase.close();
    }
}
