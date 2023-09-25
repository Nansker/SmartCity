package com.nansk.smartcity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.BottomNavData;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.ViewPagerFragmentAdapter;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/07 17:37
 * @Description 主页
 */

public class MainActivity extends BaseActivity {
    long firstTime;

    public static TabLayout bottomTabLayout;

    private List<Fragment> fragments;
    public ViewPager bodyContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomView(R.layout.activity_main);
        setToolBarRightButton(false, "", null);
        setBackBarIsShow(false);
        setToolBarBackground("#007aff");
        setBarDivider(false,"");

        //保存底部导航栏不被键盘顶起
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initView();
        initTabLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getIP(MainActivity.this);
    }

    private void initView() {

        bodyContainer = findViewById(R.id.body_container);
        bottomTabLayout = findViewById(R.id.bottom_tab_layout);

        fragments = BottomNavData.getFragments();
        ViewPagerFragmentAdapter fragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, null);
        bodyContainer.setAdapter(fragmentAdapter);

    }

    /**
     * @Create 2021/9/8 11:34
     * @Role 初始化底部导航栏
     * @Param 这里应该先设置回调监听事件在addTab之前, 不然TabLayout将不会默认选中第一个
     */
    private void initTabLayout() {
        //设置监听事件
        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bodyContainer.setCurrentItem(tab.getPosition(), false);

                setToolBarTitle(BottomNavData.bottomNavText[tab.getPosition()]);
                if (tab.getPosition() == 4) {
                    setToolbarIsShow(false);
                } else {
                    setToolbarIsShow(true);
                }

                //设置图标颜色
                for (int i = 0; i < bottomTabLayout.getTabCount(); i++) {
                    View customView = bottomTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = customView.findViewById(R.id.icon_iv);
                    TextView text = customView.findViewById(R.id.text_tv);
                    //选中状态
                    if (i == tab.getPosition()) {
                        icon.setColorFilter(Color.parseColor("#007aff"));
                        text.setTextColor(Color.parseColor("#007aff"));
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
        for (int i = 0; i < fragments.size(); i++) {
            bottomTabLayout.addTab(bottomTabLayout.newTab().setCustomView(BottomNavData.getTabView(MainActivity.this, i)));
        }

    }

    //返回键监听
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {//如果两次按键时间间隔大于2000毫秒，则不退出
                showToast("在按一次退出",1200);
                firstTime = secondTime;//更新firstTime
                return true;
            } else {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

}
