package com.nansk.smartcity.design.party;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.design.DesignResources;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.youth.banner.Banner;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/21 17:11
 * @description 智慧党建
 */

public class PartyActivity extends BaseActivity {

    private Banner banner;
    private GridLayout menuContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party);
        setToolBarTitle("智慧党键");
        setToolBarBackground(getResources().getString(R.string.theme_party));
        initView();
    }

    /**
     * @Create 2021/10/21 18:14
     * @Role
     * @Param
     */
    private void initView() {

        banner = (Banner) findViewById(R.id.banner);
        menuContainer = (GridLayout) findViewById(R.id.menu_container);

        BannerSetUtils.setBannerStyle(PartyActivity.this, banner);
        banner.setImages(DesignResources.getPartyBannerPagers());
        banner.start();

        for (int i = 0; i < menuContainer.getChildCount(); i++) {
            menuContainer.getChildAt(i).setBackground(getDrawable("#ffffff", 10));

            final int finalI = i;
            menuContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        //党建动态
                        case 0:
                            startActivity(new Intent(PartyActivity.this, PartyPressActivity.class));
                            break;
                        //党员学习
                        case 1:
                            startActivity(new Intent(PartyActivity.this, PartyStudyActivity.class));
                            break;
                        //组织活动
                        case 2:
                            startActivity(new Intent(PartyActivity.this, PartyActActivity.class));
                            break;
                        //建言献策
                        case 3:
                            startActivity(new Intent(PartyActivity.this, PartyAdviceActivity.class));
                            break;
                        //随手拍
                        case 4:
                            startActivity(new Intent(PartyActivity.this, PartySharingActivity.class));
                            break;
                    }
                }
            });

        }
    }
}