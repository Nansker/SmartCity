package com.nansk.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nansk.smartcity.activity.MainActivity;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/07 18:12
 * @Description
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        WindowMangerUtils.setSysStatusBar(SplashActivity.this, 1);
        MyApplication.getIP(SplashActivity.this);
        //延时跳转页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                boolean firstOpenApp = isFirstOpenApp();
                if (firstOpenApp == true) {
                    //打开引导页
                    intent = new Intent(SplashActivity.this, GuidePagerActivity.class);
                    startActivity(intent);
                } else if (firstOpenApp == false) {
                    //跳转到主页
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    //增加加一次登录次数
                    int loginCount = (int) SharedPreferencesUtils.getRecord(SplashActivity.this, "loginCount", 0);
                    SharedPreferencesUtils.addRecord(SplashActivity.this,"loginCount",loginCount+1);
                }
            }
        }, 1000);
    }

    public boolean isFirstOpenApp() {
            int loginCount = (int) SharedPreferencesUtils.getRecord(SplashActivity.this, "loginCount", 0);
            if (loginCount == 0) {
                return true;
            } else {
                return false;
            }
    }

}