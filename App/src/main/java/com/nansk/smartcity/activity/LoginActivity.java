package com.nansk.smartcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.UserAccountInfo;
import com.nansk.smartcity.beans.UserLoginBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/14 14:57
 * @Description 用户登录页
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText accountEt;
    private EditText passwordEt;
    private Button registerBtn;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_login);
        setToolBarTitle("用户登录");
        initView();
    }

    private void initView() {
        accountEt = (EditText) findViewById(R.id.account_et);
        passwordEt = (EditText) findViewById(R.id.password_et);
        registerBtn = (Button) findViewById(R.id.register_btn);
        loginBtn = (Button) findViewById(R.id.login_btn);

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

    }

    /**
     * @Create 2021/9/14 15:39
     * @Role 用户登录
     * @Param
     */
    private void toLogin() {

        //用户输入的账号信息
        final String account = accountEt.getText().toString().trim();
        final String password = passwordEt.getText().toString().trim();
        final String url = ConnectInfo.getUrl(ConnectInfo.USER_LOGIN, "");

        final UserAccountInfo accountInfo = new UserAccountInfo();
        accountInfo.setUsername(account);
        accountInfo.setPassword(password);

        OkHttpUtil.doPost(url, "", accountInfo, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final UserLoginBean loginBean = MyApplication.gson.fromJson(response.body().string(), UserLoginBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //登录成功
                        if (loginBean.getCode() == 200) {
                            //保存用户账号密码
                            SharedPreferencesUtils.addRecord(LoginActivity.this,"userAccount",MyApplication.gson.toJson(accountInfo));
                            //设置用户登录状态，保存用户详细信息
                            SharedPreferencesUtils.addRecord(LoginActivity.this,"isLogin",true);
                            //添加用户Token
                            SharedPreferencesUtils.addRecord(LoginActivity.this,"token", loginBean.getToken());
                            //更新用户信息
                            UserInfoUtils.updateUserInfo(LoginActivity.this);

                            showToast("登录成功！",1050);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                        finish();
                                    }
                            }, 1000);
                            //登录失败
                        } else {
                            showToast(loginBean.getMsg(),1200);
                        }
                    }
                });
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //跳转注册页
            case R.id.register_btn:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            //用户登录
            case R.id.login_btn:
                toLogin();
                break;
        }
    }

}