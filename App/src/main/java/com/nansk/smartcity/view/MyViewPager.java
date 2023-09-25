package com.nansk.smartcity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * @author 南山客
 * @email 2771557108@qq.com
 * @date 2021年06月22日
 */
public class MyViewPager extends ViewPager {

    public MyViewPager(Context context) {
        super(context);
    }
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childSize = getChildCount();
        int maxHeight = 0;
        for (int i = 0; i < childSize; i++) {
        View child = getChildAt(i);
        child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        //找到最大子View的高度
        if (child.getMeasuredHeight() > maxHeight) {
                maxHeight = child.getMeasuredHeight();
        }
        }
        //重新设置ViewPager容器的高度
        if (maxHeight > 0) {
            setMeasuredDimension(getMeasuredWidth(), maxHeight);
        }

    }

}
