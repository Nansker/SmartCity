package com.nansk.smartcity.beans.bus;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 15:28
 * @Description 公交站点Bean
 */

import java.io.Serializable;
import java.util.List;

public class BusStopBean {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"linesId":1,"stepsId":1,"name":"光谷金融街","sequence":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"linesId":1,"stepsId":2,"name":"解放路","sequence":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"linesId":1,"stepsId":3,"name":"西安路","sequence":3},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"linesId":1,"stepsId":4,"name":"南湖大厦","sequence":4}]
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
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * linesId : 1
         * stepsId : 1
         * name : 光谷金融街
         * sequence : 1
         */

        public Object searchValue;
        public Object createBy;
        public Object createTime;
        public Object updateBy;
        public Object updateTime;
        public Object remark;
        public int linesId;
        public int stepsId;
        public String name;
        public int sequence;

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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getLinesId() {
            return linesId;
        }

        public void setLinesId(int linesId) {
            this.linesId = linesId;
        }

        public int getStepsId() {
            return stepsId;
        }

        public void setStepsId(int stepsId) {
            this.stepsId = stepsId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }
    }
}
