package com.nansk.smartcity.activity.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.hospital.HospitalBannerBean;
import com.nansk.smartcity.beans.hospital.HospitalDetailsBean;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.youth.banner.Banner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 11:19
 * @Description 医院详情
 */

public class HospitalDetailsActivity extends BaseActivity {
    private int hospitalId;
    private Banner banner;
    private Button orderBtn;
    private Button orderHistoryBtn;

    private Gson gson;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_hospital_details);
        setToolBarTitle("医院详情");

        hospitalId = getIntent().getIntExtra("hospitalId",0);

        initView();
        getHospitalInfo();
        initBanner();
    }

    /**
     * @Create 2021/10/21 15:53
     * @Role
     * @Param
     */
    private void getHospitalInfo() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL, hospitalId);
        OkHttpUtil.doGet(url,"", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final HospitalDetailsBean json = MyApplication.gson.fromJson(response.body().string(), HospitalDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200){
                            try {
                                if (json.getData() != null){
                                    webView.loadData(URLEncoder.encode(json.getData().getBrief(), "utf-8"), "text/html", "utf-8");
                                }else {
                                    webView.loadData(URLEncoder.encode("暂无更多信息", "utf-8"), "text/html", "utf-8");
                                }

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        orderBtn = (Button) findViewById(R.id.order_btn);
        orderHistoryBtn = (Button) findViewById(R.id.orderHistory_btn);
        webView = (WebView) findViewById(R.id.webView);

        gson = MyApplication.gson;

        BannerSetUtils.setBannerStyle(HospitalDetailsActivity.this, banner);

        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalDetailsActivity.this, HospitalCardActivity.class);
                startActivity(intent);
            }
        });

        orderHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalDetailsActivity.this, HospitalHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @Create 2021/9/17 14:16
     * @Role 设置轮播图
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL_BANNER, "?hospitalId=" + hospitalId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final HospitalBannerBean bannerBean = gson.fromJson(response.body().string(), HospitalBannerBean.class);
                List<HospitalBannerBean.DataBean> data = bannerBean.getData();
                final List<String> imgUrls = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    imgUrls.add(MyApplication.IP + data.get(i).getImgUrl());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(imgUrls);
                        banner.start();
                    }
                });

            }
        });
    }

}