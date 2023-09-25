package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 18:50
 * @Description
 */

import java.util.List;

public class LifepayRecordBean {

    /**
     * total : 12
     * rows : [{"id":16,"paymentNo":"15666083","billNo":"202104140820372","amount":56,"categoryId":2,"paymentType":"电子支付","rechargeTime":"2021-03-23 14:01:44","userId":2,"liveName":"水费","imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/08/72163ab8-832a-45e0-8d06-44546294affb.png"},"......"]
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
         * id : 16
         * paymentNo : 15666083
         * billNo : 202104140820372
         * amount : 56
         * categoryId : 2
         * paymentType : 电子支付
         * rechargeTime : 2021-03-23 14:01:44
         * userId : 2
         * liveName : 水费
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021 /05/08/72163ab8-832a-45e0-8d06-44546294affb.png
         */

        public int id;
        public String paymentNo;
        public String billNo;
        public int amount;
        public int categoryId;
        public String paymentType;
        public String rechargeTime;
        public int userId;
        public String liveName;
        public String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPaymentNo() {
            return paymentNo;
        }

        public void setPaymentNo(String paymentNo) {
            this.paymentNo = paymentNo;
        }

        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
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

        public String getLiveName() {
            return liveName;
        }

        public void setLiveName(String liveName) {
            this.liveName = liveName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
