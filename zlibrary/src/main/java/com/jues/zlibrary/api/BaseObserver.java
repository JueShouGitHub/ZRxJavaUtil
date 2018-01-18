package com.jues.zlibrary.api;

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
    private List<Disposable> disposableList;

    protected BaseObserver() {
    }

    /**
     * 构造方法 用于保存Disposable 取消请求 解除订阅
     * 在activity的onDestroy方法中遍历调用
     *
     * @param disposableList List<Disposable>
     */
    public BaseObserver(List<Disposable> disposableList) {
        this.disposableList = disposableList;
    }

    //执行
    protected abstract void onExecute(T t);

    protected abstract void onError(String msg);

    @Override
    public void onNext(T t) {
        onExecute(t);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {
        onError(t.getLocalizedMessage());
        //Log.e("JS-Error", t.toString());
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (null != disposableList) disposableList.add(d);
    }

}
