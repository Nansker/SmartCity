package com.nansk.smartcity.utils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 11:25
 * @Description 公告轮播跳转页
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nansk.smartcity.activity.lifepay.LivingPressDetailsActivity;
import com.nansk.smartcity.activity.metro.MetroPressDetailsActivity;
import com.nansk.smartcity.activity.press.PressDetailsActivity;
import com.nansk.smartcity.beans.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

public class BannerLinkListener {

    public static void setBannerLink(final Context context, Banner banner, final List<BannerBean.RowsBean> rows) {

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {

                BannerBean.RowsBean targeObj = rows.get(i);
                String servModule = targeObj.getServModule();
                String appType = targeObj.getAppType();

                Intent intent = null;
                Bundle bundle = new Bundle();

                switch (servModule) {
                    case "新闻详情":
                        intent = new Intent(context, PressDetailsActivity.class);
                        bundle.putInt("pressId", targeObj.getTargetId());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                        break;

                    case "新闻资讯":
                        //生活缴费模块中的资讯
                        if (appType.equals("living")) {
                            //注：生活缴费模块没有提供轮播图接口，故此处展示不做跳转操作
                            Toast.makeText(context, "注：生活缴费模块没有提供轮播图接口，故此处展示不做跳转操作", Toast.LENGTH_SHORT).show();
                            //城市地铁模块中的资讯
                        } else if (appType.equals("metro")){
                            intent = new Intent(context, MetroPressDetailsActivity.class);
                            bundle.putInt("pressId", targeObj.getTargetId());
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }

                        break;
                }

            }
        });
    }
}
