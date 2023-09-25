package com.nansk.smartcity.activity.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 08:43
 * @description 影院底部导航栏数据
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

public class MovieBottomNavData {
    private static int[] icons = new int[]{R.mipmap.home,R.mipmap.allservice,R.mipmap.xiaolian};
    public static String[] names = new String[]{"首页", "影院", "影片"};


    public static List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MovieHomeFragment());
        fragments.add(new MovieTheatreFragment());
        fragments.add(new MovieFilmFragment());
        return fragments;
    }

    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_bottom_nav, null);
        ImageView iconIv = (ImageView) view.findViewById(R.id.icon_iv);
        TextView textTv = (TextView) view.findViewById(R.id.text_tv);

        iconIv.setImageResource(icons[position]);
        textTv.setText(names[position]);
        iconIv.setColorFilter(Color.parseColor("#999999"));
        textTv.setTextColor(Color.parseColor("#999999"));
        return view;
    }
}
