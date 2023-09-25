package com.nansk.smartcity.activity.bus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.bus.BusLinesBean;

import java.util.ArrayList;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 12:41
 * @Description 日期选择页
 */

public class BusChangeDateActivity extends BaseActivity implements View.OnClickListener {

    private DatePicker datePicker;
    private TextView dateTv;
    private Button backBtn;
    private Button nextBtn;

    private BusLinesBean.RowsBean busObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_bus_change_date);
        setToolBarTitle("日期选择");
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {

        datePicker = (DatePicker) findViewById(R.id.date_picker);
        dateTv = (TextView) findViewById(R.id.date_tv);
        backBtn = (Button) findViewById(R.id.back_btn);
        nextBtn = (Button) findViewById(R.id.next_btn);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateTv.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        });

        Intent intent = getIntent();
        busObj = (BusLinesBean.RowsBean) intent.getSerializableExtra("busObj");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.next_btn:
                //传递公交车信息对象和选择的时间
                Intent intent = new Intent(BusChangeDateActivity.this, BusCustomActivity.class);
                String date = dateTv.getText().toString();

                ArrayList<Object> objects = new ArrayList<>();
                objects.add(0, busObj);
                objects.add(1, date);

                Bundle bundle = new Bundle();
                bundle.putSerializable("objects", objects);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}