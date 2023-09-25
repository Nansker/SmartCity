package com.nansk.smartcity.beans.bus;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 11:24
 * @Description 公交线路
 */

import java.io.Serializable;
import java.util.List;

public class BusLinesBean {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2020-10-05 11:23:32","updateBy":null,"updateTime":"2020-10-21 11:23:35","remark":null,"params":{},"id":1,"name":"一号线","first":"光谷金融街","end":"南湖大厦","startTime":"6:30","endTime":"19:45","price":8,"mileage":"20"},{"searchValue":null,"createBy":null,"createTime":"2020-10-13 12:28:57","updateBy":null,"updateTime":"2020-10-22 12:29:00","remark":null,"params":{},"id":2,"name":"二号线","first":"光谷金融街","end":"万达广场","startTime":"6:30","endTime":"21:45","price":8,"mileage":"22"},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 16:57:07","updateBy":null,"updateTime":"2020-10-27 16:57:21","remark":null,"params":{},"id":3,"name":"三号线","first":"香炉礁","end":"金石沙滩","startTime":"6:30","endTime":"22:00","price":9,"mileage":"30"},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 16:59:03","updateBy":null,"updateTime":"2020-10-27 16:59:06","remark":null,"params":{},"id":4,"name":"十二号线","first":"河口","end":"辛寨子","startTime":"5:30","endTime":"23:00","price":12,"mileage":"40"}]
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

    public static class RowsBean implements Serializable {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2020-10-05 11:23:32
         * updateBy : null
         * updateTime : 2020-10-21 11:23:35
         * remark : null
         * params : {}
         * id : 1
         * name : 一号线
         * first : 光谷金融街
         * end : 南湖大厦
         * startTime : 6:30
         * endTime : 19:45
         * price : 8.0
         * mileage : 20
         */

        public Object searchValue;
        public Object createBy;
        public String createTime;
        public Object updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public String name;
        public String first;
        public String end;
        public String startTime;
        public String endTime;
        public Number price;
        public String mileage;

        public static class ParamsBean {
        }

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Number getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }
    }
}
