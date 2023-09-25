package com.nansk.smartcity.beans.hospital;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 16:37
 * @Description
 */

import java.io.Serializable;

public class AddPatientBean implements Serializable {

    /**
     * address : 大连市甘井子区
     * birthday : 2001-09-10
     * cardId : 210882199001302318
     * name : 张三
     * sex : 0
     * tel : 15800000000
     */

    public String address;
    public String birthday;
    public String cardId;
    public String name;
    public String sex;
    public String tel;

    public AddPatientBean() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
