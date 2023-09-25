package com.nansk.smartcity.beans.protection;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 14:36
 * @description
 */

import java.io.Serializable;

public class ProtectionAddressBean implements Serializable {
    private String name;
    private String tel;
    private String address;
    private String addressNo;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }
}
