package com.nansk.smartcity.activity.hospital;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.MainActivity;
import com.nansk.smartcity.adapter.hospital.HospitalHistoryAdapter;
import com.nansk.smartcity.beans.hospital.HospitalOrdersBean;
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
 * @Create 2021/09/17 20:50
 * @Description
 */

public class HospitalHistoryActivity extends BaseActivity {

    private TextView tipsTv;
    private RecyclerView bodyContainer;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_hospital_history);
        setToolBarTitle("历史订单");
        initView();
        fillData();
    }


    private void initView() {
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        backBtn = (Button) findViewById(R.id.back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalHistoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HospitalHistoryActivity.this);
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
    }

    /**
     * @Create 2021/9/17 21:02
     * @Role 填充数据
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL_RESERVE_LIST, "");
        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HospitalOrdersBean ordersBean = MyApplication.gson.fromJson(response.body().string(), HospitalOrdersBean.class);
                if (ordersBean.getCode() == 200){
                    final List<HospitalOrdersBean.RowsBean> rows = ordersBean.getRows();
                    if (rows.size()>0){
                        final HospitalHistoryAdapter adapter = new HospitalHistoryAdapter(HospitalHistoryActivity.this, rows);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tipsTv.setVisibility(View.GONE);
                                bodyContainer.setAdapter(adapter);
                            }
                        });

                    }
                }
            }
        });
    }

}