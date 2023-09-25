package com.nansk.smartcity.utils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/07 18:50
 * @Description SharedPreferences储存应用信息工具类
 */

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static String FILE_NAME = "userdata";

    /**
     * @Create 2021/9/7 19:11
     * @Role 通过SharedPreferences添加一条字符串类型记录
     * @Param
     */
    public static void addRecord(Context context, String key, Object object) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String type = object.getClass().getSimpleName();

        SharedPreferences.Editor edit = preferences.edit();

        if ("String".equals(type)) {
            edit.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            edit.putInt(key, (int) object);
        }else if ("int".equals(type)) {
            edit.putInt(key, (int) object);
        } else if ("Boolean".equals(type)) {
            edit.putBoolean(key, (Boolean) object);
        }else if ("Number".equals(type)) {
            edit.putString(key, (String) object);
        }else if ("LazilyParsedNumber".equals(type)){
            edit.putFloat(key, Float.parseFloat(object.toString()));
        }

        edit.commit();
    }

    /**
     * @Create 2021/9/7 19:30
     * @Role 返回一条字符串类型记录
     * @Param
     */
    public static Object getRecord(Context context,String key,Object defaultValue) {
        String type = defaultValue.getClass().getSimpleName();
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if ("String".equals(type)) {
            return preferences.getString(key, (String) defaultValue);
        } else if ("Integer".equals(type)) {
            return preferences.getInt(key, (int) defaultValue);
        } else if ("int".equals(type)) {
            return preferences.getInt(key, (int) defaultValue);
        } else if ("Boolean".equals(type)) {
            return preferences.getBoolean(key, (Boolean) defaultValue);
        }else if ("Number".equals(type)) {
            return preferences.getString(key, (String) defaultValue);
        }else if ("Float".equals(type)){
            return preferences.getFloat(key, (float) defaultValue);
        }else if ("float".equals(type)){
            return preferences.getFloat(key, (float) defaultValue);
        }else if ("double".equals(type)){
            return preferences.getFloat(key, (float) defaultValue);
        }

        return  null;
    }


    /**
     * @Create 2021/9/7 19:19
     * @Role 删除一条记录
     * @Param
     */
    public void clearRecord(Context context,String key) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(key);
        edit.commit();
    }

//    /**
//     * @Create 2021/9/7 19:21
//     * @Role 添加一次用户登录次数
//     * @Param
//     */
//    public void setLoginCount() {
//        SharedPreferences.Editor editor = preferences.edit();
//        int count = getLoginCount() + 1;
//        editor.putInt("loginCount", count);
//        editor.commit();
//    }
//
//    /**
//     * @Create 2021/9/7 19:21
//     * @Role 获取用户登录次数
//     * @Param
//     */
//    public int getLoginCount() {
//        int count = preferences.getInt("loginCount", 0);
//        return count;
//    }
//
//    public String getToken() {
//        String token = preferences.getString("token", "");
//        return token;
//    }
//
//    /**
//     * @Create 2021/9/14 21:51
//     * @Role 设置用户登录状态
//     * @Param
//     */
//    public void setLoginStatus(boolean status) {
//        SharedPreferences.Editor edit = preferences.edit();
//        edit.putBoolean("isLogin", status);
//        edit.commit();
//    }
//
//    /**
//     * @Create 2021/9/14 21:52
//     * @Role 获取用户登录状态
//     * @Param
//     */
//    public boolean isLogin() {
//        boolean isLogin = preferences.getBoolean("isLogin", false);
//        return isLogin;
//    }

}
