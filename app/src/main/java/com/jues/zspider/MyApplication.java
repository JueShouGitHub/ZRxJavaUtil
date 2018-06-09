package com.jues.zspider;

import android.app.Application;

import crashlogcathelper.CrashLogcatHelper;

/**
 * Created by Android Studio .
 * 作者：zhong
 * 日期：2018/6/9
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashLogcatHelper.getInstance().init(this,"CrashTest",true);
    }
}
