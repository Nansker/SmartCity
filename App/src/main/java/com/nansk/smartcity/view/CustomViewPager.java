package com.nansk.smartcity.view;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 23:56
 * @Description
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/14 10:19
 * @Description
 */

public class CustomViewPager extends ViewPager {
    private boolean enabled;

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
