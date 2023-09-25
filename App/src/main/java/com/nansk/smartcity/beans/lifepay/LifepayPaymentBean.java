package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 21:40
 * @Description
 */

public class LifepayPaymentBean {

    /**
     * billNo : 202104240819072
     * paymentNo : 15674939
     * paymentType : 电子支付
     */

    public String billNo;
    public String paymentNo;
    public String paymentType;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
