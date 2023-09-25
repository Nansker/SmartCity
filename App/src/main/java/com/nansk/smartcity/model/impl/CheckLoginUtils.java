package com.nansk.smartcity.model.impl;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/15 15:32
 * @description
 */

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.nansk.smartcity.model.CheckLogin;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.dialog.ToastDialog;
import com.nansk.smartcity.utils.MyApplication;

public class CheckLoginUtils {

    public static void isLogin(final Context context, boolean autoLogin, CheckLogin callBack) {
        if (MyApplication.isLogin(context)) {
            callBack.onAlready();
        } else {
            if (autoLogin) {
                final ToastDialog dialog = new ToastDialog(context);
                dialog.showReveal("用户未登录，即将跳转到登录...");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        context.startActivity(new Intent(context, LoginActivity.class));
                        dialog.dismiss();
                    }
                }, 800);

            }
            callBack.onNone();
            }
        }
    }
