package com.nansk.smartcity.beans.activity;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 16:31
 * @Description
 */

import java.util.List;

public class ActivityListBean {


    /**
     * total : 1
     * rows : [{"id":1,"name":"相约长跑 10 公里","content":"<p>相约长跑 10 公里<\/p>","imgUrl":"/dev-api/profile/upload/image/2021/04/22/ecfee0c4-32 31-4bdc-89ad-ab1398710d16.png","categoryId":1,"recommend":"Y","signupNum":46,"likeNum":233,"status":"1","publishTime":"2021-04-22 10:38:02","categoryName":"跑步"}]
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
         * id : 1
         * name : 相约长跑 10 公里
         * content : <p>相约长跑 10 公里</p>
         * imgUrl : /dev-api/profile/upload/image/2021/04/22/ecfee0c4-32 31-4bdc-89ad-ab1398710d16.png
         * categoryId : 1
         * recommend : Y
         * signupNum : 46
         * likeNum : 233
         * status : 1
         * publishTime : 2021-04-22 10:38:02
         * categoryName : 跑步
         */

        public int id;
        public String name;
        public String content;
        public String imgUrl;
        public int categoryId;
        public String recommend;
        public int signupNum;
        public int likeNum;
        public String status;
        public String publishTime;
        public String categoryName;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public int getSignupNum() {
            return signupNum;
        }

        public void setSignupNum(int signupNum) {
            this.signupNum = signupNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }
}
