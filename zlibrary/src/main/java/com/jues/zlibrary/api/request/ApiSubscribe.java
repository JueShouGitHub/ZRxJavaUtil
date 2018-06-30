package com.jues.zlibrary.api.request;

import com.jues.zlibrary.api.BaseObservable;
import com.jues.zlibrary.api.BaseObserver;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Introduce:
 * <p>
 * Create By JueShou -- 2018/1/18.23:24
 * Project: ZRxJavaUtil
 * Email: 152063943642@163.com
 */

public class ApiSubscribe {

    /**
     * 线程控制，省去重复订阅的代码
     *
     * @param observable
     * @param observer
     */
    public static <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 线程控制
     * 此方法仅用于控制异步操作，不能作为网络请求方式
     *
     * @param observable
     * @param observer
     */
    public static <T> void asyncSubscribe(BaseObservable<T> observable, BaseObserver<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
