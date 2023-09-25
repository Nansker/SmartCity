package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 16:16
 * @Description 根据缴费编号查询缴费账单
 */

import java.io.Serializable;

public class LifePayBillBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":25,"billNo":"202104271358091","title":"第一季度电费","amount":100,"chargeUnit":"水电公司","address":"大连高新园区大华锦绣 3 号楼 2 单元 2803","paymentNo":"15674939","categoryId":2,"payStatus":"0"}
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
         * id : 25
         * billNo : 202104271358091
         * title : 第一季度电费
         * amount : 100
         * chargeUnit : 水电公司
         * address : 大连高新园区大华锦绣 3 号楼 2 单元 2803
         * paymentNo : 15674939
         * categoryId : 2
         * payStatus : 0
         */

        public int id;
        public String billNo;
        public String title;
        public Number amount;
        public String chargeUnit;
        public String address;
        public String paymentNo;
        public int categoryId;
        public String payStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Number getAmount() {
            return amount;
        }

        public void setAmount(Number amount) {
            this.amount = amount;
        }

        public String getChargeUnit() {
            return chargeUnit;
        }

        public void setChargeUnit(String chargeUnit) {
            this.chargeUnit = chargeUnit;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPaymentNo() {
            return paymentNo;
        }

        public void setPaymentNo(String paymentNo) {
            this.paymentNo = paymentNo;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }
    }
}
