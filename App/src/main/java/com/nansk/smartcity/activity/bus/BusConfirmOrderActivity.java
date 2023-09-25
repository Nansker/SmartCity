package com.nansk.smartcity.activity.bus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.bus.BusCustomInfoBean;
import com.nansk.smartcity.beans.bus.BusSubmitOrdersBean;
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
 * @Create 2021/09/16 21:33
 * @Description 智慧巴士确订单
 */

public class BusConfirmOrderActivity extends BaseActivity {
    private BusCustomInfoBean customInfoBean;
    private TextView pathTv;
    private TextView dateTv;
    private TextView nickNameTv;
    private TextView phoneTv;
    private TextView startTv;
    private TextView endTv;
    private TextView priceTv;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_bus_confirm_order);
        setToolBarTitle("确认订单");
        Intent intent = getIntent();

        customInfoBean = (BusCustomInfoBean) intent.getSerializableExtra("ordersObj");

        initView();
    }

    private void initView() {
        pathTv = (TextView) findViewById(R.id.path_tv);
        dateTv = (TextView) findViewById(R.id.date_tv);
        nickNameTv = (TextView) findViewById(R.id.nickName_tv);
        phoneTv = (TextView) findViewById(R.id.phone_tv);
        startTv = (TextView) findViewById(R.id.start_tv);
        endTv = (TextView) findViewById(R.id.end_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);
        submitBtn = (Button) findViewById(R.id.submit_btn);


        fillData();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrders();
            }
        });
    }


    /**
     * @Create 2021/9/17 9:31
     * @Role 填充订单信息
     * @Param
     */
    private void fillData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pathTv.setText(customInfoBean.getPath());
                dateTv.setText(customInfoBean.getDate());
                nickNameTv.setText(customInfoBean.getNickName());
                phoneTv.setText(customInfoBean.getPhone());
                startTv.setText(customInfoBean.getStart());
                endTv.setText(customInfoBean.getEnd());
                priceTv.setText(customInfoBean.getPrice());
            }
        });
    }

    /**
     * @Create 2021/9/17 9:43
     * @Role 提交订单
     * @Param
     */
    private void submitOrders() {
        String url = ConnectInfo.getUrl(ConnectInfo.BUS_ORDERS, "");

        BusSubmitOrdersBean ordersBean = new BusSubmitOrdersBean();
        ordersBean.setStart(customInfoBean.getStart());
        ordersBean.setEnd(customInfoBean.getEnd());
        ordersBean.setPath(customInfoBean.getPath());
        ordersBean.setPrice(customInfoBean.getPrice());
        ordersBean.setStatus(0);

        OkHttpUtil.doPost(url, MyApplication.getToken(this), ordersBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(json.getMsg(),1200);
                        if (json.getCode() == 200){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 1200);
                        }
                    }
                });
            }
        });
    }

}