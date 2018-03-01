package com.jues.zlibrary.decode;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.jues.zlibrary.api.EncryptionUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by JueShou on 2017/11/25:21:14.
 * Mail :1172783012@qq.com//全局//常量
 */

public class DecodeRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public DecodeRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(@NonNull T value) throws IOException {
        //String tempStr = value.toString();
        //String tempStr = gson.toJson(value);
        String tempStr = value.toString();
        Log.w("RequestParam", tempStr);
        //以下为加密处理
        try {
            JSONObject object = new JSONObject();
            object.put("body", EncryptionUtil.aesEncrypt(tempStr));
            tempStr = object.toString();
            Log.w("RequestParam加密", tempStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(MEDIA_TYPE, tempStr);
    }
}
