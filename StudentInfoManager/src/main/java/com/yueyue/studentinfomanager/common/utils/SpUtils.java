package com.yueyue.studentinfomanager.common.utils;

import android.content.SharedPreferences;

import com.yueyue.studentinfomanager.base.App;

import static android.content.Context.MODE_PRIVATE;

/**
 * author : yueyue on 2018/2/8 13:16
 * desc   :
 */

public class SpUtils {

    private static SharedPreferences mPrefs = App.getAppContext()
            .getSharedPreferences("config", MODE_PRIVATE);


    public static void putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public static void putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {

        return mPrefs.getBoolean(key, defValue);
    }


    public static void clear() {
        mPrefs.edit().clear().apply();
    }


}
