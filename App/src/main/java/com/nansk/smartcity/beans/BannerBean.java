package com.nansk.smartcity.beans;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 11:20
 * @Description
 */

import java.util.List;

public class BannerBean {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":"admin","createTime":"2021-05-05 12:09:30","updateBy":"1","updateTime":"2021-05-12 08:54:22","remark":null,"params":{},"id":14,"appType":"living","status":"1","sort":1,"advTitle":"广告1","advImg":"/prod-api/profile/upload/image/2021/05/12/4aa010a5-2787-4aeb-aecb-6032d0d327cb.jpg","servModule":"新闻资讯","targetId":185,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-12 08:51:29","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":31,"appType":"living","status":"1","sort":2,"advTitle":"广告2","advImg":"/prod-api/profile/upload/image/2021/05/12/4a980d40-a1e7-4eab-84f8-c4a3eb873bee.jpg","servModule":"新闻资讯","targetId":2,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-12 08:51:52","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":32,"appType":"living","status":"1","sort":3,"advTitle":"广告3","advImg":"/prod-api/profile/upload/image/2021/05/12/c81fdbd5-3257-4f98-83eb-9c4bdfbd044a.jpg","servModule":"新闻资讯","targetId":3,"type":"2"}]
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
         * searchValue : null
         * createBy : admin
         * createTime : 2021-05-05 12:09:30
         * updateBy : 1
         * updateTime : 2021-05-12 08:54:22
         * remark : null
         * params : {}
         * id : 14
         * appType : living
         * status : 1
         * sort : 1
         * advTitle : 广告1
         * advImg : /prod-api/profile/upload/image/2021/05/12/4aa010a5-2787-4aeb-aecb-6032d0d327cb.jpg
         * servModule : 新闻资讯
         * targetId : 185
         * type : 2
         */

        public Object searchValue;
        public String createBy;
        public String createTime;
        public String updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public String appType;
        public String status;
        public int sort;
        public String advTitle;
        public String advImg;
        public String servModule;
        public int targetId;
        public String type;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
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

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getAdvTitle() {
            return advTitle;
        }

        public void setAdvTitle(String advTitle) {
            this.advTitle = advTitle;
        }

        public String getAdvImg() {
            return advImg;
        }

        public void setAdvImg(String advImg) {
            this.advImg = advImg;
        }

        public String getServModule() {
            return servModule;
        }

        public void setServModule(String servModule) {
            this.servModule = servModule;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
