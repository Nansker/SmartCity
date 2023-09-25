package com.nansk.smartcity.beans.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 20:53
 * @description 领取乘车卡
 */

public class MetroGetCardBean {

    /**
     * idCard : 210211199909090014
     * phonenumber : 13800000000
     * userName : 王大卫
     */

    public String idCard;
    public String phonenumber;
    public String userName;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
