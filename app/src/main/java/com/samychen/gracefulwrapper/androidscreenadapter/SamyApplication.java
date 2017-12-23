package com.samychen.gracefulwrapper.androidscreenadapter;

import android.app.Application;

/**
 * Created by samychen on 2017/12/23 0023.
 * 我的github地址 https://github.com/samychen
 * 方便框架使用Application和获取context
 */

public class SamyApplication extends Application{
    private static SamyApplication instance;
    public static SamyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
