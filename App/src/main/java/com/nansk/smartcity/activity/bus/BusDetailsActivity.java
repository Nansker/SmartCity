package com.nansk.smartcity.activity.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.bus.BusStopsAdapter;
import com.nansk.smartcity.beans.bus.BusLinesBean;
import com.nansk.smartcity.beans.bus.BusStopBean;
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
 * @Create 2021/09/16 17:10
 * @Description 巴士详情页
 */

public class BusDetailsActivity extends BaseActivity implements View.OnClickListener {
    private BusLinesBean.RowsBean busObj;

    private TextView startTv;
    private TextView endTv;
    private TextView priceTv;
    private TextView mileageTv;
    private Button nextBtn;

    private Gson gson;
    private RecyclerView stopContainer;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_bus_details);
        setToolBarTitle("巴士详情");

        Intent intent = getIntent();
        busObj = (BusLinesBean.RowsBean) intent.getSerializableExtra("busObj");

        gson = new Gson();

        initView();

        setBusStation();
    }


    private void initView() {
        startTv = (TextView) findViewById(R.id.start_tv);
        endTv = (TextView) findViewById(R.id.end_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);
        mileageTv = (TextView) findViewById(R.id.mileage_tv);
        nextBtn = (Button) findViewById(R.id.next_btn);
        backBtn = (Button) findViewById(R.id.back_btn);

        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startTv.setText(busObj.getFirst());
                endTv.setText(busObj.getEnd());
                priceTv.setText("￥" + busObj.getPrice().toString());
                mileageTv.setText("全程 " + busObj.getMileage() + "km");
            }
        });

        stopContainer = (RecyclerView) findViewById(R.id.stop_container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BusDetailsActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        stopContainer.setLayoutManager(linearLayoutManager);

    }

    /**
     * @Create 2021/9/16 20:03
     * @Role 获取公交站点
     * @Param
     */
    private void setBusStation() {
        String stopUrl = ConnectInfo.getUrl(ConnectInfo.BUS_STOP_LIST, "?linesId=" + busObj.getId());
        OkHttpUtil.doGet(stopUrl, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                BusStopBean busStopBean = gson.fromJson(response.body().string(), BusStopBean.class);
                final List<BusStopBean.RowsBean> stopBeanRows = busStopBean.getRows();

                //设置适配器
                final BusStopsAdapter adapter = new BusStopsAdapter(BusDetailsActivity.this, stopBeanRows);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (stopBeanRows.size() > 0) {
                            stopContainer.setAdapter(adapter);
                        }
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.next_btn:
                Intent intent = new Intent(BusDetailsActivity.this, BusChangeDateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("busObj", busObj);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}