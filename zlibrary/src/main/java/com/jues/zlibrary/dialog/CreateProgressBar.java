package com.jues.zlibrary.dialog;

import android.content.Context;
import android.graphics.Color;

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
    private static void getInfo(){
        LemonBubbleInfo bubbleInfo = LemonBubble.getRoundProgressBubbleInfo();
        bubbleInfo.setIconColor(Color.YELLOW);
    }

    public static void showProgress(Context context,String msg){
        getInfo();
        if (context != null)LemonBubble.showRoundProgress(context,msg);
    }

    public static void dismissProgress(){
        getInfo();
        LemonBubble.forceHide();
    }
}
