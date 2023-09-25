package com.nansk.smartcity.utils;



import android.content.Context;
import android.content.Intent;

import com.nansk.smartcity.activity.activity.HuodongActivity;
import com.nansk.smartcity.activity.bus.BusActivity;
import com.nansk.smartcity.activity.hospital.HospitalActivity;
import com.nansk.smartcity.activity.house.HouseActivity;
import com.nansk.smartcity.activity.job.JobActivity;
import com.nansk.smartcity.activity.metro.MetroActivity;
import com.nansk.smartcity.activity.movie.MovieMainActivity;
import com.nansk.smartcity.activity.parking.ParkingActivity;
import com.nansk.smartcity.activity.takeout.TakeoutMainActivity;
import com.nansk.smartcity.activity.traffic.TrafficActivity;
import com.nansk.smartcity.beans.service.ServiceJsonRows;
import com.nansk.smartcity.activity.lifepay.LifePayActivity;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/10 22:25
 * @Description 服务跳转监听类
 */
public class ServiceLinkListener {

    public final static void setLinkListener(Context context, ServiceJsonRows rows){
        Intent intent;
        switch (rows.getServiceName()){
            case "智慧巴士":
                intent = new Intent(context, BusActivity.class);
                context.startActivity(intent);
                break;
            case "门诊预约":
                intent = new Intent(context, HospitalActivity.class);
                context.startActivity(intent);
                break;
            case "停哪儿":
                intent = new Intent(context, ParkingActivity.class);
                context.startActivity(intent);
                break;
            case "外卖订餐":
                intent = new Intent(context, TakeoutMainActivity.class);
                context.startActivity(intent);
                break;
            case "找房子":
                intent = new Intent(context, HouseActivity.class);
                context.startActivity(intent);
                break;
            case "活动管理":
                intent = new Intent(context, HuodongActivity.class);
                context.startActivity(intent);
                break;
            case "生活缴费":
                intent = new Intent(context, LifePayActivity.class);
                context.startActivity(intent);
                break;
            case "找工作":
                intent = new Intent(context, JobActivity.class);
                context.startActivity(intent);
                break;
            case "智慧交管":
                intent = new Intent(context, TrafficActivity.class);
                context.startActivity(intent);
                break;
            case "城市地铁":
                intent = new Intent(context, MetroActivity.class);
                context.startActivity(intent);
                break;
            case "看电影":
                intent = new Intent(context, MovieMainActivity.class);
                context.startActivity(intent);
                break;
        }
    }

}
