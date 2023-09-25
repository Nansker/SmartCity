package com.nansk.smartcity.activity.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.takeout.TakeoutProductOrderAdapter;
import com.nansk.smartcity.beans.takeout.TakeoutAddressListBean;
import com.nansk.smartcity.beans.takeout.TakeoutCreateOrderBean;
import com.nansk.smartcity.beans.takeout.TakeoutOrderObj;
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
 * @Create 2021/09/23 09:37
 * @Description 外卖订单确认页
 */

public class TakeoutOrderConfirmActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout addressInfoBox;
    private TextView addressTv;
    private TextView nameTv;
    private TextView phoneTv;
    private TextView sellerNameTv;
    private TextView priceTv;
    private TextView numberTv;
    private Button submitBtn;

    private TakeoutOrderObj orderObj;
    private RecyclerView bodyContainer;

    private TakeoutAddressListBean.DataBean changeAddress;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_order_confirm);
        setToolBarBackground("#FFC107", 0);
        setToolBarTitle("提交订单");

        initView();
    }

    private void initView() {

        addressInfoBox = (LinearLayout) findViewById(R.id.addressInfo_box);
        addressTv = (TextView) findViewById(R.id.address_tv);
        nameTv = (TextView) findViewById(R.id.name_tv);
        phoneTv = (TextView) findViewById(R.id.phone_tv);
        sellerNameTv = (TextView) findViewById(R.id.sellerName_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);
        numberTv = (TextView) findViewById(R.id.number_tv);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        addressInfoBox.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        bodyContainer.setLayoutManager(new LinearLayoutManager(TakeoutOrderConfirmActivity.this));

        Intent intent = getIntent();
        orderObj = (TakeoutOrderObj) intent.getSerializableExtra("orderObj");

        token = MyApplication.getToken(this);

        fillData();

    }

    /**
     * @Create 2021/9/23 11:05
     * @Role 填充数据
     * @Param
     */
    private void fillData() {
        sellerNameTv.setText(orderObj.getSellerName());
        priceTv.setText(orderObj.getAmount().toString());
        numberTv.setText(orderObj.getNumber() + "");
        TakeoutProductOrderAdapter adapter = new TakeoutProductOrderAdapter(TakeoutOrderConfirmActivity.this, orderObj.getOrderItemList());
        bodyContainer.setAdapter(adapter);
    }

    /**
     * @Create 2021/9/23 16:09
     * @Role 选择收货地址
     * @Param
     */
    private void changeAddress() {
        final TakeoutChangeAddressDialog dialog = new TakeoutChangeAddressDialog();
        dialog.show(getSupportFragmentManager(), "dialog");
        dialog.DialogCallBack(new TakeoutChangeAddressDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(TakeoutAddressListBean.DataBean obj) {
                changeAddress = obj;
                addressTv.setText(obj.getAddressDetail());
                nameTv.setText(obj.getName());
                phoneTv.setText(obj.getPhone());
                dialog.dismiss();
            }
        });
    }

    /**
     * @Create 2021/9/23 16:44
     * @Role 提交订单
     * @Param
     */
    private void submitOrder() {
        int sellerId = orderObj.getSellerId();

        if (changeAddress != null) {

            TakeoutOrderObj takeoutOrderObj = new TakeoutOrderObj();
            takeoutOrderObj.setAddressDetail(changeAddress.getAddressDetail());
            takeoutOrderObj.setLabel(changeAddress.getLabel());
            takeoutOrderObj.setPhone(changeAddress.getPhone());
            takeoutOrderObj.setName(changeAddress.getName());
            takeoutOrderObj.setAmount(orderObj.getAmount());
            takeoutOrderObj.setSellerId(sellerId);
            takeoutOrderObj.setSellerName(orderObj.getSellerName());

            takeoutOrderObj.setOrderItemList(orderObj.getOrderItemList());

            //创建订单，得到订单号
            createOrder(takeoutOrderObj);

        } else {
            Toast.makeText(this, "请选择收货地址！", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * @Create 2021/9/23 20:42
     * @Role 创建订单
     * @Param
     */
    private void createOrder(TakeoutOrderObj takeoutOrderObj) {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ORDER_CREATE, "");

        OkHttpUtil.doPost(url, token, takeoutOrderObj, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TakeoutCreateOrderBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TakeoutCreateOrderBean.class);
                if (jsonObj.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(TakeoutOrderConfirmActivity.this, TakeoutPlayActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("orderNo", jsonObj.getOrderNo());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(TakeoutOrderConfirmActivity.this, "订单创建失败！请稍后再试\n" + jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //选择收货地址
            case R.id.addressInfo_box:
                changeAddress();
                break;
            //提交订单
            case R.id.submit_btn:
                submitOrder();
                break;
        }
    }

}