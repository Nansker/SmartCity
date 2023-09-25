package com.nansk.smartcity.beans.press;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/15 17:48
 * @Description 新闻评论列表
 */

import java.util.List;

public class PressCommentListObj {

    /**
     * total : 19
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"appType":"smart_city","newsId":28,"content":"支持","commentDate":"2021-05-11 17:30:25","userId":2,"likeNum":38,"userName":"user1","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"appType":"smart_city","newsId":28,"content":"赞同！！！","commentDate":"2021-05-11 21:27:24","userId":1111115,"likeNum":14,"userName":"lisi","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"appType":"smart_city","newsId":28,"content":"hao","commentDate":"2021-05-29 16:20:08","userId":1111130,"likeNum":1,"userName":"qwerty","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":137,"appType":"smart_city","newsId":28,"content":"tyy","commentDate":"2021-07-23 09:44:42","userId":1111122,"likeNum":5,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":139,"appType":"smart_city","newsId":28,"content":"你说啥","commentDate":"2021-07-23 10:46:51","userId":1111122,"likeNum":1,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":142,"appType":"smart_city","newsId":28,"content":"rtr ","commentDate":"2021-07-23 14:04:46","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":154,"appType":"smart_city","newsId":28,"content":"加个v互相学习学习吧：JackeyA06","commentDate":"2021-07-31 11:17:20","userId":1111175,"likeNum":0,"userName":"admine","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":186,"appType":"smart_city","newsId":28,"content":"新闻评论测试","commentDate":"2021-08-29 11:55:11","userId":1111381,"likeNum":0,"userName":"huajian","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":202,"appType":"smart_city","newsId":28,"content":"Hello","commentDate":"2021-09-13 09:19:01","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":203,"appType":"smart_city","newsId":28,"content":"Hello","commentDate":"2021-09-13 09:21:03","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":204,"appType":"smart_city","newsId":28,"content":"2314124","commentDate":"2021-09-13 09:21:19","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":205,"appType":"smart_city","newsId":28,"content":"Q3rqwe","commentDate":"2021-09-13 09:22:15","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":206,"appType":"smart_city","newsId":28,"content":"Sdfaf","commentDate":"2021-09-13 09:22:21","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":207,"appType":"smart_city","newsId":28,"content":"Hello World","commentDate":"2021-09-13 09:22:34","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":208,"appType":"smart_city","newsId":28,"content":"Hello","commentDate":"2021-09-13 09:25:08","userId":1111122,"likeNum":0,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":209,"appType":"smart_city","newsId":28,"content":"Hello Everyone, I\u2019m Wells","commentDate":"2021-09-13 09:25:32","userId":1111122,"likeNum":3,"userName":"test01","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":236,"appType":"smart_city","newsId":28,"content":"111","commentDate":"2021-09-14 20:24:29","userId":1111326,"likeNum":0,"userName":"B017","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":238,"appType":"smart_city","newsId":28,"content":"亲亲我","commentDate":"2021-09-14 22:54:58","userId":1111326,"likeNum":0,"userName":"B017","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":239,"appType":"smart_city","newsId":28,"content":"好","commentDate":"2021-09-15 13:50:32","userId":1111326,"likeNum":0,"userName":"B017","newsTitle":"iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏"}]
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
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 8
         * appType : smart_city
         * newsId : 28
         * content : 支持
         * commentDate : 2021-05-11 17:30:25
         * userId : 2
         * likeNum : 38
         * userName : user1
         * newsTitle : iPhone 13再爆猛料：不止刘海屏有望缩小，超大杯或将搭载LTPO屏
         */

        public int id;
        public String appType;
        public int newsId;
        public String content;
        public String commentDate;
        public int userId;
        public int likeNum;
        public String userName;
        public String newsTitle;

        public static class ParamsBean {
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

        public int getNewsId() {
            return newsId;
        }

        public void setNewsId(int newsId) {
            this.newsId = newsId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(String commentDate) {
            this.commentDate = commentDate;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNewsTitle() {
            return newsTitle;
        }

        public void setNewsTitle(String newsTitle) {
            this.newsTitle = newsTitle;
        }
    }
}
