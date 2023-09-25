package com.nansk.smartcity.beans;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 08:26
 * @description
 */

public class DayBean {
    String time; //时间
    private String week; //星期几
    private boolean isToday;//是否为今天

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}
