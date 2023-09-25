package com.nansk.smartcity.activity.traffic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.ViewPagerFragmentAdapter;
import com.nansk.smartcity.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 12:52
 * @description 服务网点首页
 */

public class TrafficServiceActivity extends BaseActivity {

    private TabLayout tabMenu;
    private CustomViewPager bodyContainer;

    private String[] tabNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_service);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("办事网点");

        initView();
        initTabMenu();
    }

    /**
     * @Create 2021/10/9 13:23
     * @Role
     * @Param
     */
    private void initTabMenu() {
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bodyContainer.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0;i<tabNames.length;i++){
            tabMenu.addTab(tabMenu.newTab().setText(tabNames[i]));
        }
    }

    private void initView() {

        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        bodyContainer = (CustomViewPager) findViewById(R.id.body_container);

        tabNames = new String[]{"资讯中心","服务网点","服务中心"};
        bodyContainer.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),getFragments(),null));
    }

    /**
     * @Create 2021/10/9 13:14
     * @Role
     * @Param
     */
    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TrafficServicePressFragment());
        fragments.add(new TrafficServiceBranchesFragment());
        fragments.add(new TrafficServiceCenterFragment());
        return fragments;
    }

}