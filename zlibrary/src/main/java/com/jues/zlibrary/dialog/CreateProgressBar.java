package com.jues.zlibrary.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.jues.zlibrary.R;
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
    private static GifLoadingView loadingView;

    private static void getInfo() {
        LemonBubbleInfo bubbleInfo = LemonBubble.getRoundProgressBubbleInfo();
        bubbleInfo.setIconColor(Color.YELLOW);
    }

    public static GifLoadingView showProgress(Context context, String msg) {
//        getInfo();
//        if (context != null)LemonBubble.showRoundProgress(context,msg);
        if (null == loadingView) loadingView = new GifLoadingView();
        loadingView.setCancelable(true);
        Activity activity = (Activity) context;
        loadingView.setImageResource(R.drawable.load_cat_eye);
        loadingView.show(activity.getFragmentManager(), msg);
        /*这是由于延迟执行事务更改。我们可以通过调用FragmentManager类的
        executePendingTransactions（）方法强制FragmentTransaction实例的工作立即执行。
        当对executePendingExecutions（）方法的调用返回时，
        我们知道所有提交的FragmentTransaction工作都已执行。*/
        activity.getFragmentManager().executePendingTransactions();
        loadingView.getDialog().setCanceledOnTouchOutside(false);
        return loadingView;
    }

    public static void dismissProgress() {
//        getInfo();
//        LemonBubble.forceHide();
        if (loadingView != null) loadingView.dismiss();
    }
}
