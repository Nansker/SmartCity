package com.nansk.smartcity.beans.hospital;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 20:05
 * @Description
 */

import java.io.Serializable;

public class ReservationsBean implements Serializable {

    /**
     * categoryId : 3
     * money : 6
     * patientName : 张三
     * reserveTime : 2021-09-10 10:30
     * type : 1
     */

    public int categoryId;
    public Number money;
    public String patientName;
    public String categoryName;
    public String reserveTime;
    public String type;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Number getMoney() {
        return money;
    }

    public void setMoney(Number money) {
        this.money = money;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
