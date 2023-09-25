package com.nansk.smartcity.activity.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.takeout.TakeoutAddressListBean;
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
 * @Create 2021/09/23 15:09
 * @Description 外卖-我的-地址管理-新增地址
 */

public class TakeoutNewAddressActivity extends BaseActivity {
    private EditText addressEt;
    private EditText nameEt;
    private EditText phoneEt;
    private RadioButton homeRd;
    private RadioButton companyRd;
    private RadioButton schoolRd;
    private Button submitBtn;

    private TakeoutAddressListBean.DataBean addressObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_new_address);
        setToolBarBackground("#FFC107",0);
        setToolBarTitle("地址管理");
        initView();
    }

    private void initView() {

        addressEt = (EditText) findViewById(R.id.address_et);
        nameEt = (EditText) findViewById(R.id.name_et);
        phoneEt = (EditText) findViewById(R.id.phone_et);
        homeRd = (RadioButton) findViewById(R.id.home_rd);
        companyRd = (RadioButton) findViewById(R.id.company_rd);
        schoolRd = (RadioButton) findViewById(R.id.school_rd);
        submitBtn = (Button) findViewById(R.id.submit_btn);


        Intent intent = getIntent();
        addressObj = (TakeoutAddressListBean.DataBean) intent.getSerializableExtra("addressObj");

        if (addressObj != null){
            fillData();
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改地址
                if (addressObj != null){
                    editAddress();
                 //新增地址
                }else {
                    newAddress();
                }
            }
        });
    }

    /**
     * @Create 2021/9/23 15:41
     * @Role 新增地址
     * @Param
     */
    private void newAddress() {
        String address = addressEt.getText().toString().trim();
        String name = nameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String label = null;
        if (homeRd.isChecked()){
            label = "家";
        }else if (companyRd.isChecked()){
            label ="公司";
        }else if (schoolRd.isChecked()){
            label ="学校";
        }
        if (address!=null && name!=null && phone!=null && label !=null){
            TakeoutAddressListBean.DataBean obj = new TakeoutAddressListBean.DataBean();
            obj.setAddressDetail(address);
            obj.setName(name);
            obj.setPhone(phone);
            obj.setLabel(label);

            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ADDRESS, "");

            OkHttpUtil.doPost(url, MyApplication.getToken(this), obj, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final RequestResultBean resultBean = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultBean.getCode() == 200){
                                Toast.makeText(TakeoutNewAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 1000);
                            }else {
                                Toast.makeText(TakeoutNewAddressActivity.this, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }else {
            Toast.makeText(this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @Create 2021/9/23 15:41
     * @Role 修改地址信息
     * @Param
     */
    private void editAddress() {
        String address = addressEt.getText().toString().trim();
        String name = nameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String label = null;
        if (homeRd.isChecked()){
            label = "家";
        }else if (companyRd.isChecked()){
            label ="公司";
        }else if (schoolRd.isChecked()){
            label ="学校";
        }
        if (address!=null && name!=null && phone!=null && label !=null){
            TakeoutAddressListBean.DataBean obj = new TakeoutAddressListBean.DataBean();
            obj.setId(addressObj.getId());
            obj.setAddressDetail(address);
            obj.setName(name);
            obj.setPhone(phone);
            obj.setLabel(label);

            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ADDRESS, "");

            OkHttpUtil.doPut(url, MyApplication.getToken(this), obj, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final RequestResultBean resultBean = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultBean.getCode() == 200){
                                Toast.makeText(TakeoutNewAddressActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 1000);
                            }else {
                                Toast.makeText(TakeoutNewAddressActivity.this, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }else {
            Toast.makeText(this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Create 2021/9/23 15:35
     * @Role 修改地址，自动填充信息
     * @Param
     */
    private void fillData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addressEt.setText(addressObj.getAddressDetail());
                nameEt.setText(addressObj.getName());
                phoneEt.setText(addressObj.getPhone());

                switch (addressObj.getLabel()){
                    case "家":
                        homeRd.setChecked(true);
                        break;
                    case "gs":
                        companyRd.setChecked(true);
                        break;
                    case "学校":
                        schoolRd.setChecked(true);
                        break;
                }
            }
        });
    }


}