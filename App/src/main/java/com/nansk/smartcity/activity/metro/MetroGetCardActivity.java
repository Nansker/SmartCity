package com.nansk.smartcity.activity.metro;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.metro.MetroGetCardBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 20:35
 * @description 乘车码领取页
 */

public class MetroGetCardActivity extends BaseActivity {

    private TextView userNameTv;
    private TextView idCardTv;
    private TextView telTv;
    private Button handleBtn;
    private TextView tipsTv;
    private TextView leftTitleBar;

    private MetroGetCardBean cardObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro_get_code);
        setToolBarTitle("乘车卡领取");
        setToolBarBackground("#ca062c");

        initView();
    }

    /**
     * @Create 2021/10/6 20:54
     * @Role 领取乘车卡
     * @Param
     */
    private void getCard() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_CARD, "");
        OkHttpUtil.doPost(url, MyApplication.getToken(this), cardObj, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            tipsTv.setVisibility(View.VISIBLE);
                            tipsTv.setText("乘车卡领取成功！");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 600);

                        } else {
                            tipsTv.setText(jsonObj.getMsg());
                        }
                    }
                });
            }
        });
    }



    private void initView() {

        userNameTv = (TextView) findViewById(R.id.userName_tv);
        idCardTv = (TextView) findViewById(R.id.idCard_tv);
        telTv = (TextView) findViewById(R.id.tel_tv);
        handleBtn = (Button) findViewById(R.id.handle_btn);
        tipsTv = (TextView) findViewById(R.id.tips_tv);


        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#ca062c"));
        gradientDrawable.setCornerRadius(10);
        handleBtn.setBackground(gradientDrawable);

        String userName = (String) SharedPreferencesUtils.getRecord(this,"userName","");
        String idCard = (String) SharedPreferencesUtils.getRecord(this,"idCard","");
        String phonenumber = (String) SharedPreferencesUtils.getRecord(this,"phonenumber","");

        cardObj = new MetroGetCardBean();
        cardObj.setUserName(userName);
        cardObj.setIdCard(idCard);
        cardObj.setPhonenumber(phonenumber);

        userNameTv.setText("用户名：" + userName);
        idCardTv.setText("身份证号：" + idCard.substring(0, 2) + "************" + idCard.substring(idCard.length()-4));
        telTv.setText("联系方式：" + phonenumber);

        handleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCard();
            }
        });

    }
}