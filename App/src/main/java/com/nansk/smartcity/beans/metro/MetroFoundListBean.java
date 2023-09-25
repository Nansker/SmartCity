package com.nansk.smartcity.beans.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 16:52
 * @description
 */

import java.util.List;

public class MetroFoundListBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"publishDate":"2021-05-10","metroFoundList":[{"searchValue":null,"createBy":null,"createTime":"2021-05-10 11:36:06","updateBy":null,"updateTime":null,"remark":"","params":{},"id":6,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/ed997996-d26c-43f3-b169-86330362c90f.png","foundTime":"2021-05-09","foundPlace":"霍营地铁站","claimPlace":"霍营地铁站服务中心"},{"searchValue":null,"createBy":null,"createTime":"2021-05-10 11:36:37","updateBy":null,"updateTime":null,"remark":"","params":{},"id":7,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/41d5125b-2081-4a6c-ae55-55245b3cd4d1.png","foundTime":"2021-05-10","foundPlace":"五道口地铁站","claimPlace":"五道口地铁站服务中心"},{"searchValue":null,"createBy":null,"createTime":"2021-05-10 11:40:12","updateBy":null,"updateTime":null,"remark":"","params":{},"id":8,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/517e75e9-e1d4-4d6d-b467-09f3e7bcf209.png","foundTime":"2021-05-10","foundPlace":"王府井地铁站","claimPlace":"王府井地铁服务中心"}]},{"publishDate":"2021-05-08","metroFoundList":[{"searchValue":null,"createBy":null,"createTime":"2021-05-08 17:54:42","updateBy":null,"updateTime":"2021-05-10 11:35:11","remark":"","params":{},"id":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/ff165c4e-12a2-4c79-8c0d-39e944f52c67.png","foundTime":"2021-05-04","foundPlace":"西直门地铁口","claimPlace":"西直门地铁服务中心"}]},{"publishDate":"2021-04-06","metroFoundList":[{"searchValue":null,"createBy":null,"createTime":"2021-04-06 12:09:10","updateBy":null,"updateTime":"2021-05-10 11:35:03","remark":"","params":{},"id":4,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/66b2707a-5f08-4a20-8bd1-18da1c275af8.png","foundTime":"2021-04-06","foundPlace":"北京站","claimPlace":"北京站服务中心"}]},{"publishDate":"2021-04-04","metroFoundList":[{"searchValue":null,"createBy":null,"createTime":"2021-04-04 09:59:22","updateBy":null,"updateTime":"2021-05-10 11:34:45","remark":"","params":{},"id":2,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/c0f32adf-58f2-43ed-9962-96295abf133b.png","foundTime":"2021-04-04","foundPlace":"西直门地铁站A出口","claimPlace":"西直门地铁服务中心"},{"searchValue":null,"createBy":null,"createTime":"2021-04-04 10:01:46","updateBy":null,"updateTime":"2021-05-10 11:34:53","remark":"","params":{},"id":3,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/3dbabe35-1103-44b9-821b-321ca1da35c6.png","foundTime":"2021-04-04","foundPlace":"朝阳门站","claimPlace":"朝阳门地铁服务中心"}]},{"publishDate":"2021-04-02","metroFoundList":[{"searchValue":null,"createBy":null,"createTime":"2021-04-02 22:27:42","updateBy":null,"updateTime":"2021-05-10 11:34:36","remark":"","params":{},"id":1,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/7e393d16-53f9-4403-938a-cd0127f7dee5.png","foundTime":"2021-04-02","foundPlace":"望京西站A出口","claimPlace":"望京西站服务中心"}]}]
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
         * publishDate : 2021-05-10
         * metroFoundList : [{"searchValue":null,"createBy":null,"createTime":"2021-05-10 11:36:06","updateBy":null,"updateTime":null,"remark":"","params":{},"id":6,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/ed997996-d26c-43f3-b169-86330362c90f.png","foundTime":"2021-05-09","foundPlace":"霍营地铁站","claimPlace":"霍营地铁站服务中心"},{"searchValue":null,"createBy":null,"createTime":"2021-05-10 11:36:37","updateBy":null,"updateTime":null,"remark":"","params":{},"id":7,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/41d5125b-2081-4a6c-ae55-55245b3cd4d1.png","foundTime":"2021-05-10","foundPlace":"五道口地铁站","claimPlace":"五道口地铁站服务中心"},{"searchValue":null,"createBy":null,"createTime":"2021-05-10 11:40:12","updateBy":null,"updateTime":null,"remark":"","params":{},"id":8,"imgUrl":"/prod-api/profile/upload/image/2021/05/10/517e75e9-e1d4-4d6d-b467-09f3e7bcf209.png","foundTime":"2021-05-10","foundPlace":"王府井地铁站","claimPlace":"王府井地铁服务中心"}]
         */

        public String publishDate;
        public List<FoundListBean> metroFoundList;

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public List<FoundListBean> getMetroFoundList() {
            return metroFoundList;
        }

        public void setMetroFoundList(List<FoundListBean> metroFoundList) {
            this.metroFoundList = metroFoundList;
        }

        public static class FoundListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2021-05-10 11:36:06
             * updateBy : null
             * updateTime : null
             * remark :
             * params : {}
             * id : 6
             * imgUrl : /prod-api/profile/upload/image/2021/05/10/ed997996-d26c-43f3-b169-86330362c90f.png
             * foundTime : 2021-05-09
             * foundPlace : 霍营地铁站
             * claimPlace : 霍营地铁站服务中心
             */

            public Object searchValue;
            public Object createBy;
            public String createTime;
            public Object updateBy;
            public Object updateTime;
            public String remark;
            public int id;
            public String imgUrl;
            public String foundTime;
            public String foundPlace;
            public String claimPlace;

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

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getFoundTime() {
                return foundTime;
            }

            public void setFoundTime(String foundTime) {
                this.foundTime = foundTime;
            }

            public String getFoundPlace() {
                return foundPlace;
            }

            public void setFoundPlace(String foundPlace) {
                this.foundPlace = foundPlace;
            }

            public String getClaimPlace() {
                return claimPlace;
            }

            public void setClaimPlace(String claimPlace) {
                this.claimPlace = claimPlace;
            }
        }
    }
}
