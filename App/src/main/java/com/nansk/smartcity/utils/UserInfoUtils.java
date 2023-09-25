package com.nansk.smartcity.utils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 11:34
 * @description
 */

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 11:33
 * @description 用户信息工具类
 */

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.nansk.smartcity.beans.UserInfoBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserInfoUtils {

    /**
     * @Create 2021/10/12 11:33
     * @Role 从服务器更新全部用户信息
     * @Param
     */
    public static void updateUserInfo(final Context context) {
        final Handler handler = new Handler();
        String url = ConnectInfo.getUrl(ConnectInfo.USER_INFO, "");

        OkHttpUtil.doGet(url, MyApplication.getToken(context), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final UserInfoBean jsonObj = MyApplication.gson.fromJson(response.body().string(), UserInfoBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            UserInfoBean.UserBean user = jsonObj.getUser();
                            //将对象序列化存入sp
                            String json = MyApplication.gson.toJson(user);
                            SharedPreferencesUtils.addRecord(context,"userInfo",json);
                        } else {
                            Toast.makeText(context, "获取用户信息失败\n" + jsonObj.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    /**
     * @Create 2021/10/12 11:47
     * @Role 获取已保存的用户信息
     * @Param
     */

    public static UserInfoBean.UserBean getUserInfo(Context context) {
        String record = (String) SharedPreferencesUtils.getRecord(context, "userInfo", "");
        UserInfoBean.UserBean userBean = MyApplication.gson.fromJson(record, UserInfoBean.UserBean.class);
        return userBean;
    }
}
