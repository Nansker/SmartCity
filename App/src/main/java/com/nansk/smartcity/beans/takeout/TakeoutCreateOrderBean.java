package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 20:45
 * @Description
 */

public class TakeoutCreateOrderBean {

    /**
     * msg : 操作成功
     * code : 200
     * orderNo : 2021051120444612594
     */

    public String msg;
    public int code;
    public String orderNo;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
