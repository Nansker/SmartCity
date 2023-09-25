package com.nansk.smartcity.activity.job;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.job.JobCompanyBean;
import com.nansk.smartcity.beans.job.JobPostDetailsBean;
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
 * @Create 2021/09/30 22:21
 * @Description 岗位详情
 */

public class PositionDetailsActivity extends BaseActivity {
    private int postId;

    private TextView nameTv;
    private TextView salaryTv;
    private TextView addressTv;
    private TextView companyNameTv;
    private TextView contactsTv;
    private TextView needTv;
    private TextView obligationTv;
    private Button deliverBtn;
    private TextView introductoryTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_job_position_details);
        setToolBarTitle("职位详情");
        setToolBarBackground("#37c2bb");
        initView();
        initObject();
        fillData();
    }


    private void initView() {

        nameTv = (TextView) findViewById(R.id.name_tv);
        salaryTv = (TextView) findViewById(R.id.salary_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);
        companyNameTv = (TextView) findViewById(R.id.companyName_tv);
        contactsTv = (TextView) findViewById(R.id.contacts_tv);
        needTv = (TextView) findViewById(R.id.need_tv);
        obligationTv = (TextView) findViewById(R.id.obligation_tv);
        deliverBtn = (Button) findViewById(R.id.deliver_btn);
        introductoryTv = (TextView) findViewById(R.id.introductory_tv);
    }

    private void initObject() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(5);
        gradientDrawable.setColor(Color.parseColor("#37c2bb"));
        deliverBtn.setBackground(gradientDrawable);

        Intent intent = getIntent();
        postId = intent.getIntExtra("postId", 0);
    }

    /**
     * @Create 2021/9/30 23:21
     * @Role 获取数据
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.JOB_POST, postId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final JobPostDetailsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), JobPostDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            JobPostDetailsBean.DataBean data = jsonObj.getData();

                            /**
                             * @Create 2021/9/30 23:52
                             * @Role API有问题显示不了职位名称和公司名
                             * @Param
                             */
                            nameTv.setText(data.getName());

                            salaryTv.setText(data.getSalary()+"k");
                            addressTv.setText(data.getAddress());
                            companyNameTv.setText(data.getCompanyName());
                            contactsTv.setText(data.getContacts());
                            needTv.setText(data.getNeed());
                            obligationTv.setText(data.getObligation());

                            //根据公司id查找公司简介
                            getCompanyDetails(data.getCompanyId());
                        }else {
                            Toast.makeText(PositionDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    private void getCompanyDetails(int id){
        String url = ConnectInfo.getUrl(ConnectInfo.JOB_COMPANY, id);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final JobCompanyBean jsonObj = MyApplication.gson.fromJson(response.body().string(), JobCompanyBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            JobCompanyBean.DataBean data = jsonObj.getData();
                            companyNameTv.setText(data.getCompanyName());
                            introductoryTv.setText(data.getIntroductory());

                        }else {
                            Toast.makeText(PositionDetailsActivity.this, jsonObj.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

}