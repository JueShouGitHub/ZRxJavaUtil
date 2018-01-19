package com.jues.zlibrary.api;

import com.google.gson.Gson;
import com.jues.zlibrary.api.request.BaseRequest;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/18
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class ZRequest<T> {
    public BaseRequest getObjRequest(T t, String module, String interf) {
        BaseRequest request;
        EncryptionUtil<T> util = new EncryptionUtil<>();
        String s = "";
        try {
            s = util.generateMessage(module, interf, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        request = gson.fromJson(s, BaseRequest.class);
        return request;
    }

    /**
     * 特殊 special 个人公司使用
     *
     * @param t      请求体
     * @param module 模块名称
     * @param interf 接口名称
     * @return String
     */
    public String getSpecialRequest(T t, String module, String interf) {
        //BaseRequest request;
        EncryptionUtil<T> util = new EncryptionUtil<>();
        String s = "";
        try {
            s = util.generateMessage(module, interf, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Gson gson = new Gson();
        //request = gson.fromJson(s, BaseRequest.class);
        return s;
    }

    /**
     * 通用生成请求
     *
     * @param data 请求体
     * @return String
     */
    public String getGeneralRequest(T data) {
        String s = "";
        try {
            EncryptionUtil<T> util = new EncryptionUtil<>();
            s = util.generateMessage(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
