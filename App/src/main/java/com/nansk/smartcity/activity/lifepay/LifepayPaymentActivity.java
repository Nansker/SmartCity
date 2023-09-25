package com.nansk.smartcity.activity.lifepay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.lifepay.LifePayBillBean;
import com.nansk.smartcity.beans.lifepay.LifepayPaymentBean;
import com.nansk.smartcity.dialog.PaymentTypeChangeDialog;
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
 * @Create 2021/09/28 16:21
 * @Description 账单支付页
 */

public class LifepayPaymentActivity extends BaseActivity implements View.OnClickListener {
    private LifePayBillBean.DataBean billObj;


    private TextView titleTv;
    private TextView billNoTv;
    private TextView chargeUnitTv;
    private TextView paymentNoTv;
    private TextView addressTv;
    private TextView amountTv;
    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_lifepay_payment);
        setToolBarTitle("缴费");

        Intent intent = getIntent();
        billObj = (LifePayBillBean.DataBean) intent.getSerializableExtra("billObj");


        initView();
        fillData();

        setToolBarRightButton(true, "缴费历史", new MyCallBack() {
            @Override
            public void CallBack() {
                Intent intent = new Intent(LifepayPaymentActivity.this, LifepayHistoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", billObj.getCategoryId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initView() {


        titleTv = (TextView) findViewById(R.id.title_tv);
        billNoTv = (TextView) findViewById(R.id.billNo_tv);
        chargeUnitTv = (TextView) findViewById(R.id.chargeUnit_tv);
        paymentNoTv = (TextView) findViewById(R.id.paymentNo_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);
        amountTv = (TextView) findViewById(R.id.amount_tv);
        payBtn = (Button) findViewById(R.id.pay_btn);

        payBtn.setOnClickListener(this);

    }

    /**
     * @Create 2021/9/28 17:39
     * @Role 获取缴费数据
     * @Param
     */
    private void fillData() {
        if (billObj != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    titleTv.setText(billObj.getTitle());
                    billNoTv.setText(billObj.getBillNo());
                    chargeUnitTv.setText(billObj.getChargeUnit());
                    paymentNoTv.setText(billObj.getPaymentNo());
                    addressTv.setText(billObj.getAddress());
                    amountTv.setText(billObj.getAmount().toString() + "元");
                }
            });
        } else {
            Toast.makeText(this, "获取缴费数据失败！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Create 2021/9/28 21:19
     * @Role 支付
     * @Param
     */
    private void payment() {
        final PaymentTypeChangeDialog dialog = new PaymentTypeChangeDialog();
        dialog.DialogCallBack(new PaymentTypeChangeDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(String paymentType) {
                final LifepayPaymentBean paymentBean = new LifepayPaymentBean();
                paymentBean.setBillNo(billObj.getBillNo());
                paymentBean.setPaymentNo(billObj.getPaymentNo());
                paymentBean.setPaymentType(paymentType);

                String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_RECHARGE, "");
                dialog.dismiss();

                payBtn.setText("正在支付...");

                OkHttpUtil.doPost(url, MyApplication.getToken(LifepayPaymentActivity.this), paymentBean, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (jsonObj.getCode() == 200){
                                    Toast.makeText(LifepayPaymentActivity.this,"支付成功！",Toast.LENGTH_SHORT).show();
                                    payBtn.setVisibility(View.GONE);
                                    //延时跳转页面
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(LifepayPaymentActivity.this, LifepayHistoryActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("categoryId",billObj.getCategoryId());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }, 1000);
                                }else {
                                    payBtn.setText("重新支付");
                                    Toast.makeText(LifepayPaymentActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
            }
        });
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_btn:
                payment();
                break;
        }
    }


}