package com.nansk.smartcity.beans;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 11:07
 * @Description 反馈内容Bean
 * 标题+内容
 */

public class FeedBackBean {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
