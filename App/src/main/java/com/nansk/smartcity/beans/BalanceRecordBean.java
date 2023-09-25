package com.nansk.smartcity.beans;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 20:34
 * @description
 */

import java.util.List;

public class BalanceRecordBean {

    /**
     * total : 10
     * rows : [{"id":2,"appType":null,"userId":1,"event":"缴纳违章罚款","changeAmount":200,"changeType":"支出","userName":null,"changeTime":"2021-04-21 10:30:45"}]
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
         * id : 2
         * appType : null
         * userId : 1
         * event : 缴纳违章罚款
         * changeAmount : 200
         * changeType : 支出
         * userName : null
         * changeTime : 2021-04-21 10:30:45
         */

        public int id;
        public Object appType;
        public int userId;
        public String event;
        public Number changeAmount;
        public String changeType;
        public Object userName;
        public String changeTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getAppType() {
            return appType;
        }

        public void setAppType(Object appType) {
            this.appType = appType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public Number getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(Number changeAmount) {
            this.changeAmount = changeAmount;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public String getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(String changeTime) {
            this.changeTime = changeTime;
        }
    }
}
