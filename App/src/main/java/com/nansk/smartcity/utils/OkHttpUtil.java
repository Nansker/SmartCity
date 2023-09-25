package com.nansk.smartcity.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.nansk.smartcity.R;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Timeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 16:35
 * @Description okHttp工具类 封装post、get请求
 */

public class OkHttpUtil {
    private static final MediaType JSON = MediaType.parse("application/json");

    /**
     * @Create 2021/9/8 16:38
     * @Role post请求
     * @Param
     */
    public static void doPost(String url, String token, Object object, Callback callBack) {
        RequestBody body = RequestBody.create(JSON, MyApplication.gson.toJson(object));
        Request request = new Request.Builder()
                .url(url)
                .post(body).addHeader("Authorization", token)
                .build();
        MyApplication.httpClient.newCall(request).enqueue(callBack);
    }

    /**
     * @Create 2021/9/8 16:38
     * @Role get请求
     * @Param
     */
    public static void doGet(String url, String token, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token).get()
                .build();
        MyApplication.httpClient.newCall(request).enqueue(callback);
    }

    /**
     * @Create 2021/9/15 15:35
     * @Role put请求
     * @Param
     */
    public static void doPut(String url, String token, Object object, Callback callback) {
        RequestBody body = RequestBody.create(JSON,MyApplication.gson.toJson(object));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .put(body)
                .build();
        MyApplication.httpClient.newCall(request).enqueue(callback);
    }

    /**
     * @Create 2021/9/8 16:38
     * @Role delete请求
     * @Param
     */
    public static void delete(String url, String token,Object object, Callback callBack) {
        RequestBody body = RequestBody.create(JSON, MyApplication.gson.toJson(object));
        Request request = new Request.Builder()
                .url(url)
                .delete(body).addHeader("Authorization", token)
                .build();
        MyApplication.httpClient.newCall(request).enqueue(callBack);
    }

    public static void upload(String url,String token,Object object,Callback callback){
        RequestBody body = RequestBody.create(JSON, MyApplication.gson.toJson(object));
        Request request = new Request.Builder()
                .url(url).post(body).addHeader("Authorization",token).build();
        MyApplication.httpClient.newCall(request).enqueue(callback);
    }

}
