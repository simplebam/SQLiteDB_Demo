package com.yueyue.studentinfomanager.modules.login.utils;

import android.text.TextUtils;

/**
 * author : yueyue on 2018/2/8 15:45
 * desc   :
 */

public class ValidateUtil {

    private static final String NUMBER_PATTERN = "^[0-9]*$";

    public static boolean userName(String userName) {
        return !userName.isEmpty() && userName.matches(NUMBER_PATTERN);
    }

    public static boolean password(String password) {
        return !TextUtils.isEmpty(password) && password.length() > 5;
    }
}
