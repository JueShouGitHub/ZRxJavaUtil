package com.jues.zspider.service;

import com.jues.zlibrary.api.BaseObserver;
import com.jues.zspider.entity.AdEntity;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/17
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public interface TestService {
    @Headers("Authorization:APPCODE c7578936e2a542799f4b32c2d95c2f84")
    @POST("whapi/json/alicityweather/briefforecast3days")
    Observable<AdEntity> getData(@Query("cityId") String cityId);

}
