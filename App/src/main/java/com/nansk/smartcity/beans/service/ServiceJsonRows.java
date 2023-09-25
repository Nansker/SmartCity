package com.nansk.smartcity.beans.service;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 20:54
 * @Description
 */

public class ServiceJsonRows {

        /**
         * searchValue : null
         * createBy : null
         * createTime : 2020-10-23 16:17:56
         * updateBy : null
         * updateTime : 2021-05-10 09:20:12
         * remark : null
         * params : {}
         * id : 17
         * serviceName : 停哪儿
         * serviceDesc : 查询停车场
         * serviceType : 车主服务
         * imgUrl : /prod-api/profile/upload/image/2021/05/10/814fc6c4-de48-48a1-95f8-de3e749e348d.png
         * pid : 1
         * link : park/index
         * sort : 0
         * isRecommend : N
         */

        public Object searchValue;
        public Object createBy;
        public String createTime;
        public Object updateBy;
        public String updateTime;
        public Object remark;
        public ParamsBean params;
        public int id;
        public String serviceName;
        public String serviceDesc;
        public String serviceType;
        public String imgUrl;
        public int pid;
        public String link;
        public int sort;
        public String isRecommend;

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

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceDesc() {
            return serviceDesc;
        }

        public void setServiceDesc(String serviceDesc) {
            this.serviceDesc = serviceDesc;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }
}
