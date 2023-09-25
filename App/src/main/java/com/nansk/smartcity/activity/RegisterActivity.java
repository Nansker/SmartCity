package com.nansk.smartcity.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/14 15:40
 * @Description 用户注册页
 */
public class RegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_register);
        setToolBarTitle("用户注册");
    }
}
