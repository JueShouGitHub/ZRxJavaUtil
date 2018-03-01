package com.jues.zlibrary.decode;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.TypeAdapter;
import com.jues.zlibrary.api.EncryptionUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by JueShou on 2017/11/25:21:16.
 * Mail :1172783012@qq.com
 */

public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final TypeAdapter<T> adapter;

    public DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String response = value.string();
        String json = EncryptionUtil.aesDecrypt(response);
        Log.w("ResponseBody", response);
        Log.w("ResponseBody解密", json);
        try {
            JSONObject object = new JSONObject(json);
            String code = object.getString("result_code");
            if (!code.equals("0000")) throw new IOException(object.getString("result_info"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return adapter.fromJson(json);
    }
}
