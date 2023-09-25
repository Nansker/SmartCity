package com.nansk.smartcity.beans.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 17:20
 * @description
 */

public class MovieTheatreDetailsBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:52:17","updateBy":"1111129","updateTime":"2021-05-29 16:39:20","remark":null,"params":{},"id":11,"name":"金逸影城","cover":"/prod-api/profile/upload/image/2021/05/29/e2911af3-b632-497f-9c15-bfb0401f522a.jpg","province":"辽宁省","city":"大连市","area":"沙河口区","address":"西安路99号福佳新天地5层","score":91,"tags":null,"brand":null,"distance":"1000米","status":"Y","movieId":null,"timesId":null}
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

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : 1
         * createTime : 2021-05-08 22:52:17
         * updateBy : 1111129
         * updateTime : 2021-05-29 16:39:20
         * remark : null
         * params : {}
         * id : 11
         * name : 金逸影城
         * cover : /prod-api/profile/upload/image/2021/05/29/e2911af3-b632-497f-9c15-bfb0401f522a.jpg
         * province : 辽宁省
         * city : 大连市
         * area : 沙河口区
         * address : 西安路99号福佳新天地5层
         * score : 91
         * tags : null
         * brand : null
         * distance : 1000米
         * status : Y
         * movieId : null
         * timesId : null
         */

        public Object searchValue;
        public String createBy;
        public String createTime;
        public String updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public String name;
        public String cover;
        public String province;
        public String city;
        public String area;
        public String address;
        public int score;
        public String tags;
        public String brand;
        public String distance;
        public String status;
        public int movieId;
        public int timesId;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public int getTimesId() {
            return timesId;
        }

        public void setTimesId(int timesId) {
            this.timesId = timesId;
        }
    }
}
