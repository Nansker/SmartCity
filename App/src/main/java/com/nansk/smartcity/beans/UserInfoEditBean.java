package com.nansk.smartcity.beans;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 15:35
 * @description
 */

public class UserInfoEditBean {

    /**
     * email : lixl@163.com
     * idCard : 210882199807251656
     * nickName : 大卫王
     * phonenumber : 15898125461
     * sex : 0
     */

    public String email;
    public String idCard;
    public String nickName;
    public String phonenumber;
    public String sex;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
