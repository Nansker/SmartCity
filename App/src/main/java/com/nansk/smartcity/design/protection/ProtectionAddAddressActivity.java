package com.nansk.smartcity.design.protection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.protection.ProtectionAddressBean;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 14:15
 * @description 地址填写
 */

public class ProtectionAddAddressActivity extends BaseActivity {

    private EditText nameEt;
    private EditText telEt;
    private EditText addressEt;
    private EditText addressNoEt;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_protection_add_address);
        setToolBarTitle("地址填写");
        initView();
    }

    private void initView() {
        nameEt = (EditText) findViewById(R.id.name_et);
        telEt = (EditText) findViewById(R.id.tel_et);
        addressEt = (EditText) findViewById(R.id.address_et);
        addressNoEt = (EditText) findViewById(R.id.address_no_et);
        submitBtn = (Button) findViewById(R.id.submit_btn);

        submitBtn.setBackground(getDrawable("#22bf76",100));
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInfo();
            }
        });
    }

    /**
     * @Create 2021/10/17 14:32
     * @Role 提交信息
     * @Param
     */
    private void submitInfo() {
        String name = nameEt.getText().toString().trim();
        String tel = telEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();
        String addressNo = addressNoEt.getText().toString().trim();

        if (!name.isEmpty() && !name.isEmpty() && !name.isEmpty() && !name.isEmpty()){
            ProtectionAddressBean addressBean = new ProtectionAddressBean();
            addressBean.setName(name);
            addressBean.setTel(tel);
            addressBean.setAddress(address);
            addressBean.setAddressNo(addressNo);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("addressObj",addressBean);
            intent.putExtras(bundle);
            setResult(121,intent);
            finish();
        } else {
            showToast("请将地址信息填写完整",1200);
        }

    }
}