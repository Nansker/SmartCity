package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 14:52
 * @Description 话费充值优惠政策
 */

import java.util.List;


public class LifepayPhoneRuleBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"ruleId":1,"rechargeAmount":50,"backAmount":1,"sort":1,"status":"1"},{"ruleId":2,"rechargeAmount":100,"backAmount":2,"sort":2,"status":"1"}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ruleId : 1
         * rechargeAmount : 50
         * backAmount : 1
         * sort : 1
         * status : 1
         */

        public int ruleId;
        public Number rechargeAmount;
        public Number backAmount;
        public int sort;
        public String status;

        public int getRuleId() {
            return ruleId;
        }

        public void setRuleId(int ruleId) {
            this.ruleId = ruleId;
        }

        public Number getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(Number rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public Number getBackAmount() {
            return backAmount;
        }

        public void setBackAmount(Number backAmount) {
            this.backAmount = backAmount;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
