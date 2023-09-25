package com.nansk.smartcity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.AllOrderAdapter;
import com.nansk.smartcity.beans.AllOrdersBean;
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
 * @Create 2021/09/15 20:57
 * @Description 用户全部订单
 */

public class AllOrdersActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton didnNtpayBtn;
    private RadioButton haveTopay;
    private RecyclerView bodyContainer;


    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_all_orders);
        setToolBarTitle("全部订单");
        initView();

        getOrder("已付款");

    }

    private void initView() {
        didnNtpayBtn = (RadioButton) findViewById(R.id.didnNtpay_btn);
        haveTopay = (RadioButton) findViewById(R.id.haveTopay);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        tipsTv = (TextView) findViewById(R.id.tips_tv);

        didnNtpayBtn.setOnClickListener(this);
        haveTopay.setOnClickListener(this);

        bodyContainer.setLayoutManager(new LinearLayoutManager(AllOrdersActivity.this));


    }

    /**
     * @Create 2021/9/15 21:42
     * @Role 获取订单
     * @Param
     */
    private void getOrder(String status) {
        String url = ConnectInfo.getUrl(ConnectInfo.USER_ALL_ORDER, "?orderStatus=" + status);

        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final AllOrdersBean jsonObj = MyApplication.gson.fromJson(response.body().string(), AllOrdersBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            final List<AllOrdersBean.RowsBean> rows = jsonObj.getRows();
                            final AllOrderAdapter adapter = new AllOrderAdapter(AllOrdersActivity.this, rows);
                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                bodyContainer.setAdapter(adapter);
                            }
                        } else {
                            Toast.makeText(AllOrdersActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //未支付
            case R.id.didnNtpay_btn:
                getOrder("未付款");
                break;
            //已支付
            case R.id.haveTopay:
                getOrder("已付款");
                break;
        }
    }
}