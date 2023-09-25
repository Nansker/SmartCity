package com.nansk.smartcity.activity.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.hospital.HospitalCategoryAdapter;
import com.nansk.smartcity.beans.hospital.HospitalCategoryBean;
import com.nansk.smartcity.beans.hospital.PatientListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 18:49
 * @Description 预约挂号
 */

public class HospitalRegActivity extends BaseActivity {
    private Gson gson;
    private PatientListBean.RowsBean patientObj;

    private RadioButton ordinaryRd;
    private RadioButton expertsRd;
    private TextView tipsTv;
    private RecyclerView bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_hospital_reg);
        setToolBarTitle("预约挂号");
        initView();
        fillData("2");
    }


    private void initView() {
        ordinaryRd = (RadioButton) findViewById(R.id.ordinary_rd);
        expertsRd = (RadioButton) findViewById(R.id.experts_rd);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);


        gson = new Gson();
        Intent intent = getIntent();
        patientObj = (PatientListBean.RowsBean) intent.getSerializableExtra("patientObj");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HospitalRegActivity.this);
        bodyContainer.setLayoutManager(linearLayoutManager);
        bodyContainer.addItemDecoration(new DividerItemDecoration(HospitalRegActivity.this, DividerItemDecoration.VERTICAL));

        ordinaryRd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillData("2");
            }
        });

        expertsRd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillData("1");
            }
        });
    }

    /**
     * @Create 2021/9/17 19:05
     * @Role 获取科室列表
     * @Param condition 2普通门诊，1专家门诊
     */
    private void fillData(String condition) {
        String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL_CATEGORY_LIST, "?type=" + condition);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HospitalCategoryBean jsonObj = gson.fromJson(response.body().string(), HospitalCategoryBean.class);
                if (jsonObj.getCode() == 200) {
                    List<HospitalCategoryBean.RowsBean> rows = jsonObj.getRows();
                    final HospitalCategoryAdapter adapter = new HospitalCategoryAdapter(HospitalRegActivity.this, rows, patientObj);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tipsTv.setVisibility(View.GONE);
                            bodyContainer.setAdapter(adapter);
                        }
                    });
                }

            }
        });
    }
}