package com.nansk.smartcity.utils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 10:52
 * @description 判断字符串非空
 */

import android.graphics.Color;
import android.view.View;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.DayBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SystemUtils {

    /**
     * @Create 2021/10/17 9:48
     * @Role 获取主题色
     * @Param
     */
    public static String getThemeColor(String module){
        String color = "#ffffff";
        switch (module){
            case "":
//                color = "";
                break;
        }
        return color;
    }

    /**
     * @Create 2021/10/12 14:37
     * @Role 字符串判断非空
     * @Param
     */
    public static String getValue(String value, String defaultValue) {
        if (value != null && value != "") {
            return value;
        }
        return defaultValue;
    }

    public static int getWeatherImg(String weather){
        int[] images = new int[]{R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,R.drawable.w8,R.drawable.w9,
                R.drawable.w10,R.drawable.w11,R.drawable.w12,R.drawable.w13,R.drawable.w14,R.drawable.w15,R.drawable.w16,R.drawable.w17,R.drawable.w18,R.drawable.w19,R.drawable.w20,R.drawable.w21,R.drawable.w22,R.drawable.w23,R.drawable.w24,R.drawable.w25,R.drawable.w26,R.drawable.w27,R.drawable.w28,};
        String[] names = new String[]{"暴雪","暴雨","暴雨转大暴雨","冰雹","大雪","大雪转暴雪","大雨","大雨转暴雨","冻雨","多云","浮尘","雷阵雨","雷阵雨伴冰雹"
        ,"晴","沙尘暴","雾","小雪","小雪转中雪","小雨","小雨转中雨","扬沙","阴","雨夹雪","阵雨","中雨","中雨转大雨"};

        for (int i =0;i<names.length;i++){
            if (weather.equals(names[i])){
                return images[i];
            }
        }
        return 0;
    }

    /**
     * @Create 2021/10/14 8:25
     * @Role 获取最近一周时间
     * @Param
     */
    public static List<DayBean> getLastWeek() {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        Calendar calendar = Calendar.getInstance();

        List<DayBean> dayBeans = new ArrayList<>();

        //设置日期为当天时间
        for (int i = 0; i < 6; i++) {
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, +i);
            Date time = calendar.getTime();
            String date = format.format(time);

            DayBean dayBean = new DayBean();
            dayBean.setTime(date);

            if (i == 0) {
                dayBean.setWeek("今天");
            } else if (i == 1) {
                dayBean.setWeek("明天");
            } else if (i == 2) {
                dayBean.setWeek("后天");
            } else {
                int w = calendar.get(Calendar.DAY_OF_WEEK);
                String week = getWeek(w);
                dayBean.setWeek(week);
            }

            dayBeans.add(dayBean);
        }
        return dayBeans;
    }

    private static String getWeek(int i) {
        String week = "";
        switch (i) {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;
        }
        return week;

    }

}
