package com.nansk.smartcity.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 23:43
 * @Description
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;
     private List<Fragment> fragments;

     private String[] tabNames;

    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragments,String[] tabNames) {
            super(fm);
            this.fragmentManager = fm;
            this.fragments = fragments;
            this.tabNames = tabNames;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //每次切换fragment时都保存状态
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        fragmentManager.beginTransaction().hide(fragments.get(position)).commit();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (tabNames != null){
            return tabNames[position];
        }
        return null;
    }
}
