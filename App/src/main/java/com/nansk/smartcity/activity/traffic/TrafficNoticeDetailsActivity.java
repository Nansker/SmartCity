package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.traffic.TrafficNoticeDetailsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/07 14:01
 * @description 处罚判决书详情页
 */

public class TrafficNoticeDetailsActivity extends BaseActivity {

    private TextView codeTv;
    private TextView userNameTv;
    private TextView fileNoTv;
    private TextView licenseNoTv;
    private TextView licenseLevelTv;
    private TextView contactTv;
    private TextView plateNoTv;
    private TextView carTypeTv;
    private TextView auditOfficeTv;
    private TextView illegalDateTv;
    private TextView illegalEvenTv;
    private TextView illegalAddressTv;
    private TextView moneyTv;
    private TextView deductMarks;
    private TextView performOfficeTv;
    private TextView performDateTv;

    private int id;
    private String token;
    private Button paymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_notice_details);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("判决书详情页");
        initView();
        fillData();
    }

    /**
     * @Create 2021/10/7 15:00
     * @Role 生成处罚判决书
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_NOTICE, id);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficNoticeDetailsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TrafficNoticeDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            final TrafficNoticeDetailsBean.DataBean data = jsonObj.getData();

                            codeTv.setText(getValue(data.getCode()));
                            userNameTv.setText(getValue(data.getUserName()));
                            fileNoTv.setText(getValue(data.getFileNo()));
                            licenseNoTv.setText(getValue(data.getLicenseNo()));
                            licenseLevelTv.setText(getValue(data.getLicenseLevel()));
                            contactTv.setText(getValue(data.getContact()));
                            plateNoTv.setText(getValue(data.getPlateNo()));
                            carTypeTv.setText(getValue(data.getCarType()));
                            auditOfficeTv.setText(getValue(data.getAuditOffice()));
                            illegalDateTv.setText(getValue(data.getIllegalDate()));
                            illegalEvenTv.setText(getValue(data.getIllegalEven()));
                            illegalAddressTv.setText(getValue(data.getIllegalAddress()));
                            moneyTv.setText(getValue(Integer.toString(data.getMoney())));
                            deductMarks.setText(getValue(Integer.toString(data.getDeductMarks())));
                            performOfficeTv.setText(getValue(data.getPerformOffice()));
                            performDateTv.setText(getValue(data.getPerformDate()));

                            paymentBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    payFine(data.getIllegalId());
                                }
                            });

                        } else {
                            Toast.makeText(TrafficNoticeDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    /**
     * @Create 2021/10/7 16:18
     * @Role 缴款
     * @Param
     */
    private void payFine(int illegalId) {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "pay/" + illegalId);
        OkHttpUtil.doPost(url, token, "", new Callback() {
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
                            fillData();
                            Toast.makeText(TrafficNoticeDetailsActivity.this, "缴款成功！", Toast.LENGTH_SHORT).show();
                            paymentBtn.setText("已缴款");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 600);
                            finish();
                        } else {
                            Toast.makeText(TrafficNoticeDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    private String getValue(String value) {
        if (value != null && value != "") {
            return value;
        }
        return "暂无数据";
    }

    ;

    private void initView() {

        paymentBtn = (Button) findViewById(R.id.payment_btn);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setColor(Color.parseColor("#2c65a8"));
        paymentBtn.setBackground(gradientDrawable);

        codeTv = (TextView) findViewById(R.id.code_tv);
        userNameTv = (TextView) findViewById(R.id.userName_tv);
        fileNoTv = (TextView) findViewById(R.id.fileNo_tv);
        licenseNoTv = (TextView) findViewById(R.id.licenseNo_tv);
        licenseLevelTv = (TextView) findViewById(R.id.licenseLevel_tv);
        contactTv = (TextView) findViewById(R.id.contact_tv);
        plateNoTv = (TextView) findViewById(R.id.plateNo_tv);
        carTypeTv = (TextView) findViewById(R.id.carType_tv);
        auditOfficeTv = (TextView) findViewById(R.id.auditOffice_tv);
        illegalDateTv = (TextView) findViewById(R.id.illegalDate_tv);
        illegalEvenTv = (TextView) findViewById(R.id.illegalEven_tv);
        illegalAddressTv = (TextView) findViewById(R.id.illegalAddress_tv);
        moneyTv = (TextView) findViewById(R.id.money_tv);
        deductMarks = (TextView) findViewById(R.id.deductMarks);
        performOfficeTv = (TextView) findViewById(R.id.performOffice_tv);
        performDateTv = (TextView) findViewById(R.id.performDate_tv);

        token = MyApplication.getToken(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("noticeId", 0);

    }
}