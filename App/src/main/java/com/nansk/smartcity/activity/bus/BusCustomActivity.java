package com.nansk.smartcity.activity.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.bus.BusCustomInfoBean;
import com.nansk.smartcity.beans.bus.BusLinesBean;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 17:04
 * @Description 定制班车页
 */

public class BusCustomActivity extends BaseActivity implements View.OnClickListener {
    private BusLinesBean.RowsBean busObj;
    private String date;

    private Button nextBtn;
    private TextView dateTv;

    private EditText nickNameEt;
    private EditText phoneEt;
    private EditText startEt;
    private EditText endEt;

    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_bus_custom);
        setToolBarTitle("乘车信息");

        Intent intent = getIntent();
        List<Object> objects = (List<Object>) intent.getSerializableExtra("objects");
        busObj = (BusLinesBean.RowsBean) objects.get(0);
        date = (String) objects.get(1);

        initView();
    }

    private void initView() {

        dateTv = (TextView) findViewById(R.id.date_tv);
        nickNameEt = (EditText) findViewById(R.id.nickName_et);
        phoneEt = (EditText) findViewById(R.id.phone_et);
        startEt = (EditText) findViewById(R.id.start_et);
        endEt = (EditText) findViewById(R.id.end_et);

        nextBtn = (Button) findViewById(R.id.next_btn);
        backBtn = (Button) findViewById(R.id.back_btn);

        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);


        setUserInfo();

    }

    /**
     * @Create 2021/9/16 20:48
     * @Role 自动填充用户相关信息
     * @Param
     */
    private void setUserInfo() {
        final String nickName = (String) SharedPreferencesUtils.getRecord(this, "nickName", "");
        final String phonenumber = (String) SharedPreferencesUtils.getRecord(this, "phonenumber", "");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dateTv.setText(date);
                nickNameEt.setText(nickName);
                phoneEt.setText(phonenumber);
            }
        });

    }

    /**
     * @Create 2021/9/16 21:08
     * @Role 下一步，传递输入信息到下一页页
     * @Param
     */
    private void next() {
        String date = dateTv.getText().toString();
        String nickName = nickNameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String start = startEt.getText().toString().trim();
        String end = endEt.getText().toString().trim();

        if (!nickName.isEmpty() && !phone.isEmpty() && !start.isEmpty() && !end.isEmpty()) {
            BusCustomInfoBean customInfoBean = new BusCustomInfoBean();

            customInfoBean.setDate(date);
            customInfoBean.setNickName(nickName);
            customInfoBean.setPhone(phone);
            customInfoBean.setStart(start);
            customInfoBean.setEnd(end);
            customInfoBean.setPath(busObj.getName());
            customInfoBean.setPrice(busObj.getPrice().toString());

            Intent intent = new Intent(BusCustomActivity.this, BusConfirmOrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ordersObj", customInfoBean);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            showToast("请将信息填写完整",1200);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bar:
            case R.id.back_btn:
                finish();
                break;
            case R.id.next_btn:
                WindowMangerUtils.closeKeyboard(BusCustomActivity.this, v);
                next();
                break;
        }
    }

}