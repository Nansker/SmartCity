package com.nansk.smartcity;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 11:22
 * @Description 底部导航栏数据
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.fragment.DesignFragment;
import com.nansk.smartcity.fragment.HomeFragment;
import com.nansk.smartcity.fragment.PressFragment;
import com.nansk.smartcity.fragment.PersonFragment;
import com.nansk.smartcity.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomNavData {
    public static final int[] bottomNavImages = new int[]
            {R.mipmap.home, R.mipmap.services,R.mipmap.metro_person, R.mipmap.view, R.mipmap.prson};
    public static final String[] bottomNavText = new String[]{"首页", "全部服务", "自主设计", "新闻", "个人中心"};

    //主页获取fragment页面
    public static List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ServiceFragment());
        fragments.add(new DesignFragment());
        fragments.add(new PressFragment());
        fragments.add(new PersonFragment());
        return fragments;
    }

    //获取tab显示的内容
    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_bottom_nav, null);
        ImageView tabContentImage = (ImageView) view.findViewById(R.id.icon_iv);
        TextView tabContentText = (TextView) view.findViewById(R.id.text_tv);

        tabContentImage.setImageResource(BottomNavData.bottomNavImages[position]);
        tabContentText.setText(BottomNavData.bottomNavText[position]);

        tabContentImage.setColorFilter(Color.parseColor("#666666"));
        tabContentText.setTextColor(Color.parseColor("#666666"));
        return view;
    }

}
