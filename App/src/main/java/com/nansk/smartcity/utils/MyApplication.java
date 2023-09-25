package com.nansk.smartcity.utils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 12:49
 * @description
 */

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    public static Gson gson = new Gson();

    public static OkHttpClient httpClient = new OkHttpClient.Builder().build();

    public static String IP;
    private static String defaultIp = "http://124.93.196.45:10001";

    public static String token;
    public static boolean isLogin;

    /**
     * @Create 2021/10/12 11:58
     * @Role 用户登录状态
     * @Param
     */
    public static boolean isLogin(Context context) {
        boolean isLogin = (boolean) SharedPreferencesUtils.getRecord(context, "isLogin", false);
        return isLogin;
    }

    public static void setIsLogin(Context context,boolean isLogin) {
        SharedPreferencesUtils.addRecord(context,"isLogin",false);
        MyApplication.isLogin = isLogin;
    }

    public static String getToken(Context context) {
        String token = (String) SharedPreferencesUtils.getRecord(context, "token", "");
        return token;
    }

    /**
     * @Create 2021/10/12 11:58
     * @Role 服务器IP
     * @Param
     */
    public static String getIP(Context context) {
        String ip = (String) SharedPreferencesUtils.getRecord(context, "ip", defaultIp);
        IP = ip;
        return IP;
    }

    public static void setIP(Context context,String value) {
        SharedPreferencesUtils.addRecord(context,"ip","http://" + value);
        MyApplication.IP = value;
    }

}
