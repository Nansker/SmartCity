package com.nansk.smartcity.activity.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.ViewPagerFragmentAdapter;
import com.nansk.smartcity.view.CustomViewPager;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 08:29
 * @description 看电影首页
 */

public class MovieMainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private CustomViewPager bodyContainer;
    public static TabLayout tabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_movie_main);
        setToolBarBackground("#ff3e5d");
        initView();
    }

    private void initView() {
        bodyContainer = (CustomViewPager) findViewById(R.id.body_container);
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        List<Fragment> fragments = MovieBottomNavData.getFragments();
        ViewPagerFragmentAdapter pagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, null);
        bodyContainer.setAdapter(pagerFragmentAdapter);

        tabMenu.addOnTabSelectedListener(this);
        for (int i = 0; i < MovieBottomNavData.getFragments().size(); i++) {
            tabMenu.addTab(tabMenu.newTab().setCustomView(MovieBottomNavData.getTabView(MovieMainActivity.this, i)));
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        bodyContainer.setCurrentItem(tab.getPosition(), false);
        setToolBarTitle(MovieBottomNavData.names[tab.getPosition()]);
        for (int i = 0; i < tabMenu.getTabCount(); i++) {
            View customView = tabMenu.getTabAt(i).getCustomView();
            ImageView iconIv = (ImageView) customView.findViewById(R.id.icon_iv);
            TextView textTv = (TextView) customView.findViewById(R.id.text_tv);
            if (tab.getPosition() == i) {
                iconIv.setColorFilter(Color.parseColor("#ff3e5d"));
                textTv.setTextColor(Color.parseColor("#ff3e5d"));
            } else {
                iconIv.setColorFilter(Color.parseColor("#999999"));
                textTv.setTextColor(Color.parseColor("#999999"));
            }
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}