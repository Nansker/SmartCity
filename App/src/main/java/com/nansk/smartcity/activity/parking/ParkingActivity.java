package com.nansk.smartcity.activity.parking;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.parking.ParkLotListAdapter;
import com.nansk.smartcity.beans.park.ParkLotListBean;
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
 * @Create 2021/09/18 10:11
 * @Description 停车场
 */

public class ParkingActivity extends BaseActivity implements View.OnClickListener {

    private TextView tipsTv;
    private RecyclerView bodyContainer;
    private Button recordBtn;
    private Button moreBtn;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_parking);
        setToolBarTitle("停车场查询");

        initView();

//        默认显示６条数据
        fillData("?pageNum=1&pageSize=6");

    }

    private void initView() {

        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        recordBtn = (Button) findViewById(R.id.record_btn);
        moreBtn = (Button) findViewById(R.id.more_btn);

        recordBtn.setOnClickListener(this);
        moreBtn.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ParkingActivity.this);
        bodyContainer.setLayoutManager(linearLayoutManager);

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
                outRect.bottom = 20;
                outRect.right = 40;
                outRect.left = 40;
            }
        });

        gson = new Gson();

    }

    /**
     * @Create 2021/9/18 11:06
     * @Role 获取停车场数据
     * @Param
     */
    private void fillData(String condition) {
        String url = ConnectInfo.getUrl(ConnectInfo.PARK_LOT_LIST,condition);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ParkLotListBean jsonObj = gson.fromJson(response.body().string(), ParkLotListBean.class);
                if (jsonObj.getCode() == 200) {
                    List<ParkLotListBean.RowsBean> rows = jsonObj.getRows();
                    if (rows.size() > 0) {
                        final ParkLotListAdapter adapter = new ParkLotListAdapter(ParkingActivity.this, rows);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bar:
                finish();
                break;
            case R.id.record_btn:
                Intent intent = new Intent(ParkingActivity.this, ParkLotRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.more_btn:
                fillData("");
                break;
        }
    }
}