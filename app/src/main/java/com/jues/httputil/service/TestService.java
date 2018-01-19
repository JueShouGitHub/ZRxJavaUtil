package com.jues.httputil.service;

import com.jues.httputil.entity.AdEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/17
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public interface TestService {
    @POST()
    Observable<AdEntity> getData(@Url String url, @Body String request);
}
