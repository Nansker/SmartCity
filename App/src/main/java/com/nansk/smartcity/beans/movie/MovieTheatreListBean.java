package com.nansk.smartcity.beans.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 12:31
 * @description 影院列表
 */

import java.util.List;

public class MovieTheatreListBean {

    /**
     * total : 14
     * rows : [{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:52:17","updateBy":"1111129","updateTime":"2021-05-29 16:39:20","remark":null,"params":{},"id":11,"name":"金逸影城","cover":"/prod-api/profile/upload/image/2021/05/29/e2911af3-b632-497f-9c15-bfb0401f522a.jpg","province":"辽宁省","city":"大连市","area":"沙河口区","address":"西安路99号福佳新天地5层","score":91,"tags":null,"brand":null,"distance":"1000米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:38:21","updateBy":"1111129","updateTime":"2021-05-29 16:45:58","remark":null,"params":{},"id":5,"name":"中影星美国际影城","cover":"/prod-api/profile/upload/image/2021/05/29/4420d9af-932c-4b1e-8d47-d9a309963e9c.jpg","province":"辽宁省","city":"大连市","area":"沙河口区","address":"大连高新技术产业园区软件园路1a-12号","score":93,"tags":null,"brand":null,"distance":"100米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:40:28","updateBy":"1111129","updateTime":"2021-05-29 16:46:32","remark":null,"params":{},"id":6,"name":"中影华臣影城","cover":"/prod-api/profile/upload/image/2021/05/29/e739fdf9-0963-4e70-b428-b56faf4e39df.jpg","province":"辽宁省","city":"大连市","area":"沙河口区","address":"数码路南段13号锦辉商城东财书香园点2楼","score":91,"tags":null,"brand":null,"distance":"1100米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:42:55","updateBy":"1111129","updateTime":"2021-05-29 16:47:05","remark":null,"params":{},"id":7,"name":"SFC星感觉国际影城","cover":"/prod-api/profile/upload/image/2021/05/29/142f4df2-3601-470b-bcc4-4c8cbfbbac36.jpg","province":"辽宁省","city":"大连市","area":"甘井子区","address":"大连高新技术产业园区博翔街49号2层201室","score":89,"tags":null,"brand":null,"distance":"1700米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:45:26","updateBy":"1111129","updateTime":"2021-05-29 16:47:34","remark":null,"params":{},"id":8,"name":"中影华臣影城（黑石礁店）","cover":"/prod-api/profile/upload/image/2021/05/29/44acb4a7-1a06-4d4b-9997-879d22fd525c.jpg","province":"辽宁省","city":"大连市","area":"沙河口区","address":"中山路673号富丽庭生活广场3层","score":96,"tags":null,"brand":null,"distance":"1900米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:35:12","updateBy":"1111129","updateTime":"2021-05-29 16:48:21","remark":null,"params":{},"id":4,"name":"奥斯卡国际影城","cover":"/prod-api/profile/upload/image/2021/05/29/413f0efa-0558-4e7e-a205-97e66d6ffa8c.jpg","province":"辽宁省","city":"大连市","area":"甘井子区","address":"高新园区锦辉购物广场5楼","score":93,"tags":null,"brand":null,"distance":"2500米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:29:52","updateBy":"1","updateTime":"2021-05-12 10:36:55","remark":null,"params":{},"id":3,"name":"万达影城","cover":"/prod-api/profile/upload/image/2021/05/12/3f7ec6a0-e846-44e0-be9a-16292f4d815c.jpg","province":"辽宁省","city":"大连市","area":"甘井子区","address":"高新园区黄浦路500号万达广场4层","score":97,"tags":null,"brand":null,"distance":"3000米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:48:03","updateBy":"1111129","updateTime":"2021-05-29 16:48:48","remark":null,"params":{},"id":9,"name":"华美影城","cover":"/prod-api/profile/upload/image/2021/05/29/5a6a4c93-e894-4309-ad03-3c4ba47e1838.jpg","province":"辽宁省","city":"大连市","area":"沙河口区","address":"黄河路1105号马栏广场逸彩城3层","score":96,"tags":null,"brand":null,"distance":"3800米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:50:52","updateBy":"1111129","updateTime":"2021-05-29 16:49:20","remark":null,"params":{},"id":10,"name":"博纳国际影城IMAX","cover":"/prod-api/profile/upload/image/2021/05/29/c74fa494-4da5-4198-af7a-16c7afa11a1e.jpg","province":"辽宁省","city":"大连市","area":"沙河口区","address":"西安路103号中央大道旅游购物中心5层","score":96,"tags":null,"brand":null,"distance":"5000米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:53:51","updateBy":"1111129","updateTime":"2021-05-29 16:49:55","remark":null,"params":{},"id":12,"name":"百丽宫影城","cover":"/prod-api/profile/upload/image/2021/05/29/c433d32f-df24-4481-8e76-4068fbb00743.jpg","province":"辽宁省","city":"大连市","area":"西岗区","address":"五四路66号恒隆广场4层","score":98,"tags":null,"brand":null,"distance":"6300米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:55:39","updateBy":"1111129","updateTime":"2021-05-29 16:50:49","remark":null,"params":{},"id":13,"name":"北联影城","cover":"/prod-api/profile/upload/image/2021/05/29/694fcb5b-13ce-4b5a-85a6-25f69db48c4e.jpg","province":"辽宁省","city":"大连市","area":"甘井子区","address":"柳韵园1-13号3层","score":94,"tags":null,"brand":null,"distance":"6400","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 22:57:43","updateBy":"1111129","updateTime":"2021-05-29 16:51:11","remark":null,"params":{},"id":14,"name":"上影国际影城","cover":"/prod-api/profile/upload/image/2021/05/29/788aa177-ee32-44a9-970f-435691105770.jpg","province":"辽宁省","city":"大连市","area":"西岗区","address":"香炉礁百年港奥特莱斯A2区2层","score":93,"tags":null,"brand":null,"distance":"7500米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 23:00:30","updateBy":"1111129","updateTime":"2021-05-29 16:51:33","remark":null,"params":{},"id":15,"name":"华夏为莱激光4K影城","cover":"/prod-api/profile/upload/image/2021/05/29/7faa7f94-27d7-49be-9e76-acfb2f375370.jpg","province":"辽宁省","city":"大连市","area":"甘井子区","address":"张前路第五郡亿家缘广场内环2层","score":93,"tags":null,"brand":null,"distance":"7500米","status":"Y","movieId":null,"timesId":null},{"searchValue":null,"createBy":"1","createTime":"2021-05-08 23:02:40","updateBy":"1111129","updateTime":"2021-05-29 16:51:52","remark":null,"params":{},"id":16,"name":"CGV影城","cover":"/prod-api/profile/upload/image/2021/05/29/634a235c-d9e7-40e3-ba3a-d3d7a961c617.jpg","province":"辽宁省","city":"大连市","area":"中山区","address":"中山路129-3号柏威年购物广场4层","score":96,"tags":null,"brand":null,"distance":"8400米","status":"Y","movieId":null,"timesId":null}]
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
        public Object brand;
        public String distance;
        public String status;
        public String movieId;
        public String timesId;

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

        public Object getBrand() {
            return brand;
        }

        public void setBrand(Object brand) {
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

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getTimesId() {
            return timesId;
        }

        public void setTimesId(String timesId) {
            this.timesId = timesId;
        }
    }
}
