package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 15:14
 * @Description 话费充值对象
 */

public class LifepayPhoneRechargeBean {

    /**
     * paymentType : 电子支付
     * phonenumber : 15898125172
     * rechargeAmount : 50
     * ruleId : 1
     * type : 2
     */

    public String paymentType;
    public String phonenumber;
    public Number rechargeAmount;
    public int ruleId;
    public String type;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Number getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Number rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
