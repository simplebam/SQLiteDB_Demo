package com.yueyue.studentinfomanager.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.jasypt.util.text.BasicTextEncryptor;

public class Config {

    // 第一次进入主页
    private static final String SHARE_USERNAME = "loginname";
    private static final String SHARE_PASSWORD = "password";
    private static final String ENCRYPT_PASSWORD = "81399085ba848c87";

    public static String getCachePassword(Context context) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(getEncryptPass());
        String pass = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE)
                .getString(SHARE_PASSWORD, "");
        if (TextUtils.isEmpty(pass))
            return pass;
        else
            return encryptor.decrypt(pass);//解密
    }

    public static void cachePassword(Context context, String password) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(getEncryptPass());
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE).edit();
        editor.putString(SHARE_PASSWORD, encryptor.encrypt(password));//加密
        editor.commit();
    }

    public static String getCacheUserName(Context context) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(getEncryptPass());
        String userName = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE).getString(SHARE_USERNAME, "");
        if (TextUtils.isEmpty(userName))
            return userName;
        else
            return encryptor.decrypt(userName);
    }

    public static void cacheUserName(Context context, String userName) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(getEncryptPass());
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE).edit();
        editor.putString(SHARE_USERNAME, encryptor.encrypt(userName));
        editor.commit();
    }

    private static String encryptPass = null;

    public static void setEncryptPass(String encryptPass) {
        if (Config.encryptPass != null) return;
        if (TextUtils.isEmpty(encryptPass))
            Config.encryptPass = ENCRYPT_PASSWORD;
        else
            Config.encryptPass = encryptPass;
    }

    private static String getEncryptPass() {
        return encryptPass;
    }

    private Config() {
    }


}
