package com.nansk.smartcity.beans.activity;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 20:45
 * @Description 参加活动报名对象
 */

public class ActivityApplyBean {

    /**
     * activityId : 1
     * content : 我喜欢这个活动
     */

    public int activityId;
    public String content;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
