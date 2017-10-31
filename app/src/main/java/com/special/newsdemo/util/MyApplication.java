package com.special.newsdemo.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Special on 2017/10/31.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
