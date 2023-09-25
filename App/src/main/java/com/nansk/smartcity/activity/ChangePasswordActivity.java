package com.nansk.smartcity.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.ModifyPasswordBean;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.UserAccountInfo;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/15 16:00
 * @Description 修改密码页
 */

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText oldPasswordEt;
    private EditText newPasswordEt;
    private Button modifyBtn;
    private EditText confirmPasswordEt;

    private UserAccountInfo accountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_change_password);
        setToolBarTitle("修改密码");
        initView();
    }

    private void initView() {
        oldPasswordEt = (EditText) findViewById(R.id.oldPassword_et);
        newPasswordEt = (EditText) findViewById(R.id.newPassword_et);
        confirmPasswordEt = (EditText) findViewById(R.id.confirmPassword_et);
        modifyBtn = (Button) findViewById(R.id.modify_btn);
        modifyBtn.setOnClickListener(this);
        String account = (String) SharedPreferencesUtils.getRecord(ChangePasswordActivity.this, "userAccount", "");
        accountInfo = MyApplication.gson.fromJson(account, UserAccountInfo.class);
    }


    /**
     * @Create 2021/10/21 11:18
     * @Role
     * @Param
     */

    private void checkPassword() {
        String oldPassword = oldPasswordEt.getText().toString().trim();
        String newPassword = newPasswordEt.getText().toString().trim();
        String confirmPassword = confirmPasswordEt.getText().toString().trim();

        if (!oldPassword.equals("")) {
            if (!newPassword.equals("")) {
                if (!confirmPassword.equals("")) {
                    if (oldPassword.equals(accountInfo.getPassword())) {
                        if (newPassword.equals(confirmPassword)) {
                            modifyPassword();
                        } else {
                            showToast("两次密码输入不一致！");
                        }
                    } else {
                        showToast("原密码输入不正确！");
                    }
                } else {
                    showToast("请再次输入新密码！");
                }
            } else {
                showToast("请输入新密码！");
            }
        } else {
            showToast("请输入原密码！");
        }
    }

    /**
     * @Create 2021/10/21 11:22
     * @Role
     * @Param
     */
    private void modifyPassword() {
        String url = ConnectInfo.getUrl(ConnectInfo.RESET_PASSWORD, "");

        ModifyPasswordBean passwordObj = new ModifyPasswordBean();
        passwordObj.setNewPassword(newPasswordEt.getText().toString().trim());
        passwordObj.setOldPassword(accountInfo.getPassword());

        OkHttpUtil.doPut(url, MyApplication.getToken(ChangePasswordActivity.this), passwordObj, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            MyApplication.setIsLogin(ChangePasswordActivity.this, false);
                            showToast("密码修改成功！,需要重新登录！");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 1200);

                        } else {
                            showToast(json.getMsg());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_btn:
                checkPassword();
                break;
        }
    }


}