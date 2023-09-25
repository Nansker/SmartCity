package com.nansk.smartcity.beans.protection;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 14:56
 * @description
 */

public class ProtectionRecyclingSubmitBean {
    private ProtectionAddressBean addressObj;
    private String classX;
    private String time;
    private String params;

    public ProtectionAddressBean getAddressObj() {
        return addressObj;
    }

    public void setAddressObj(ProtectionAddressBean addressObj) {
        this.addressObj = addressObj;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
