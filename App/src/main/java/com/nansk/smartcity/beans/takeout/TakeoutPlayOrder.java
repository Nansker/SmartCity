package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 21:12
 * @Description 支付订单对对象
 */

public class TakeoutPlayOrder {

    /**
     * orderNo : 2021051120444612594
     * paymentType : 电子支付
     */

    public String orderNo;
    public String paymentType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
