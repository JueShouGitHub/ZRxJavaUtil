package com.jues.zlibrary.config;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/17
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class HttpConfig {
    private final static Long TimeOut = 30L;

    /**
     * 打印请求信息 日志
     *
     * @return HttpLoggingInterceptor（拦截器）
     */
    private static HttpLoggingInterceptor loggingInterceptorConfig() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("LogDebug", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    /**
     * Http 配置
     *
     * @return OkHttpClient 客户端
     */
    @NonNull
    public static OkHttpClient httpConfig() {
        return new OkHttpClient.Builder().cache(null)
                .addInterceptor(loggingInterceptorConfig())
                .connectTimeout(TimeOut, TimeUnit.SECONDS)
                .readTimeout(TimeOut, TimeUnit.SECONDS)
                .writeTimeout(TimeOut, TimeUnit.SECONDS)
                .build();
    }
}
