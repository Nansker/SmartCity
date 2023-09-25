package com.nansk.smartcity.activity.takeout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.ViewPagerFragmentAdapter;
import com.nansk.smartcity.fragment.TakeoutHomeFragment;
import com.nansk.smartcity.fragment.TakeoutOrderFragment;
import com.nansk.smartcity.fragment.TakeoutPersonFragment;
import com.nansk.smartcity.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 16:49
 * @Description 外卖首页
 */

public class TakeoutMainActivity extends BaseActivity {
    private List<Fragment> fragments;

    private  CustomViewPager bodyContainer;
    public static TabLayout bottomTabLayout;

    private TakeoutBottomNavData navData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_main_take_out);
        setToolBarBackground("#FFC107",0);
        setToolBarTitle("外卖订餐");
        setBarDivider(false,"");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView();
        initTabLayout();
    }


    private void initView() {
        bodyContainer = (CustomViewPager) findViewById(R.id.body_container);
        bottomTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        navData = new TakeoutBottomNavData();
        fragments = navData.getFragments();

        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, null);
        bodyContainer.setAdapter(adapter);

    }

    /**
     * @Create 2021/9/18 17:36
     * @Role 初始化底部导航栏
     * @Param
     */
    private void initTabLayout() {
        //设置监听事件
        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bodyContainer.setCurrentItem(tab.getPosition(), false);

                //设置图标颜色
                for (int i = 0; i < bottomTabLayout.getTabCount(); i++) {
                    View customView = bottomTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = customView.findViewById(R.id.icon_iv);
                    TextView text = customView.findViewById(R.id.text_tv);
                    //选中状态
                    if (i == tab.getPosition()) {
                        icon.setColorFilter(Color.parseColor("#FFFF9800"));
                        text.setTextColor(Color.parseColor("#FFFF9800"));
                    } else {
                        // 未选中状态
                        icon.setColorFilter(Color.parseColor("#666666"));
                        text.setTextColor(Color.parseColor("#666666"));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //添加内容
        for (int i = 0; i < 3; i++) {
            bottomTabLayout.addTab(bottomTabLayout.newTab().setCustomView(navData.getTabView(TakeoutMainActivity.this, i)));
        }
    }

    /**
     * @Create 2021/9/26 16:22
     * @Role 底部导航栏数据
     * @Param
     */
    static class TakeoutBottomNavData {
        private static int[] bottomNavImages = new int[]{
                R.mipmap.home, R.mipmap.order, R.mipmap.prson};

        public static String[] bottomNavText = new String[]{"首页", "订单", "个人中心"};

        public static final List<Fragment> getFragments() {
            ArrayList<Fragment> fragments = new ArrayList<>();
            fragments.add(new TakeoutHomeFragment());
            fragments.add(new TakeoutOrderFragment());
            fragments.add(new TakeoutPersonFragment());
            return fragments;
        }

        //获取tab显示的内容
        public static View getTabView(Context context, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_main_bottom_nav, null);
            ImageView tabContentImage = (ImageView) view.findViewById(R.id.icon_iv);
            TextView tabContentText = (TextView) view.findViewById(R.id.text_tv);

            tabContentImage.setImageResource(bottomNavImages[position]);
            tabContentText.setText(bottomNavText[position]);
            return view;
        }


    }

}