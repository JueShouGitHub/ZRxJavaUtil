package com.jues.zlibrary.api.request;

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
     *  @param observable
     * @param observer
     */
    public static void subscribe(Observable<AdEntity> observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
