package com.nansk.smartcity.beans.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 19:57
 * @description
 */

public class MoviePressDetailsBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"1","createTime":"2021-05-10 09:09:15","updateBy":"2","updateTime":"2021-05-17 12:28:51","remark":null,"params":{},"id":99,"appType":"movie","cover":"/prod-api/profile/upload/image/2021/05/10/e0844f6e-50f4-4e64-b0c9-0867c73f86ef.jpg","title":"真·三国无双","subTitle":null,"content":"<p>导演: 周显扬<\/p><p>编剧: 杜致朗<\/p><p>主演: 古天乐 / 王凯 / 杨祐宁 / 韩庚 / 古力娜扎 / <\/p><p>类型: 动作 / 奇幻 / 古装<\/p><p>制片国家/地区: 中国大陆 / 中国香港<\/p><p>语言: 汉语普通话 / 粤语<\/p><p>上映日期: 2021-05-01(中国大陆) / 2021-04-29(中国香港)<\/p><p>片长: 117分钟<\/p><p>又名: 真三国无双电影版 / 三国无双 / Dynasty Warriors<\/p><p>&nbsp;<\/p><p>真·三国无双的剧情简介<\/p><p>· · · · · ·<\/p><p>　　电影根据同名游戏改编，讲述了动荡的东汉末年，汉室摇摇欲坠，董卓入京霸占朝野，引起天下动荡，身怀绝世武艺的曹操、吕布、刘备、关羽、张飞等各路英雄豪杰纷纷崛起，一场群雄逐鹿的大混战正式拉开序幕。在乱世中，到底谁才是真正的无双英雄\u2026\u2026<\/p>","status":"Y","publishDate":"2021-05-10","tags":null,"commentNum":null,"likeNum":33,"readNum":5632,"type":"2","top":"N","hot":"N"}
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
         * createTime : 2021-05-10 09:09:15
         * updateBy : 2
         * updateTime : 2021-05-17 12:28:51
         * remark : null
         * params : {}
         * id : 99
         * appType : movie
         * cover : /prod-api/profile/upload/image/2021/05/10/e0844f6e-50f4-4e64-b0c9-0867c73f86ef.jpg
         * title : 真·三国无双
         * subTitle : null
         * content : <p>导演: 周显扬</p><p>编剧: 杜致朗</p><p>主演: 古天乐 / 王凯 / 杨祐宁 / 韩庚 / 古力娜扎 / </p><p>类型: 动作 / 奇幻 / 古装</p><p>制片国家/地区: 中国大陆 / 中国香港</p><p>语言: 汉语普通话 / 粤语</p><p>上映日期: 2021-05-01(中国大陆) / 2021-04-29(中国香港)</p><p>片长: 117分钟</p><p>又名: 真三国无双电影版 / 三国无双 / Dynasty Warriors</p><p>&nbsp;</p><p>真·三国无双的剧情简介</p><p>· · · · · ·</p><p>　　电影根据同名游戏改编，讲述了动荡的东汉末年，汉室摇摇欲坠，董卓入京霸占朝野，引起天下动荡，身怀绝世武艺的曹操、吕布、刘备、关羽、张飞等各路英雄豪杰纷纷崛起，一场群雄逐鹿的大混战正式拉开序幕。在乱世中，到底谁才是真正的无双英雄……</p>
         * status : Y
         * publishDate : 2021-05-10
         * tags : null
         * commentNum : null
         * likeNum : 33
         * readNum : 5632
         * type : 2
         * top : N
         * hot : N
         */

        public Object searchValue;
        public String createBy;
        public String createTime;
        public String updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public String appType;
        public String cover;
        public String title;
        public Object subTitle;
        public String content;
        public String status;
        public String publishDate;
        public Object tags;
        public Object commentNum;
        public int likeNum;
        public int readNum;
        public String type;
        public String top;
        public String hot;

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

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
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

        public Object getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(Object subTitle) {
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

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
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
