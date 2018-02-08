package com.yueyue.studentinfomanager.common.utils;

import android.widget.Toast;

import com.yueyue.studentinfomanager.base.App;

/**
 * Created by HugoXie on 16/5/23.
 * <p>
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * Info:
 */
public class ToastUtil {

    public static void showShort(String msg) {
        Toast.makeText(App.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(App.getAppContext(), msg, Toast.LENGTH_LONG).show();
    }
}
