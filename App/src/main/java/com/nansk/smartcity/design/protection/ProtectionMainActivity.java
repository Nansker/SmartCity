package com.nansk.smartcity.design.protection;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 10:10
 * @description
 */

public class ProtectionMainActivity extends BaseActivity {

    private Banner banner;
    private GridLayout tabContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_protection_main);
        setToolBarBackground("#22bf76");
        setToolBarTitle("智慧环保");
        initView();
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        tabContainer = (GridLayout) findViewById(R.id.tab_container);

        BannerSetUtils.setBannerStyle(ProtectionMainActivity.this,banner);

        List<Integer> pagers = new ArrayList<>();
        pagers.add(R.drawable.ep_banner1);
        pagers.add(R.drawable.ep_banner2);
        pagers.add(R.drawable.ep_banner3);
        pagers.add(R.drawable.ep_banner4);
        pagers.add(R.drawable.ep_banner5);
        banner.setImages(pagers);
        banner.start();


            for (int i = 0; i < tabContainer.getChildCount(); i++) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(20);
                gradientDrawable.setColor(Color.parseColor("#ffffff"));
                tabContainer.getChildAt(i).setBackground(gradientDrawable);
                final int finalI = i;
                tabContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (finalI) {
                            case 0:
                                startActivity(new Intent(ProtectionMainActivity.this,ProtectionClassroomActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(ProtectionMainActivity.this,ProtectRecyclingActivity.class));
                                break;
                            case 2:  Intent intent = new Intent(ProtectionMainActivity.this, ProtectRecyclingListActivity.class);
                                intent.putExtra("dataType","near");
                                startActivity(intent);
                                break;
                        }
                    }
                });
        }
    }
}
