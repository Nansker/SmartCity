package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.traffic.TrafficCarListAdapter;
import com.nansk.smartcity.beans.traffic.TrafficCarListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 17:49
 * @description 违法处理
 */

public class TrafficIllegalProcessing1Activity extends BaseActivity {

    private TextView tipsTv;
    private RecyclerView bodyContainer;
    private Button addCarBtn;

    private String token;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_illegal_processing1);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("违章处理");

        initView();
        initObject();
        fillData();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fillData();
    }

    private void initView() {

        tipsTv = findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        addCarBtn = (Button) findViewById(R.id.addCar_btn);
    }

    private void initObject() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setColor(Color.parseColor("#2c65a8"));
        addCarBtn.setBackground(gradientDrawable);

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrafficIllegalProcessing1Activity.this, TrafficAddCardActivity.class);
                startActivity(intent);
            }
        });

        token = MyApplication.getToken(this);
        gson = MyApplication.gson;

        bodyContainer.setLayoutManager(new LinearLayoutManager(TrafficIllegalProcessing1Activity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
                outRect.bottom = 15;

            }
        });
    }

    /**
     * @Create 2021/10/4 18:23
     * @Role 获取已绑定的机动车列表
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_CAR, "list");
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficCarListBean jsonObj = gson.fromJson(response.body().string(), TrafficCarListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            if (jsonObj.getTotal()>0){
                                tipsTv.setVisibility(View.GONE);
                                List<TrafficCarListBean.RowsBean> rows = jsonObj.getRows();
                                TrafficCarListAdapter adapter = new TrafficCarListAdapter(TrafficIllegalProcessing1Activity.this, rows);
                                bodyContainer.setAdapter(adapter);
                                adapter.OnItemCallBack(new TrafficCarListAdapter.OnItemCallBack() {
                                    @Override
                                    public void OnItemCallBack(int position, TrafficCarListBean.RowsBean obj) {
                                        Intent intent = new Intent(TrafficIllegalProcessing1Activity.this, TrafficIllegalProcessing2Activity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("plateNo", obj.getPlateNo());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }else {
                                tipsTv.setText("暂无绑定的车辆");
                            }
                        }else {
                            Toast.makeText(TrafficIllegalProcessing1Activity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}