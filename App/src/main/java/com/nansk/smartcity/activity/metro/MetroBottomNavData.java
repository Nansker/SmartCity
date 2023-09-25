package com.nansk.smartcity.activity.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 10:48
 * @description 底部导航栏数据
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.nansk.smartcity.R;

import java.util.ArrayList;
import java.util.List;

public class MetroBottomNavData {
    public static String[] names = new String[]{"首页","生活","乘车码","线路图","我的"};
    private static int[] icons = new int[]{R.mipmap.metro_home,R.mipmap.metro_life,
            R.mipmap.metro_code, R.mipmap.metro_line,R.mipmap.metro_person};

    public static List<Fragment> getFragments(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MetroHomeFragment());
        fragments.add(new MetroPressFragment());
        fragments.add(new MetroCardFragment());
        fragments.add(new MetroLineFragment());
        fragments.add(new MetroPersonFragment());
        return fragments;
    }

    public static View getView(Context context,int position){
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_bottom_nav, null);
        TextView textView = view.findViewById(R.id.text_tv);
        ImageView imageView = view.findViewById(R.id.icon_iv);

        textView.setText(MetroBottomNavData.names[position]);
        imageView.setImageResource(MetroBottomNavData.icons[position]);

        imageView.setColorFilter(Color.parseColor("#7F7F7F"));
        textView.setTextColor(Color.parseColor("#7F7F7F"));
        return  view;
    }
}
