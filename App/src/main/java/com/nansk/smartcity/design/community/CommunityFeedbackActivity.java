package com.nansk.smartcity.design.community;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.dialog.ToastDialog;
import com.nansk.smartcity.utils.WindowMangerUtils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 18:56
 * @description 评价反馈
 */

public class CommunityFeedbackActivity extends BaseActivity {

    private EditText contentEt;
    private Button submitBtn;
    private LinearLayout successBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_community_feedback);
        setBarDivider(false, "");
        initView();
    }

    private void initView() {
        contentEt = (EditText) findViewById(R.id.content_et);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        successBox = (LinearLayout) findViewById(R.id.success_box);

        contentEt.setBackground(getDrawable("#ffffff", 20, 5, "#f2f2f2"));
        submitBtn.setBackground(getDrawable("#007aff", 100));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = WindowMangerUtils.getWindowSize(CommunityFeedbackActivity.this, 1) / 3;
        contentEt.setLayoutParams(layoutParams);

        successBox.setVisibility(View.GONE);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!contentEt.getText().toString().trim().isEmpty()) {

                    final ToastDialog dialog = new ToastDialog(CommunityFeedbackActivity.this);
                    dialog.showReveal("提交反馈中...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMsg("反馈成功！");
                        }
                    }, 600);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            contentEt.setText("");
                            contentEt.setVisibility(View.GONE);
                            successBox.setVisibility(View.VISIBLE);
                            dialog.dismiss();
                        }
                    }, 1200);
                } else {
                    showToast("请填写需要反馈内容!");
                }
            }
        });
    }
}