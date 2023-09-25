package com.nansk.smartcity.beans.community;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 16:26
 * @description
 */

import java.io.Serializable;

public class CommunityCarBean implements Serializable {
    private String carNo;
    private String parkNo;
    private String cardNo;
    private String name;
    private String tel;
    private String group;
    private String address;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getParkNo() {
        return parkNo;
    }

    public void setParkNo(String parkNo) {
        this.parkNo = parkNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
