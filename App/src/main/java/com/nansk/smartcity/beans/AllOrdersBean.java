package com.nansk.smartcity.beans;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 10:37
 * @Description 全部订单bean
 */

import java.util.List;

public class AllOrdersBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":21,"orderNo":"2021051001061885228","amount":58,"orderStatus":"已付款","userId":2,"payTime":"2021-05-10","name":"中影华臣影城","orderType":"movie","orderTypeName":"看电影"}]
     * total : 1
     */

    public int code;
    public String msg;
    public int total;
    public List<RowsBean> rows;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 21
         * orderNo : 2021051001061885228
         * amount : 58
         * orderStatus : 已付款
         * userId : 2
         * payTime : 2021-05-10
         * name : 中影华臣影城
         * orderType : movie
         * orderTypeName : 看电影
         */

        public int id;
        public String orderNo;
        public int amount;
        public String orderStatus;
        public int userId;
        public String payTime;
        public String name;
        public String orderType;
        public String orderTypeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderTypeName() {
            return orderTypeName;
        }

        public void setOrderTypeName(String orderTypeName) {
            this.orderTypeName = orderTypeName;
        }
    }
}
