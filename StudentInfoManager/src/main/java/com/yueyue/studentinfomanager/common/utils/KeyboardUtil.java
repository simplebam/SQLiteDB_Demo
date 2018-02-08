package com.yueyue.studentinfomanager.common.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * author : yueyue on 2018/2/8 10:57
 * desc   :
 */

public class KeyboardUtil {


    /**
     * 隐藏键盘
     */
    public static void hideKeyboard(@NonNull Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager == null) return;
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
