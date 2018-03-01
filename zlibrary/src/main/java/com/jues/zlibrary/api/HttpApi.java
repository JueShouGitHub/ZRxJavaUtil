package com.jues.zlibrary.api;

import android.support.annotation.NonNull;

import com.jues.zlibrary.config.HttpConfig;
import com.jues.zlibrary.decode.DecodeConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/17
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class HttpApi {
    /**
     * 普通Retrofit 网络请求
     *
     * @return 返回一个Retrofit实例
     */
    @NonNull
    public static Retrofit retrofit() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .client(HttpConfig.httpConfig())
                .build();
    }

    /**
     * RxJava Retrofit 网络请求(数据加密)
     *
     * @return 返回一个Retrofit实例
     */
    @NonNull
    public static Retrofit rxEncryRetrofit() {//加密
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(DecodeConverterFactory.create())
                .client(HttpConfig.httpConfig())
                .build();
    }

    /**
     * RxJava Retrofit 网络请求(数据不加密)
     *
     * @return 返回一个Retrofit实例
     */
    @NonNull
    public static Retrofit rxNoEncryRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpConfig.httpConfig()).build();
    }
}
