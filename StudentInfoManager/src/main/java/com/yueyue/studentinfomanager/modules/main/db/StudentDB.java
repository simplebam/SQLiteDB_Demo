package com.yueyue.studentinfomanager.modules.main.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yueyue.studentinfomanager.modules.main.domain.Person;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * author : yueyue on 2018/2/9 12:38
 * desc   : 封装数据库操作
 */

public class StudentDB {

    public static final String TAG = StudentDB.class.getSimpleName();


    public static long insert(SQLiteDatabase db, String nullColumnHack, ContentValues values) {
        return db.insert("student", nullColumnHack, values);
    }

    public static List<Person> query(SQLiteDatabase db, String[] columns,
                                     String selection, String[] selectionArgs, String groupBy,
                                     String having, String orderBy) {
        if (db == null) throw new NullPointerException("数据库db为null");
        Cursor cursor = db.query("student", columns, selection, selectionArgs, groupBy,
                having, orderBy);
        return infoToPerson(Integer.MAX_VALUE, cursor);
    }

    public static List<Person> query(SQLiteDatabase db) {
        if (db == null) throw new NullPointerException("数据库db为null");
        return query(db, null, null, null,
                null, null, null);
    }


    public static int update(SQLiteDatabase db, ContentValues values,
                             String whereClause, String[] whereArgs) {
        return db.update("student", values, whereClause, whereArgs);
    }

    public static int delete(SQLiteDatabase db, String whereClause, String[] whereArgs) {
        return db.delete("student", whereClause, whereArgs);
    }

    private static List<Person> infoToPerson(int maxSize, Cursor cursor) {
        List<Person> personList = new ArrayList<>();
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
                if (personList.size() >= maxSize) {
                    break;
                }
            }
            cursor.close();
        }
        closeQuietly(cursor);
        return personList;
    }


    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) closeable.close();
        } catch (IOException e) {
            Log.e(TAG, "closeQuietly: " + e.toString());
            e.printStackTrace();
        }
    }

}
