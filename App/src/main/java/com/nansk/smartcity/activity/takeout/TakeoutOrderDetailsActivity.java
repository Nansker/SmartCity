package com.nansk.smartcity.activity.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.takeout.TakeoutOrderDetailsProductAdapter;
import com.nansk.smartcity.beans.takeout.TakeoutOrderDetailsBean;
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
 * @Create 2021/09/24 12:31
 * @Description 订单详情页
 */

public class TakeoutOrderDetailsActivity extends BaseActivity {

    private TextView sellerNameTv;
    private RecyclerView bodyContainer;
    private TextView amountTv;
    private TextView addressTv;
    private TextView userNameTv;
    private TextView phoneTv;
    private TextView orderNoTv;
    private TextView payTimeTv;
    private TextView paymentTypeTv;

    private String orderNo;
    private TextView statusTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_order_details);
        setToolBarBackground("#FFC107",0);
        setToolBarTitle("订单详情");
        initView();

        fillData();
    }


    private void initView() {

        statusTv = (TextView) findViewById(R.id.status_tv);
        sellerNameTv = (TextView) findViewById(R.id.sellerName_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        amountTv = (TextView) findViewById(R.id.amount_tv);

        addressTv = (TextView) findViewById(R.id.address_tv);
        userNameTv = (TextView) findViewById(R.id.userName_tv);
        phoneTv = (TextView) findViewById(R.id.phone_tv);

        orderNoTv = (TextView) findViewById(R.id.orderNo_tv);
        payTimeTv = (TextView) findViewById(R.id.payTime_tv);
        paymentTypeTv = (TextView) findViewById(R.id.paymentType_tv);


        bodyContainer.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        this.orderNo = intent.getStringExtra("orderNo");

    }


    /**
     * @Create 2021/9/24 14:17
     * @Role 查询订单详情
     * @Param
     */
    private void fillData() {
        if (orderNo != null) {
            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ORDER_DETAILS, orderNo);
            OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    TakeoutOrderDetailsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TakeoutOrderDetailsBean.class);
                    if (jsonObj.getCode() == 200) {
                        TakeoutOrderDetailsBean.DataBean data = jsonObj.getData();
                        setData(data);
                    }
                }
            });
        }
    }

    /**
     * @Create 2021/9/24 14:26
     * @Role 设置数据
     * @Param
     */
    private void setData(TakeoutOrderDetailsBean.DataBean data) {
        final TakeoutOrderDetailsBean.DataBean.SellerInfoBean sellerInfo = data.getSellerInfo();
        final TakeoutOrderDetailsBean.DataBean.OrderInfoBean orderInfo = data.getOrderInfo();

        final TakeoutOrderDetailsProductAdapter adapter = new TakeoutOrderDetailsProductAdapter(this, orderInfo.orderItemList);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusTv.setText(orderInfo.getStatus());
                sellerNameTv.setText(sellerInfo.getName());
                amountTv.setText("￥" + orderInfo.getAmount());

                addressTv.setText(orderInfo.getReceiverAddress());
                userNameTv.setText(orderInfo.getReceiverName());
                phoneTv.setText(orderInfo.getReceiverPhone());

                orderNoTv.setText(orderInfo.getOrderNo());
                payTimeTv.setText(orderInfo.getPayTime());
                paymentTypeTv.setText(orderInfo.getPaymentType());

                bodyContainer.setAdapter(adapter);
            }
        });


    }


}