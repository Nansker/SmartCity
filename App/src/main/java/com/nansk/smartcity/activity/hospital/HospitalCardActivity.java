package com.nansk.smartcity.activity.hospital;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.hospital.HospitalCardListAdapter;
import com.nansk.smartcity.beans.hospital.PatientListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 14:43
 * @Description
 */

public class HospitalCardActivity extends BaseActivity {

    private RecyclerView bodyContainer;
    private LinearLayout addClientLayout;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_hospital_card);
        setToolBarTitle("就诊人卡片");
        initView();

        fillData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fillData();
    }

    private void initView() {
        tipsTv = findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        addClientLayout = (LinearLayout) findViewById(R.id.addClient_layout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HospitalCardActivity.this);
        bodyContainer.setLayoutManager(linearLayoutManager);

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
                outRect.bottom = 20;
                outRect.left = 40;
                outRect.right = 40;
            }
        });

        //添加就诊人
        addClientLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalCardActivity.this, HospitalAddPatientActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * @Create 2021/9/17 15:09
     * @Role 查询当前用户下的就诊人卡片
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL_PATIENT, "");

        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PatientListBean patientListBean = MyApplication.gson.fromJson(response.body().string(), PatientListBean.class);
                if (patientListBean.getCode() == 200) {
                    List<PatientListBean.RowsBean> rows = patientListBean.getRows();
                    if (rows.size() > 0) {
                        final HospitalCardListAdapter adapter = new HospitalCardListAdapter(HospitalCardActivity.this, rows);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tipsTv.setVisibility(View.GONE);
                                bodyContainer.setAdapter(adapter);
                            }
                        });
                    }
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HospitalCardActivity.this, patientListBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}