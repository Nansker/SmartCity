package com.nansk.smartcity.activity.metro;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroStatementBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 20:10
 * @description 乘车码领取条款
 */

public class MetroGetTermsActivity extends BaseActivity {

    private Button agreeBtn;
    private Button noBtn;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro_card_clause);
        setToolBarTitle("乘车卡领取条款");
        setToolBarBackground("#ca062c");
        initView();
        getStatement(2);
    }

    /**
     * @Create 2021/10/11 15:10
     * @Role 获取乘车公告
     * @Param
     */
    private void getStatement(int type) {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_STATEMENT, "?type="+type);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroStatementBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroStatementBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            MetroStatementBean.DataBean data = jsonObj.getData();
                            setToolBarTitle(SystemUtils.getValue(data.getTitle(), ""));
                            try {
                                webView.loadData(URLEncoder.encode(data.getContent(), "utf-8"), "text/html", "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(MetroGetTermsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        agreeBtn = (Button) findViewById(R.id.agree_btn);
        noBtn = (Button) findViewById(R.id.no_btn);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#ca062c"));
        gradientDrawable.setCornerRadius(10);
        agreeBtn.setBackground(gradientDrawable);

        GradientDrawable gradientDrawable1 = new GradientDrawable();
        gradientDrawable1.setColor(Color.parseColor("#e6e6e6"));
        gradientDrawable1.setCornerRadius(10);
        noBtn.setBackground(gradientDrawable1);

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetroGetTermsActivity.this, MetroGetCardActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}