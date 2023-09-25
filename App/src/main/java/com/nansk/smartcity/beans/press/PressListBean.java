package com.nansk.smartcity.beans.press;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 12:24
 * @Description 新闻列表
 */

import java.util.List;

public class PressListBean {

    /**
     * total : 5
     * rows : [{"id":180,"cover":"http://118.190.154.52:7777/profile/upload/image/2021/ 05/10/b88ef8ac-7538-4984-be59-3a768ec4709f.png","title":"卓创资讯：猪价放量急跌 多地猪价破\u201c10\u201d","subTitle":null,"content":"<p>据卓创监测，今日生猪均价跌至 19.00 元/公斤，较月 初跌 8.12%。多地开启急跌模式，\u201c10 元\u201d均价线下压到华南区域，仅极少数 销区均价仍在 20.00 元/公斤以上。大肥猪供应过剩，二次育肥补栏偏弱，预计 猪价或继续走低。<\/p>","status":"Y","publishDate":"2021-05-10","tags":null,"commentNum":16,"likeNum":53,"readNum":615,"type":"26","top":"N","hot":"N"}]
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
         * id : 180
         * cover : http://118.190.154.52:7777/profile/upload/image/2021/ 05/10/b88ef8ac-7538-4984-be59-3a768ec4709f.png
         * title : 卓创资讯：猪价放量急跌 多地猪价破“10”
         * subTitle : null
         * content : <p>据卓创监测，今日生猪均价跌至 19.00 元/公斤，较月 初跌 8.12%。多地开启急跌模式，“10 元”均价线下压到华南区域，仅极少数 销区均价仍在 20.00 元/公斤以上。大肥猪供应过剩，二次育肥补栏偏弱，预计 猪价或继续走低。</p>
         * status : Y
         * publishDate : 2021-05-10
         * tags : null
         * commentNum : 16
         * likeNum : 53
         * readNum : 615
         * type : 26
         * top : N
         * hot : N
         */

        public int id;
        public String cover;
        public String title;
        public String subTitle;
        public String content;
        public String status;
        public String publishDate;
        public String tags;
        public int commentNum;
        public int likeNum;
        public int readNum;
        public String type;
        public String top;
        public String hot;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }
    }
}
