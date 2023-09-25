package com.nansk.smartcity.design.protection;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.protection.ProtectionAddressBean;
import com.nansk.smartcity.beans.protection.ProtectionRecyclingSubmitBean;
import com.nansk.smartcity.dialog.ChangeRubbishDialog;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 13:39
 * @description 上门回收
 */

public class ProtectRecyclingActivity extends BaseActivity {

    private LinearLayout tabContainer;
    private TextView addressTv;
    private TextView classNameTv;
    private TextView timeTv;
    private EditText noticeEt;
    private Button submitBtn;

    private ProtectionRecyclingSubmitBean submitObj;
    private ProtectionAddressBean addressObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_protection_recycling);
        setToolBarBackground("#22bf76");
        setToolBarTitle("上门回收");

        setToolBarRightButton(true, "回收历史", new MyCallBack() {
            @Override
            public void CallBack() {
                Intent intent = new Intent(ProtectRecyclingActivity.this, ProtectRecyclingListActivity.class);
                intent.putExtra("dataType","history");
                startActivity(intent);
            }
        });

        setBarDivider(false, "");
        initView();
    }


    /**
     * @Create 2021/10/17 15:33
     * @Role 提交信息
     * @Param
     */
    private void submitInfo() {
        String recycling = (String) SharedPreferencesUtils.getRecord(ProtectRecyclingActivity.this, "protection_recycling", "");
        List<ProtectionRecyclingSubmitBean> json = MyApplication.gson.fromJson(recycling, new TypeToken<List<ProtectionRecyclingSubmitBean>>() {
        }.getType());
        if (json != null && json.size() > 0) {
            json.add(submitObj);
            SharedPreferencesUtils.addRecord(ProtectRecyclingActivity.this, "protection_recycling", MyApplication.gson.toJson(json));
            showToast("提交成功！", 1200);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1200);
        } else {
            List<ProtectionRecyclingSubmitBean> submitBeans = new ArrayList<>();
            submitBeans.add(submitObj);
            SharedPreferencesUtils.addRecord(ProtectRecyclingActivity.this, "protection_recycling", MyApplication.gson.toJson(submitBeans));
            showToast("提交成功！", 1200);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1200);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 121) {
            addressObj = (ProtectionAddressBean) data.getSerializableExtra("addressObj");
            submitObj.setAddressObj(addressObj);
            addressTv.setText(addressObj.getName() + "\u3000" + addressObj.getTel() + "\n" + addressObj.getAddress() + addressObj.getAddressNo());
        }
    }

    private void initView() {
        tabContainer = (LinearLayout) findViewById(R.id.tab_container);
        addressTv = (TextView) findViewById(R.id.address_tv);
        classNameTv = (TextView) findViewById(R.id.className_tv);
        timeTv = (TextView) findViewById(R.id.time_tv);
        noticeEt = (EditText) findViewById(R.id.notice_ev);
        submitBtn = (Button) findViewById(R.id.submit_btn);

        submitObj = new ProtectionRecyclingSubmitBean();
        submitBtn.setBackground(getDrawable("#22bf76", 100));
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressObj != null) {
                    if (submitObj.getClassX() != null) {
                        if (submitObj.getTime() != null) {
                            if (noticeEt.getText().toString().trim() != null) {
                                submitObj.setParams(noticeEt.getText().toString().trim());
                            }
                            submitInfo();
                        } else {
                            showToast("请选择预约时间", 1200);
                        }
                    } else {
                        showToast("请选择回收物品的分类", 1200);
                    }
                } else {
                    showToast("需要先添加上门地址", 1200);
                }

            }
        });

        for (int i = 0; i < tabContainer.getChildCount(); i++) {
            final int finalI = i;
            tabContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            startActivityForResult(new Intent(ProtectRecyclingActivity.this, ProtectionAddAddressActivity.class), 121);
                            break;
                        case 1:
                            final ChangeRubbishDialog dialog = new ChangeRubbishDialog();
                            dialog.DialogCallBack(new ChangeRubbishDialog.DialogCallBack() {
                                @Override
                                public void DialogCallBack(String classX) {
                                    classNameTv.setText(classX);
                                    submitObj.setClassX(classX);
                                    dialog.dismiss();
                                }
                            });
                            dialog.show(getSupportFragmentManager(), "dialog");
                            break;
                        case 2:
                            Calendar calendar = Calendar.getInstance();
                            DatePickerDialog pickerDialog = new DatePickerDialog(ProtectRecyclingActivity.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String format = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
                                    timeTv.setText(format);
                                    submitObj.setTime(format);
                                }
                            }, calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                            pickerDialog.show();
                            break;
                    }
                }
            });
        }

    }

}