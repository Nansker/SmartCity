package com.nansk.smartcity.beans.support;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 20:43
 * @description
 */

import java.io.Serializable;

public class SupportMyHelpBean implements Serializable {
    private String name;
    private String address;
    private String date;
    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
