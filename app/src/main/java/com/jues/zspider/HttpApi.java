package com.jues.zspider;

import com.jues.zlibrary.config.HttpConfig;
import com.jues.zspider.global.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Introduce:
 * <p>
 * Create By JueShou -- 2018/5/8.21:52
 * Project: ZRxJavaUtil
 * Email: 152063943642@163.com
 */
public class HttpApi {
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpConfig.httpConfig()).baseUrl(Constant.BASE_URL).build();
    }
}
