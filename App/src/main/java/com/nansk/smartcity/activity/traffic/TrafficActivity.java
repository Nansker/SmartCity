package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.beans.traffic.TrafficLicenseUserBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/10/02 22:22
 * @Description
 */

public class TrafficActivity extends BaseActivity {

    private String token;

    private GridLayout applicationContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic);
        setToolBarTitle("智慧交管");
        setToolBarBackground("#2c65a8");

        initView();
        initObject();
    }


    private void initView() {

        applicationContainer = (GridLayout) findViewById(R.id.application_container);
    }

    private void initObject() {

        token = MyApplication.getToken(this);

        String[] funNames = getResources().getStringArray(R.array.traffic_aap);
        for (int i = 0; i < funNames.length; i++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(5);
            gradientDrawable.setColor(Color.WHITE);
            gradientDrawable.setStroke(3, getResources().getColor(R.color.theme_traffic));

            final TextView textView = new TextView(TrafficActivity.this);
            textView.setPadding(30, 15, 30, 15);
            textView.setTextSize(15);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setBackground(gradientDrawable);

            textView.setText(funNames[i]);

            GridLayout.LayoutParams gridlayoutParams = new GridLayout.LayoutParams();
            gridlayoutParams.leftMargin = 15;
            gridlayoutParams.rightMargin = 15;
            gridlayoutParams.bottomMargin = 15;
            gridlayoutParams.topMargin = 15;

            textView.setLayoutParams(gridlayoutParams);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    link(textView.getText().toString());
                }
            });

            applicationContainer.addView(textView);
        }
    }

    /**
     * @Create 2021/10/5 15:17
     * @Role 判断用户是否绑定了驾驶证，跳转到不同页面
     * @Param
     */
    private void payFine() {
        //获取当前登录人绑定的驾驶证信息
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_LICENSE, "user");
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                final TrafficLicenseUserBean jsonObj = MyApplication.gson.fromJson(string, TrafficLicenseUserBean.class);
                runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //已绑定驾驶证
                if (jsonObj.getData().size() > 0) {
                    Intent intent = new Intent(TrafficActivity.this, TrafficPayFineActivity.class);
                    startActivity(intent);
                    //未绑定驾驶证
                }else {
                    Intent intent = new Intent(TrafficActivity.this, BoundDriverLicenseActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
});


        }

    private void link(String name) {
        Intent intent;
        if (MyApplication.isLogin(TrafficActivity.this)){

        switch (name) {
            case "违章处理":
                intent = new Intent(TrafficActivity.this, TrafficIllegalProcessing1Activity.class);
                startActivity(intent);
                break;
            case "缴纳罚款":
                payFine();
                break;
            case "违章撤销":
                intent = new Intent(TrafficActivity.this, TrafficIllegalCancel1Activity.class);
                startActivity(intent);
                break;
            case "服务网点":
                intent = new Intent(TrafficActivity.this, TrafficServiceActivity.class);
                startActivity(intent);
                break;
        }
        }else {
            intent = new Intent(TrafficActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}