package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 14:40
 * @Description 店家菜品分类
 */

import java.util.List;

public class TakeoutCategoryBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:42:01","updateBy":null,"updateTime":"2021-05-09 14:34:03","remark":null,"params":{},"id":5,"name":"黄焖鸡套餐","sellerId":4,"themeId":1,"sort":1},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 14:34:40","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":26,"name":"特色小吃","sellerId":4,"themeId":1,"sort":2},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 14:34:52","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":27,"name":"黄焖系列","sellerId":4,"themeId":1,"sort":3}]
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
         * searchValue : null
         * createBy : null
         * createTime : 2021-05-08 15:42:01
         * updateBy : null
         * updateTime : 2021-05-09 14:34:03
         * remark : null
         * params : {}
         * id : 5
         * name : 黄焖鸡套餐
         * sellerId : 4
         * themeId : 1
         * sort : 1
         */

        public Object searchValue;
        public Object createBy;
        public String createTime;
        public Object updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public String name;
        public int sellerId;
        public int themeId;
        public int sort;

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

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
