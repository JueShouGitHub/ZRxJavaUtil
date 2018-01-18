package com.jues.zlibrary.api;

import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by JueShou on 2017/11/25:21:35.
 * Mail :1172783012@qq.com
 */

public class EncryptionUtil<T> {

    //AES加密key
    private static String m_key = "ca751e7aeaf600700933bcb1e609a455";
    //AES加密Vector
    private static String m_vector = "ca751e7aeaf60070";
    //接口版本号
    private static String m_version = "1.0.0";

    /**
     * 加密文本
     *
     * @param plaintext 请求的明文
     * @return 加密字符串
     * @throws Exception 异常
     */
    public static String aesEncrypt(String plaintext) throws Exception {
        //获取加密器
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

        //获取key的字节内容
        byte[] raw = m_key.getBytes();

        SecretKeySpec spec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(m_vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, spec, iv);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes("utf-8"));
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);// 此处使用BASE64做转码。
    }

    /**
     * 解密文本
     *
     * @param ciphertext 返回的密文
     * @return 解密后的字符串
     */
    @Nullable
    public static String aesDecrypt(String ciphertext) {
        try {
            byte[] raw = m_key.getBytes("ASCII");
            SecretKeySpec spec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(m_vector.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, spec, iv);
            byte[] encrypted1 = Base64.decode(ciphertext.getBytes(), Base64.NO_WRAP);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 生成请求报文
     *
     * @param module        接口模块ID
     * @param interfaceCode 接口ID
     * @param data          接口请求数据
     * @return 返回拼装后加密的Json字符转
     * @throws Exception 异常
     */
    @Deprecated
    public static String generateMessage(String module, String interfaceCode, JSONObject data) throws Exception {
        JSONObject requestData = new JSONObject();

        //版本号
        requestData.put("version", m_version);
        //模块
        requestData.put("module", module);
        //接口编码
        requestData.put("interface", interfaceCode);

        //请求数据
        if (data == null) {
            data = new JSONObject();
        }
        requestData.put("data", data);

        String tempString = requestData.toString();
        Log.e("Tag", tempString);

        JSONObject result = new JSONObject();
        result.put("body", aesEncrypt(tempString));

        return result.toString();
    }

    /**
     * 生成请求报文
     *
     * @param module        接口模块ID
     * @param interfaceCode 接口ID
     * @param data          接口请求数据
     * @return 返回拼装后加密的Json字符转
     * @throws Exception 异常
     */
    public String generateMessage(String module, String interfaceCode, T data) throws Exception {
        JSONObject requestData = new JSONObject();
        JSONObject object;

        //版本号
        requestData.put("version", m_version);
        //模块
        requestData.put("module", module);
        //接口编码
        requestData.put("interface", interfaceCode);

        //请求数据
        if (data == null) object = new JSONObject();
        else object = new JSONObject(new Gson().toJson(data));
        requestData.put("data", object);

        String tempString = requestData.toString();
        Log.e("Tag", tempString);

        //加密数据，此处不需要加密，在DecodeRequestBodyConverter中处理加密
//        JSONObject result = new JSONObject();
//        result.put("body", aesEncrypt(tempString));

        return tempString;
    }

    /**
     * 生成请求报文
     *
     * @param data 接口请求数据
     * @return 返回拼装后加密的Json字符转
     * @throws Exception 异常
     */
    public String generateMessage(T data) throws Exception {
        JSONObject requestData = new JSONObject();
        JSONObject object;

        //请求数据
        if (data == null) object = new JSONObject();
        else object = new JSONObject(new Gson().toJson(data));
        requestData.put("data", object);

        String tempString = requestData.toString();
        Log.e("Tag", tempString);

        JSONObject result = new JSONObject();
        result.put("body", aesEncrypt(tempString));

        return result.toString();
    }
}
