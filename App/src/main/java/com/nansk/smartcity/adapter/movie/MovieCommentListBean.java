package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 20:09
 * @description
 */

import java.util.List;

public class MovieCommentListBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":2,"newsId":1,"content":"好看","commentDate":"2021-04-02 00:00:00","userId":1,"likeNum":1,"userName":"张三"}]
     * total : 1
     */

    public int code;
    public String msg;
    public String total;
    public List<RowsBean> rows;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 2
         * newsId : 1
         * content : 好看
         * commentDate : 2021-04-02 00:00:00
         * userId : 1
         * likeNum : 1
         * userName : 张三
         */

        public int id;
        public int newsId;
        public String content;
        public String commentDate;
        public int userId;
        public int likeNum;
        public String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
