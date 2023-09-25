package com.nansk.smartcity.activity.metro;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.ViewPagerFragmentAdapter;
import com.nansk.smartcity.view.CustomViewPager;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 10:32
 * @description
 */

public class MetroActivity extends BaseActivity {

    private CustomViewPager bodyContainer;
    public static TabLayout bottomTabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro);

        initView();
    }

    private void initView() {
        bodyContainer = (CustomViewPager) findViewById(R.id.body_container);
        bottomTabMenu = (TabLayout) findViewById(R.id.bottom_tab_menu);

        bodyContainer.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), MetroBottomNavData.getFragments(), null));

        bottomTabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bodyContainer.setCurrentItem(tab.getPosition(), false);

                for (int i = 0; i < bottomTabMenu.getTabCount(); i++) {
                    View customView = bottomTabMenu.getTabAt(i).getCustomView();
                    ImageView icon = customView.findViewById(R.id.icon_iv);
                    TextView text = customView.findViewById(R.id.text_tv);

                    if (i == tab.getPosition()) {
                        icon.setColorFilter(Color.parseColor("#ca062c"));
                        text.setTextColor(Color.parseColor("#ca062c"));
                    } else {
                        icon.setColorFilter(Color.parseColor("#7F7F7F"));
                        text.setTextColor(Color.parseColor("#7F7F7F"));
                    }

                }

                setToolBarTitle(MetroBottomNavData.names[tab.getPosition()]);
                if (tab.getPosition() == 4) {
                   setToolBarBackground("#ffffff");
                    setToolbarIsShow(false);
                } else {
                    setToolBarBackground("#ca062c");
                    setToolbarIsShow(true);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < MetroBottomNavData.getFragments().size(); i++) {
            bottomTabMenu.addTab(bottomTabMenu.newTab().setCustomView(MetroBottomNavData.getView(MetroActivity.this, i)));
        }

    }
}