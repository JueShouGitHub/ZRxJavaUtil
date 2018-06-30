package com.jues.zlibrary.api;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by Android Studio .
 * 作者：zhong
 * 日期：2018/6/30
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */
public class BaseObservable<T> extends Observable<T> {
    protected RxObservable<T> mRxObservable;

    public BaseObservable(RxObservable<T> rxObservable) {
        mRxObservable = rxObservable;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        if (mRxObservable != null) mRxObservable.subscribe(observer);
    }

    public interface RxObservable<T> {
        void subscribe(Observer<? super T> observer);
    }
}
