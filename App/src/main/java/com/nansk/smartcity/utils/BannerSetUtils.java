package com.nansk.smartcity.utils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 20:38
 * @Description
 */

import android.content.Context;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

public class BannerSetUtils {

    public static void setBannerStyle(Context context, Banner banner) {

        //设置banner显示样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new BannerLoader());
        banner.setDelayTime(4000);
        banner.isAutoPlay(true);
    }

}
