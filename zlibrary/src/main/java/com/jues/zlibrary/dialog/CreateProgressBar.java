package com.jues.zlibrary.dialog;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.tu.loadingdialog.LoadingDailog;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/19
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class CreateProgressBar {
    @SuppressLint("StaticFieldLeak")
    private static LoadingDailog loadingDailog;

    public static LoadingDailog showProgress(Context context, String msg) {
        if (null == loadingDailog) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(msg.equals("") ? "加载中…" : msg)
                    .setCancelable(true)
                    .setCancelOutside(false);
            loadingDailog = loadBuilder.create();
        }
        return loadingDailog;
    }

    public static void dismissProgress() {
        if (loadingDailog != null) loadingDailog.dismiss();
    }
}
