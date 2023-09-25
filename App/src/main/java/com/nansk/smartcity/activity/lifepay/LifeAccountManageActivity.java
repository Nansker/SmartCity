package com.nansk.smartcity.activity.lifepay;

import android.os.Bundle;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 14:58
 * @Description 户号管理页
 */

public class LifeAccountManageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_life_account_manage);
        setToolBarTitle("户号管理");
    }
}