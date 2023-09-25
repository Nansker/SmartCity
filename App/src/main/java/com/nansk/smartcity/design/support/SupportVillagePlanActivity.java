package com.nansk.smartcity.design.support;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.support.SupportMyHelpBean;
import com.nansk.smartcity.beans.support.SupportVillageBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 17:53
 * @description 扶贫方案
 */

public class SupportVillagePlanActivity extends BaseActivity {
    private SupportVillageBean villageObj;
    private LinearLayout bodyContainer;
    private EditText contentTitleTv;
    private EditText introduceEt;
    private EditText contentEt;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_village_plan);
        setToolBarTitle("扶贫初步方案");
        initView();
    }

    /**
     * @Create 2021/10/20 18:03
     * @Role
     * @Param
     */
    private void submit() {
        final String title = contentTitleTv.getText().toString().trim();
        final String introduce = introduceEt.getText().toString().trim();
        String content = contentEt.getText().toString().trim();

        if (!title.equals("")) {
            if (!introduce.equals("")) {
                if (!content.equals("")) {
                    showToast("方案提交中...",1000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            SupportMyHelpBean helpBean = new SupportMyHelpBean();
                            helpBean.setDate("2021-12-13");
                            helpBean.setIntroduction(introduce);
                            helpBean.setAddress(villageObj.getAddress());
                            helpBean.setName(villageObj.getName());

                            ArrayList<SupportMyHelpBean> helpBeans = new ArrayList<>();
                            helpBeans.add(helpBean);

                            String record = (String) SharedPreferencesUtils.getRecord(SupportVillagePlanActivity.this, "support_myhelp", "");
                            if (!record.equals("")) {
                                List<SupportMyHelpBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<SupportMyHelpBean>>() {
                                }.getType());
                                for (int i = 0; i < json.size(); i++) {
                                    helpBeans.add(json.get(i));
                                }
                                SharedPreferencesUtils.addRecord(SupportVillagePlanActivity.this, "support_myhelp",MyApplication.gson.toJson(helpBeans));
                            }else {
                                SharedPreferencesUtils.addRecord(SupportVillagePlanActivity.this, "support_myhelp",MyApplication.gson.toJson(helpBeans));
                            }
                            showToast("提交成功！请等待审核");

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(SupportVillagePlanActivity.this, SupportHelpActivity.class);
                                    intent.putExtra("dataType","help");
                                    startActivity(intent);

                                    finish();
                                }
                            }, 1200);

                        }
                    }, 1000);
                } else {
                    showToast("请填写方案详细内容！");
                }
            } else {
                showToast("请填写方案简介！");
            }
        } else {
            showToast("请填写方案标题！");
        }

    }

    private void initView() {
        bodyContainer = (LinearLayout) findViewById(R.id.body_container);
        contentTitleTv = (EditText) findViewById(R.id.contentTitle_tv);
        introduceEt = (EditText) findViewById(R.id.introduce_et);
        contentEt = (EditText) findViewById(R.id.content_et);
        submitBtn = (Button) findViewById(R.id.submit_btn);

        submitBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party), 100));
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        villageObj = (SupportVillageBean) getIntent().getSerializableExtra("villageObj");
    }

}