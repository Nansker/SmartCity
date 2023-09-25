package com.nansk.smartcity.activity.lifepay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.lifepay.LifePayBillBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 15:03
 * @Description 根据缴费类别（水、电..）查询记录
 */

public class LifepayQueryActivity extends BaseActivity {

    //    分类 id(1电话费 2 水费 3 电费 4 燃气费)
    private int categoryId;

    private TextView addressTv;
    private EditText paymentNoEt;
    private Button queryBtn;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_lifepay_query);

        initView();
    }

    private void initView() {

        addressTv = (TextView) findViewById(R.id.address_tv);
        paymentNoEt = (EditText) findViewById(R.id.paymentNo_et);
        queryBtn = (Button) findViewById(R.id.query_btn);
        tipsTv = (TextView) findViewById(R.id.tips_tv);

        tipsTv.setVisibility(View.GONE);

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", 0);
        switch (categoryId) {
            case 2:
                setToolBarTitle("水费");
                break;
            case 3:
                setToolBarTitle("电费");
                break;
            case 4:
                setToolBarTitle("燃气费");
                break;
        }

        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryBill();
            }
        });
    }

    /**
     * @Create 2021/9/28 16:12
     * @Role 根据缴费编号查询缴费账单
     * @Param 此处请求需要token信息，API V2.0未说明
     */
    private void queryBill() {
        //收起键盘，清除输入框焦点
        WindowMangerUtils.closeKeyboard(LifepayQueryActivity.this,paymentNoEt);
        paymentNoEt.clearFocus();
        String paymentNo = paymentNoEt.getText().toString();
        if (!paymentNo.isEmpty()) {
            String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_BILL, "?paymentNo=" + paymentNo + "&categoryId=" + categoryId);
            OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {

                    final LifePayBillBean jsonObj = MyApplication.gson.fromJson(response.body().string(), LifePayBillBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200){
                                final LifePayBillBean.DataBean data = jsonObj.getData();
                                if (data==null){
                                    tipsTv.setVisibility(View.VISIBLE);
                                }else {
                                    tipsTv.setVisibility(View.VISIBLE);
                                    tipsTv.setText("查询成功！正在生成信息...");
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //跳转到支付页
                                            Intent intent = new Intent(LifepayQueryActivity.this, LifepayPaymentActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("billObj",data);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            tipsTv.setVisibility(View.GONE);
                                            tipsTv.setText("暂无缴费记录！");
                                        }
                                    }, 1000);
                                }
                            }else {
                                Toast.makeText(LifepayQueryActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "缴费号不能为空！", Toast.LENGTH_SHORT).show();
        }
    }


}