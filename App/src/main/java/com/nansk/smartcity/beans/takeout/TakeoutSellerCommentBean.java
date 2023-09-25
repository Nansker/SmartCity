package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 17:13
 * @Description 商家评论
 */

import java.util.List;

public class TakeoutSellerCommentBean {

    /**
     * total : 12
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"userId":2,"sellerId":1,"score":5,"content":"很好吃","likeNum":100,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-04-18 13:02:00","replyContent":"谢谢你，欢迎下次光临","replyTime":"2021-04-19 11:02:00"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"userId":2,"sellerId":1,"score":5,"content":"很划算，很好吃，分量大，经济实惠","likeNum":5,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-04-28 11:27:50","replyContent":"谢谢支持","replyTime":"2021-04-29 11:28:01"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"userId":2,"sellerId":1,"score":5,"content":"真好吃啊","likeNum":0,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-04-28 11:29:03","replyContent":"谢谢","replyTime":"2021-05-14 17:00:00"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"userId":2,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-05-11 21:19:36","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"userId":1,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"admin","nickName":"超级管理员","avatar":"","commentDate":"2021-05-15 16:19:23","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"userId":1,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"admin","nickName":"超级管理员","avatar":"","commentDate":"2021-05-15 16:27:29","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"userId":1,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"admin","nickName":"超级管理员","avatar":"","commentDate":"2021-05-15 16:34:56","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"userId":2,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-05-15 20:44:15","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"userId":2,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-05-16 17:47:05","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"userId":2,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-05-16 17:47:07","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":13,"userId":2,"sellerId":1,"score":5,"content":"真好吃，下次还来","likeNum":0,"sellerName":"尊宝比萨","userName":"user1","nickName":"测试用户01","avatar":"","commentDate":"2021-05-16 17:47:10","replyContent":null,"replyTime":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":15,"userId":1111118,"sellerId":1,"score":5,"content":"行","likeNum":0,"sellerName":"尊宝比萨","userName":"jianhao","nickName":null,"avatar":"","commentDate":"2021-05-16 18:42:40","replyContent":null,"replyTime":null}]
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
         * id : 2
         * userId : 2
         * sellerId : 1
         * score : 5
         * content : 很好吃
         * likeNum : 100
         * sellerName : 尊宝比萨
         * userName : user1
         * nickName : 测试用户01
         * avatar :
         * commentDate : 2021-04-18 13:02:00
         * replyContent : 谢谢你，欢迎下次光临
         * replyTime : 2021-04-19 11:02:00
         */

        public Object searchValue;
        public Object createBy;
        public Object createTime;
        public Object updateBy;
        public Object updateTime;
        public Object remark;
        public int id;
        public int userId;
        public int sellerId;
        public int score;
        public String content;
        public int likeNum;
        public String sellerName;
        public String userName;
        public String nickName;
        public String avatar;
        public String commentDate;
        public String replyContent;
        public String replyTime;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(String commentDate) {
            this.commentDate = commentDate;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(String replyTime) {
            this.replyTime = replyTime;
        }
    }
}
