package com.nansk.smartcity.design.support;

import android.os.Bundle;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 16:18
 * @description 扶贫案例
 */

public class SupportCaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_case);
        setToolBarTitle("扶贫案例");

    }
}