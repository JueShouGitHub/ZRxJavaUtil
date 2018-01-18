package com.jues.zlibrary.base;

import android.content.Context;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/18
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public interface ZBaseView {
    Context getContext();

    List<Disposable> getDisposableList();

    void toast(String s);

    void showLoading();

    void dismissLoading();
}
