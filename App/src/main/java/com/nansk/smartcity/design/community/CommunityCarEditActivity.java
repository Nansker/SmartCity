package com.nansk.smartcity.design.community;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.community.CommunityCarBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 15:57
 * @description 车辆修改or添加
 */

public class CommunityCarEditActivity extends BaseActivity {

    private EditText carNoEt;
    private EditText parkNoEv;
    private EditText cardNoEt;
    private EditText nameEt;
    private EditText telEt;
    private EditText groupEt;
    private EditText addressEt;
    private Button saveBtn;

    private CommunityCarBean carObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_community_car_edit);
        initView();
    }

    /**
     * @Create 2021/10/18 17:17
     * @Role 修改
     * @Param
     */
    private void modifyInfo() {
        String record = (String) SharedPreferencesUtils.getRecord(CommunityCarEditActivity.this, "community_car", "");
        if (!record.equals("")) {
            List<CommunityCarBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<CommunityCarBean>>() {
            }.getType());

            for (int i =0;i<json.size();i++){
                if (json.get(i).getCarNo().equals(carObj.getCarNo())){
                    json.remove(i);
                    SharedPreferencesUtils.addRecord(CommunityCarEditActivity.this, "community_car", MyApplication.gson.toJson(json));
                    saveInfo();
                }
            }

        }
    }

    /**
     * @Create 2021/10/18 16:30
     * @Role 添加
     * @Param
     */
    private void saveInfo() {
        String carNo = carNoEt.getText().toString().trim();
        String parkNo = parkNoEv.getText().toString().trim();
        String cardNo = cardNoEt.getText().toString().trim();
        String name = nameEt.getText().toString().trim();
        String tel = telEt.getText().toString().trim();
        String group = groupEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();

        if (!carNo.isEmpty() && !parkNo.isEmpty() && !cardNo.isEmpty() && !name.isEmpty() && !tel.isEmpty() && !group.isEmpty() && !address.isEmpty()) {
            CommunityCarBean carBean = new CommunityCarBean();
            carBean.setCarNo(carNo);
            carBean.setParkNo(parkNo);
            carBean.setCardNo(cardNo);
            carBean.setName(name);
            carBean.setTel(tel);
            carBean.setGroup(group);
            carBean.setAddress(address);

            String record = (String) SharedPreferencesUtils.getRecord(CommunityCarEditActivity.this, "community_car", "");

            if (!record.equals("")) {
                List<CommunityCarBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<CommunityCarBean>>() {
                }.getType());
                if (json.size() > 0) {
                    json.add(carBean);
                    SharedPreferencesUtils.addRecord(CommunityCarEditActivity.this, "community_car", MyApplication.gson.toJson(json));
                    onSuccess();
                } else {
                    List<CommunityCarBean> beans = new ArrayList<>();
                    beans.add(carBean);
                    SharedPreferencesUtils.addRecord(CommunityCarEditActivity.this, "community_car", MyApplication.gson.toJson(beans));
                    onSuccess();
                }
            } else {
                List<CommunityCarBean> beans = new ArrayList<>();
                beans.add(carBean);
                SharedPreferencesUtils.addRecord(CommunityCarEditActivity.this, "community_car", MyApplication.gson.toJson(beans));
                onSuccess();
            }

        } else {
            showToast("请先将信息填写完整");
        }

    }

    public void onSuccess() {
        showToast("保存成功！");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.putExtra("isUpdate", true);
                setResult(121, intent);
                finish();
            }
        }, 1200);
    }

    private void initView() {
        carNoEt = (EditText) findViewById(R.id.carNo_et);
        parkNoEv = (EditText) findViewById(R.id.parkNo_ev);
        cardNoEt = (EditText) findViewById(R.id.cardNo_et);
        nameEt = (EditText) findViewById(R.id.name_et);
        telEt = (EditText) findViewById(R.id.tel_et);
        groupEt = (EditText) findViewById(R.id.group_et);
        addressEt = (EditText) findViewById(R.id.address_et);

        saveBtn = (Button) findViewById(R.id.save_btn);
        saveBtn.setBackground(getDrawable("#1296db", 20));

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carObj != null) {
                    modifyInfo();
                } else {
                    saveInfo();
                }

            }
        });

        if (getIntent().getSerializableExtra("obj") != null) {
            carObj = (CommunityCarBean) getIntent().getSerializableExtra("obj");
            carNoEt.setText(carObj.getCarNo());
            cardNoEt.setText(carObj.getCardNo());
            nameEt.setText(carObj.getName());
            telEt.setText(carObj.getTel());
            groupEt.setText(carObj.getGroup());
            addressEt.setText(carObj.getAddress());
            parkNoEv.setText(carObj.getParkNo());

            setToolBarTitle("修改车辆信息");
        }else {
            setToolBarTitle("添加车辆");
        }

    }


}