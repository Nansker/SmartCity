package com.nansk.smartcity.design.protection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 12:07
 * @description 分类课堂
 */

public class ProtectionClassroomActivity extends BaseActivity {

    private LinearLayout cardContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_protection_classroom);
        setToolBarBackground("#22bf76");
        setToolBarTitle("分类课堂");
        initView();
    }

    private void initView() {
        cardContainer = (LinearLayout) findViewById(R.id.card_container);
        final String[] names = new String[]{"可回收物", "其他垃圾", "厨余垃圾", "有害垃圾"};
        for (int i = 0; i < cardContainer.getChildCount(); i++) {
            final int finalI = i;
            cardContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProtectionClassroomActivity.this, ProtectionClassDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("obj",names[finalI]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

    }
}