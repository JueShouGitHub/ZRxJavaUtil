package com.jues.zspider;

import com.jues.zspider.global.Constant;

import retrofit2.Retrofit;

/**
 * Introduce:
 * <p>
 * Create By JueShou -- 2018/5/8.21:52
 * Project: ZRxJavaUtil
 * Email: 152063943642@163.com
 */
public class HttpApi {
    public static Retrofit getRetrofit(){
        return com.jues.zlibrary.api.HttpApi.rxNoEncryRetrofit().baseUrl(Constant.BASE_URL).build();
    }
}
