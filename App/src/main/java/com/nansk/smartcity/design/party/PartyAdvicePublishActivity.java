package com.nansk.smartcity.design.party;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.party.PartyAdviceBean;
import com.nansk.smartcity.dialog.ToastDialog;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 10:28
 * @description 发表
 */

public class PartyAdvicePublishActivity extends BaseActivity {
    private int Publish_ADVICE = 120;
    private EditText titleTv;
    private EditText bodyEt;
    private TextView wordsTv;
    private Button submitBtn;
    private ToastDialog toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_advice_publish);
        setToolBarTitle("建言献策");
        initView();
        initObject();
    }

    /**
     * @Create 2021/10/24 11:32
     * @Role
     * @Param
     */
    private void submit() {
        closeKeyboard(bodyEt);
        String title = titleTv.getText().toString().trim();
        String content = bodyEt.getText().toString().trim();
        if (!title.equals("")) {
            if (!content.equals("")) {
                PartyAdviceBean bean = new PartyAdviceBean();
                bean.setTitle(title);
                bean.setContent(content);
                bean.setName(UserInfoUtils.getUserInfo(PartyAdvicePublishActivity.this).getNickName());

                long timeMillis = System.currentTimeMillis();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(timeMillis);
                bean.setDate(format.format(date));

                List<PartyAdviceBean> list = new ArrayList<>();
                list.add(0,bean);

                String record = (String) SharedPreferencesUtils.getRecord(PartyAdvicePublishActivity.this, "party_advice", "");

                if (!record.equals("")) {
                    List<PartyAdviceBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<PartyAdviceBean>>() {
                    }.getType());
                    for (int i = 0; i < json.size(); i++) {
                        PartyAdviceBean advice = json.get(i);
                        list.add(advice);
                    }
                }
                SharedPreferencesUtils.addRecord(PartyAdvicePublishActivity.this, "party_advice", MyApplication.gson.toJson(list));

                toast.showReveal("提交中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.setMsg("提交成功！");
                    }
                }, 800);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("isUpdate", true);
                        setResult(Publish_ADVICE, intent);
                        finish();
                    }
                }, 1200);

            } else {
                showToast("请输入内容！");
            }
        } else {
            showToast("请输入标题");
        }
    }

    /**
     * @Create 2021/10/24 11:28
     * @Role
     * @Param
     */
    private void initObject() {
        toast = new ToastDialog(PartyAdvicePublishActivity.this);
        titleTv.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (temp.length() >= 20) {
                    showToast("标题最多只能输入20个字");
                }
            }
        });
        bodyEt.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                wordsTv.setText(temp.length() + "字");
            }
        });

    }

    private void initView() {
        titleTv = (EditText) findViewById(R.id.title_tv);
        bodyEt = (EditText) findViewById(R.id.body_et);
        wordsTv = (TextView) findViewById(R.id.words_tv);
        submitBtn = (Button) findViewById(R.id.submit_btn);

        submitBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party), 100));

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

}