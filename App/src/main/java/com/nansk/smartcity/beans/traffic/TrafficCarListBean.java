package com.nansk.smartcity.beans.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 18:30
 * @description
 */

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class TrafficCarListBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":2,"userId":2,"plateNo":"辽 B12345","engineNo":"1212XS123113","type":"小型汽车","userName":null}]
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

    public static class RowsBean implements Serializable {
        /**
         * id : 2
         * userId : 2
         * plateNo : 辽 B12345
         * engineNo : 1212XS123113
         * type : 小型汽车
         * userName : null
         */

        public int id;
        public int userId;
        public String plateNo;
        public String engineNo;
        public String type;
        public String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public void setEngineNo(String engineNo) {
            this.engineNo = engineNo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
