package com.jues.zlibrary.api;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.android.tu.loadingdialog.LoadingDailog;
import com.jues.zlibrary.dialog.CreateProgressBar;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2017/11/25
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public abstract class BaseObserver<T> implements Observer<T> {
    protected boolean showLoading = true;
    protected Context mContext;
    protected List<Disposable> disposableList;

    protected BaseObserver(Context context) {
        this.mContext = context;
    }

    protected BaseObserver(Context context, boolean isShow) {
        this.mContext = context;
        this.showLoading = isShow;
    }

    /**
     * 构造方法 用于保存Disposable 取消请求 解除订阅
     * 在activity的onDestroy方法中遍历调用
     *
     * @param disposableList List<Disposable>
     */
    protected BaseObserver(Context context, List<Disposable> disposableList) {
        this.mContext = context;
        this.disposableList = disposableList;
    }

    /**
     * 构造方法 用于保存Disposable 取消请求 解除订阅
     * 在activity的onDestroy方法中遍历调用
     *
     * @param disposableList List<Disposable>
     * @param isShow         是否显示loading
     */
    protected BaseObserver(Context context, List<Disposable> disposableList, boolean isShow) {
        this.mContext = context;
        this.disposableList = disposableList;
        this.showLoading = isShow;
    }

    //执行
    protected abstract void onExecute(T t);

    protected void onError(String msg) {
    }

    @Override
    public void onNext(T t) {
        onExecute(t);
    }

    @Override
    public void onComplete() {
        CreateProgressBar.dismissProgress();
    }

    @Override
    public void onError(Throwable t) {
        CreateProgressBar.dismissProgress();
        onError(t.getLocalizedMessage());
        Log.e("JS-Error", t.toString());
    }

    @Override
    public void onSubscribe(final Disposable d) {
        LoadingDailog loadingView = null;
        if (null != mContext) {
            loadingView = CreateProgressBar.showProgress(mContext, "加载中...");
            if (showLoading) loadingView.show();
        }
        if (null != disposableList) disposableList.add(d);
        loadingView.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                disposableList.remove(d);
                d.dispose();
            }
        });
    }

}
