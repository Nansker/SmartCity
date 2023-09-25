package com.nansk.smartcity.activity.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.takeout.TakeoutOrderDetailsBean;
import com.nansk.smartcity.beans.takeout.TakeoutPlayOrder;
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
 * @Create 2021/09/23 17:01
 * @Description
 */

public class TakeoutPlayActivity extends BaseActivity implements View.OnClickListener {

    private TextView priceTv;
    private TextView sellerNameTv;
    private LinearLayout dianziPlay;
    private LinearLayout weixinPlay;
    private LinearLayout zfbPlay;
    private RadioButton dianziRd;
    private RadioButton weixinRd;
    private RadioButton zfbRd;
    private Button submitBtn;
    private TextView orderNumTv;

    private String token;

    //订单号
    private String orderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_play);
        setToolBarBackground("#FFC107", 0);
        setToolBarTitle("支付订单");
        initView();
        fillData();
    }

    private void initView() {

        orderNumTv = (TextView) findViewById(R.id.orderNum_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);
        sellerNameTv = (TextView) findViewById(R.id.sellerName_tv);

        dianziPlay = (LinearLayout) findViewById(R.id.dianzi_play);
        weixinPlay = (LinearLayout) findViewById(R.id.weixin_play);
        zfbPlay = (LinearLayout) findViewById(R.id.zfb_play);

        dianziRd = (RadioButton) findViewById(R.id.dianzi_rd);
        weixinRd = (RadioButton) findViewById(R.id.weixin_rd);
        zfbRd = (RadioButton) findViewById(R.id.zfb_rd);

        submitBtn = (Button) findViewById(R.id.submit_btn);


        dianziPlay.setOnClickListener(this);
        zfbPlay.setOnClickListener(this);
        weixinPlay.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        Intent intent = getIntent();
        orderNo = intent.getStringExtra("orderNo");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                orderNumTv.setText(orderNo);

            }
        });

        token = MyApplication.getToken(this);

    }

    /**
     * @Create 2021/9/24 14:17
     * @Role 查询订单详情
     * @Param
     */
    private void fillData() {
        if (orderNo != null) {
            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ORDER_DETAILS, orderNo);
            OkHttpUtil.doGet(url, token, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    TakeoutOrderDetailsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TakeoutOrderDetailsBean.class);
                    if (jsonObj.getCode() == 200) {
                        final TakeoutOrderDetailsBean.DataBean data = jsonObj.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                priceTv.setText(data.getOrderInfo().getAmount().toString());
                                sellerNameTv.setText(data.getSellerInfo().getName());
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * @Create 2021/9/23 20:28
     * @Role 提交订单
     * @Param
     */
    private void submitOrder() {
        String paymentType = null;

        if (dianziRd.isChecked()) {
            paymentType = "电子支付";
        } else if (weixinRd.isChecked()) {
            paymentType = "微信支付";
        } else if (zfbRd.isChecked()) {
            paymentType = "支付宝支付";
        }

        if (paymentType != null) {
            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ORDER_PAY, "");

            TakeoutPlayOrder playOrder = new TakeoutPlayOrder();
            playOrder.setOrderNo(orderNo);
            playOrder.setPaymentType(paymentType);

            OkHttpUtil.doPost(url, token, playOrder, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (json.getCode() == 200) {
                                Toast.makeText(TakeoutPlayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                //延时跳转到订单详情页
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(TakeoutPlayActivity.this, TakeoutOrderDetailsActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("orderNo", orderNo);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 1000);

                            } else {
                                Toast.makeText(TakeoutPlayActivity.this, "系统发生异常！\n" + json.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            });

        } else {
            Toast.makeText(this, "请选择支付方式！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bar:
                finish();
                break;
            case R.id.dianzi_play:
                dianziRd.setChecked(true);
                break;
            case R.id.weixin_play:
                weixinRd.setChecked(true);
                break;
            case R.id.zfb_play:
                zfbRd.setChecked(true);
                break;
            case R.id.submit_btn:
                submitOrder();
                break;
        }
    }

}