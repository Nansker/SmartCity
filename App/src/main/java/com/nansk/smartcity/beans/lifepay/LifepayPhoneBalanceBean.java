package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 13:55
 * @Description 话费余额
 */

import java.io.Serializable;

public class LifepayPhoneBalanceBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":1,"phonenumber":"13800000000","balance":20,"operator":"中国移动"}
     */

    public String msg;
    public int code;
    public DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * phonenumber : 13800000000
         * balance : 20
         * operator : 中国移动
         */

        public int id;
        public String phonenumber;
        public Number balance;
        public String operator;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public Number getBalance() {
            return balance;
        }

        public void setBalance(Number balance) {
            this.balance = balance;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }

}
