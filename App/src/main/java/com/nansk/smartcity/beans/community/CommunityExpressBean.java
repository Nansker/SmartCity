package com.nansk.smartcity.beans.community;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 20:58
 * @description
 */

public class CommunityExpressBean {

    /**
     * status : 已签收
     * params : 手机尾号6730的包裹
     * date : 极兔快递：2021-10-11 13:11:38签收
     */

    public String status;
    public String params;
    public String date;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
