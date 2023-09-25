package com.nansk.smartcity.beans.press;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/15 20:25
 * @Description 新闻评论Bean
 */

public class PressCommentsBean {
    private int newsId;
    private String content;

    public PressCommentsBean() {
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
}
