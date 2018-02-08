package com.yueyue.studentinfomanager.base;

import android.app.Application;
import android.content.Context;

/**
 * author : yueyue on 2018/2/8 11:39
 * desc   :
 */

public class App extends Application {
    private static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }

    public static Context getAppContext() {
        return sAppContext;
    }
}
