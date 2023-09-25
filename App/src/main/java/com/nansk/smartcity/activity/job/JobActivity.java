package com.nansk.smartcity.activity.job;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.ViewPagerFragmentAdapter;
import com.nansk.smartcity.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 17:13
 * @Description 找工作首页
 */

public class JobActivity extends BaseActivity {

    private CustomViewPager bodyContainer;
    private TabLayout tabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_job);
        setToolBarBackground("#37c2bb");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView();
        initTabMenu();
    }

    private void initView() {
        bodyContainer = (CustomViewPager) findViewById(R.id.body_container);
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);

        bodyContainer.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),getFragments(),null));
    }

    public static List<Fragment> getFragments(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LookingJopFragment());
        fragments.add(new DeliveryRecordFragment());
        fragments.add(new PersonalResumeFragment());
        return fragments;
    }

    private void initTabMenu() {
        final String[] tabNames = new String[]{"找工作","投递记录","个人简历"};
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bodyContainer.setCurrentItem(tab.getPosition(),false);
                setToolBarTitle(tabNames[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i =0;i<3;i++){
            tabMenu.addTab(tabMenu.newTab().setText(tabNames[i]));
        }
    }


}