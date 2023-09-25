package com.nansk.smartcity.activity.hospital;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.hospital.ReservationsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 19:33
 * @Description
 */

public class HospitalConfirmActivity extends BaseActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private ReservationsBean reservaObj;

    private TextView nameTv;
    private TextView categoryNameTv;
    private TextView typeTv;
    private TextView moneyTv;
    private TextView dateTv;
    private TextView timeTv;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_hospital_confirm);
        setToolBarTitle("预约确认单");
        initView();

        fillData();

    }


    private void initView() {

        nameTv = (TextView) findViewById(R.id.name_tv);
        categoryNameTv = (TextView) findViewById(R.id.categoryName_tv);
        typeTv = (TextView) findViewById(R.id.type_tv);
        moneyTv = (TextView) findViewById(R.id.money_tv);

        dateTv = (TextView) findViewById(R.id.date_tv);
        timeTv = (TextView) findViewById(R.id.time_tv);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);

        dateTv.setOnClickListener(this);
        timeTv.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

        Intent intent = getIntent();
        reservaObj = (ReservationsBean) intent.getSerializableExtra("reservaObj");


    }

    /**
     * @Create 2021/9/17 20:22
     * @Role 生成预约单
     * @Param
     */
    private void fillData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nameTv.setText(reservaObj.getPatientName());
                categoryNameTv.setText(reservaObj.getCategoryName());
                moneyTv.setText(reservaObj.getMoney().toString() + "元");

                if (reservaObj.getType().equals("2")) {
                    typeTv.setText("普通号");
                } else if (reservaObj.getType().equals("2")) {
                    typeTv.setText("专家号");
                }
            }
        });
    }

    /**
     * @Create 2021/9/17 20:35
     * @Role 提交订单
     * @Param
     */
    private void confirmOrders() {
        String date = dateTv.getText().toString();
        String time = timeTv.getText().toString();
        if (!date.equals("请选择") && !time.equals("请选择")) {
            reservaObj.setReserveTime(date + " " + time);
            String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL_RESERVE, "");
            OkHttpUtil.doPost(url, MyApplication.getToken(this), reservaObj, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final RequestResultBean resultBean = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultBean.getCode() == 200) {
                                showToast("挂号成功！",1200);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(HospitalConfirmActivity.this, HospitalHistoryActivity.class);
                                        startActivity(intent);
                                    }
                                }, 1200);

                            } else {
                                Toast.makeText(HospitalConfirmActivity.this, resultBean.getMsg() + "", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            });

        } else {
            showToast("请选择需要预约的时间",1200);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bar:
                finish();
                break;
            //确认订单
            case R.id.confirm_btn:
                confirmOrders();
                break;
            //日期选择
            case R.id.date_tv:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog pickerDialog = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                pickerDialog.show();
                break;
            //时间选择
            case R.id.time_tv:
                Calendar calendar1 = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, this,
                        calendar1.get(Calendar.HOUR_OF_DAY),
                        calendar1.get(Calendar.MINUTE), true
                );
                timePickerDialog.show();
                break;
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
        dateTv.setText(desc);
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc = String.format("%d:%d", hourOfDay, minute);
        timeTv.setText(desc);
    }
}