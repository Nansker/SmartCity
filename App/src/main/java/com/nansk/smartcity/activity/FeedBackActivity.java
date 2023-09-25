package com.nansk.smartcity.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.FeedBackBean;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/14 16:34
 * @Description 意见反馈
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private EditText bodyEt;
    private Button submitBtn;
    private TextView wordsTv;
    private EditText contentTitleTv;

    private LinearLayout tipsBox;
    private LinearLayout bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_feed_back);
        setToolBarTitle("意见反馈");
        initView();
        initObject();

    }


    private void initView() {
        bodyEt = (EditText) findViewById(R.id.body_et);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        wordsTv = (TextView) findViewById(R.id.words_tv);
        contentTitleTv = (EditText) findViewById(R.id.contentTitle_tv);

        submitBtn.setOnClickListener(this);

        tipsBox = (LinearLayout) findViewById(R.id.tips_box);
        bodyContainer = (LinearLayout) findViewById(R.id.body_container);
    }


    private void initObject() {

        contentTitleTv.addTextChangedListener(new TextWatcher() {
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
                if (temp.length() == 20) {
                    showToast("标题最多只能20个字！");
                }
            }
        });

        ViewGroup.LayoutParams layoutParams = bodyEt.getLayoutParams();
        layoutParams.height = WindowMangerUtils.getWindowSize(FeedBackActivity.this, 1) / 4;
        bodyEt.setLayoutParams(layoutParams);

        //实时监测用户输入字符长度
        bodyEt.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //用户输入的字符数
                wordsTv.setText(Integer.toString(temp.length()));
                if (temp.length() == 150) {
                    showToast("最多只能输入150个字符！");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //提交反馈内容
    private void submitContent() {
        String title = contentTitleTv.getText().toString().trim();
        String content = bodyEt.getText().toString().trim();

        contentTitleTv.clearFocus();
        bodyEt.clearFocus();
        WindowMangerUtils.closeKeyboard(FeedBackActivity.this, contentTitleTv);
        WindowMangerUtils.closeKeyboard(FeedBackActivity.this, bodyEt);

        if (!title.isEmpty()) {
            if (!content.isEmpty()) {
                submitBtn.setText("正在提交反馈");
                FeedBackBean bean = new FeedBackBean();
                bean.setContent(content);
                bean.setTitle(title);
                String url = ConnectInfo.getUrl(ConnectInfo.FEEDBACK_COMMENT, "");
                OkHttpUtil.doPost(url, MyApplication.getToken(this), bean, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (json.getCode() == 200) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            bodyContainer.setVisibility(View.GONE);
                                            tipsBox.setVisibility(View.VISIBLE);

                                        }
                                    }, 300);

                                } else {
                                    Toast.makeText(FeedBackActivity.this, json.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                });
            } else {
                Toast.makeText(this, "反馈内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "反馈标题不能为空！", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                submitContent();
                break;
        }
    }


}