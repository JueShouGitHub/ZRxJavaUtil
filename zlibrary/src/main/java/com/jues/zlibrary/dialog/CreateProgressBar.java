package com.jues.zlibrary.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import com.jues.zlibrary.R;
import com.jues.zlibrary.callback.BaseCallback;
import com.roger.gifloadinglibrary.GifLoadingView;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.LemonBubbleInfo;

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
    private static GifLoadingView loadingView = new GifLoadingView();

    private static void getInfo() {
        LemonBubbleInfo bubbleInfo = LemonBubble.getRoundProgressBubbleInfo();
        bubbleInfo.setIconColor(Color.YELLOW);
    }

    public static void showProgress(Context context, String msg) {
//        getInfo();
//        if (context != null)LemonBubble.showRoundProgress(context,msg);
        loadingView.setCancelable(false);
        Activity activity = (Activity) context;
        loadingView.setImageResource(R.drawable.load_cat_eye);
        loadingView.show(activity.getFragmentManager(), msg);
    }

    public static void dismissProgress() {
//        getInfo();
//        LemonBubble.forceHide();
        if (loadingView != null) loadingView.dismiss();
    }
}
