package com.nansk.smartcity.beans.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 21:29
 * @Description 新闻评论列表
 */

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 17:55
 * @description
 */

public class LivingPressCommentListBean {

    /**
     * total : 1
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"appType":"living","newsId":180,"content":"这真是个好消息","commentDate":"2021-05-11 21:47:46","userId":2,"likeNum":0,"userName":"user1","newsTitle":"卓创资讯：猪价放量急跌 多地猪价破\u201c10\u201d"}]
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
         * id : 11
         * appType : living
         * newsId : 180
         * content : 这真是个好消息
         * commentDate : 2021-05-11 21:47:46
         * userId : 2
         * likeNum : 0
         * userName : user1
         * newsTitle : 卓创资讯：猪价放量急跌 多地猪价破“10”
         */

        public Object searchValue;
        public Object createBy;
        public Object createTime;
        public Object updateBy;
        public Object updateTime;
        public Object remark;
        public int id;
        public String appType;
        public int newsId;
        public String content;
        public String commentDate;
        public int userId;
        public int likeNum;
        public String userName;
        public String newsTitle;

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
