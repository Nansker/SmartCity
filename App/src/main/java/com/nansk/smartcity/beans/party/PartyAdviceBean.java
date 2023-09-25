package com.nansk.smartcity.beans.party;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 10:45
 * @description
 */

import java.io.Serializable;

public class PartyAdviceBean implements Serializable {
    private String title;
    private String content;
    private String date;
    private String name;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
