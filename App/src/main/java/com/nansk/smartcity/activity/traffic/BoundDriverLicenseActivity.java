package com.nansk.smartcity.activity.traffic;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.traffic.TrafficLicenseBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/05 13:13
 * @description 绑定驾驶证页面
 */

public class BoundDriverLicenseActivity extends BaseActivity {

    private EditText licenseNoEt;
    private TextView verifyDateTv;
    private EditText idCardEt;
    private TextView tipsTv;
    private Button bindingBtn;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_bound_driver_license);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("绑定驾驶证");

        initView();
        initObject();
    }

    private void initView() {

        licenseNoEt = (EditText) findViewById(R.id.licenseNo_et);
        verifyDateTv = (TextView) findViewById(R.id.verifyDate_tv);
        idCardEt = (EditText) findViewById(R.id.idCard_et);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bindingBtn = (Button) findViewById(R.id.binding_btn);
    }

    /**
     * @Create 2021/10/5 15:03
     * @Role
     * @Param
     */
    private void initObject() {

        tipsTv.setVisibility(View.GONE);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setColor(Color.parseColor("#2c65a8"));
        bindingBtn.setBackground(gradientDrawable);


        token = MyApplication.getToken(this);

        verifyDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog pickerDialog = new DatePickerDialog(BoundDriverLicenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String format = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
                        verifyDateTv.setText(format);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                pickerDialog.show();
            }
        });

        bindingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingLicense();
            }
        });
    }

    /**
     * @Create 2021/10/5 15:12
     * @Role 绑定驾驶证
     * @Param
     */
    private void bindingLicense() {
        String licenseNo = licenseNoEt.getText().toString().trim();
        String verifyDate = verifyDateTv.getText().toString();
        String idCard = idCardEt.getText().toString().trim();

        //判断非空
        if (!licenseNo.isEmpty() && !verifyDate.isEmpty() && !idCard.isEmpty()){
            TrafficLicenseBean trafficLicenseBean = new TrafficLicenseBean();
            trafficLicenseBean.setLicenseNo(licenseNo);
            trafficLicenseBean.setIdCard(idCard);
            trafficLicenseBean.setVerifyDate(verifyDate);

            String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_LICENSE, "");
            OkHttpUtil.doPost(url, token, trafficLicenseBean, new Callback() {
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
                                tipsTv.setVisibility(View.VISIBLE);
                                tipsTv.setText("驾驶证绑定成功！即将跳转...");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 600);

                            }else {
                                tipsTv.setVisibility(View.VISIBLE);
                                tipsTv.setText(jsonObj.getMsg());
                            }
                        }
                    });
                }
            });
        }else {
            Toast.makeText(this, "请将要绑定的驾驶证信息填写完整！", Toast.LENGTH_SHORT).show();
        }


    }

}