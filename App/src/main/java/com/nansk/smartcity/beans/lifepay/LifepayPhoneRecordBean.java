package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 10:31
 * @Description
 */

import java.util.List;

public class LifepayPhoneRecordBean {

    /**
     * total : 2
     * rows : [{"id":15,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":20,"paymentAmount":20,"paymentType":"电子支付","rechargeTime":"2021-05-10 13:51:52","userId":2}]
     * code : 200
     * msg : 查询成功
     */

    public int total;
    public int code;
    public String msg;
    public List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 15
         * title : 话费充值
         * phonenumber : 13800000000
         * rechargeAmount : 20
         * paymentAmount : 20
         * paymentType : 电子支付
         * rechargeTime : 2021-05-10 13:51:52
         * userId : 2
         */

        public int id;
        public String title;
        public String phonenumber;
        public Number rechargeAmount;
        public Number paymentAmount;
        public String paymentType;
        public String rechargeTime;
        public int userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public Number getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(Number paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getRechargeTime() {
            return rechargeTime;
        }

        public void setRechargeTime(String rechargeTime) {
            this.rechargeTime = rechargeTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
