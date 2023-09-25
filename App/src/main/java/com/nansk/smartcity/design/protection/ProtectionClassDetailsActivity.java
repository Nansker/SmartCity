package com.nansk.smartcity.design.protection;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.protection.ProtectionClassBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 12:21
 * @description 课堂详情
 */

public class ProtectionClassDetailsActivity extends BaseActivity {
    private String obj;

    private TextView title;
    private LinearLayout contentContainer;
    private ImageView imageIv;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_protection_class_details);
        setToolBarBackground("#22bf76");
        initView();

    }

    private void fillData() {
        int[] images = new int[]{R.drawable.ep_class1, R.drawable.ep_class2, R.drawable.ep_class3, R.drawable.ep_class4};

        String data = FileReadUtils.getData(ProtectionClassDetailsActivity.this, R.raw.protect_class);
        List<ProtectionClassBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<ProtectionClassBean>>() {
        }.getType());

        for (int i = 0; i < json.size(); i++) {
            if (json.get(i).getClassX().equals(obj)) {
                title.setText(json.get(i).getParams() + "\n" + json.get(i).getNote());
                imageIv.setImageResource(images[i]);
                if (json.get(i).getRows().size() > 0) {
                    for (int j = 0; j < json.get(i).getRows().size(); j++) {
                        TextView textView = new TextView(ProtectionClassDetailsActivity.this);
                        textView.setText(json.get(i).getRows().get(j).getContent());
                        textView.setTextColor(Color.parseColor("#333333"));
                        textView.setTextSize(16);
                        contentContainer.addView(textView);
                    }
                }
            }
        }

    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        contentContainer = (LinearLayout) findViewById(R.id.content_container);
        imageIv = (ImageView) findViewById(R.id.image_iv);

        obj = getIntent().getStringExtra("obj");
        setToolBarTitle(obj);
        fillData();
    }
}