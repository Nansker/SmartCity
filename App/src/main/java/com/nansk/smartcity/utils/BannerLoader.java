package com.nansk.smartcity.utils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 15:52
 * @Description
 */

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

public class BannerLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        /**
         * @Create 2021/10/1 14:44
         * @Role 直接使用context可能会出现
         * @Param 当你的Activity重新创建并且Glide中的context是旧的时，这个问题最容易发生。
         * 例如当你有一个CustomAdapter(ArrayList list, Context context),
         * 并且在MainActivity或Fragment重新创建时，你不会将新的context传递给适配器。
         * 然后Glide告诉你我正在使用的context对象不再存在。
         */
            Glide.with(imageView.getContext()).load(o).into(imageView);
    }
}
