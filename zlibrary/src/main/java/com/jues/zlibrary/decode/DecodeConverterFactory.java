package com.jues.zlibrary.decode;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.annotations.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2017/11/25
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 * 自定义 ConverterFactory Retrofit转换工厂
 */

public class DecodeConverterFactory extends Converter.Factory {
    private final Gson gson;

    public DecodeConverterFactory(Gson gson) {
        if (null == gson) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @NonNull
    public static DecodeConverterFactory create() {
        return create(new Gson());
    }

    @NonNull
    public static DecodeConverterFactory create(Gson gson) {
        return new DecodeConverterFactory(new Gson());
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        //return new Deco TODO
        return new DecodeRequestBodyConverter<>(gson, adapter);
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new DecodeResponseBodyConverter<>(adapter);
    }
}
