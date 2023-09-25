package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 18:05
 * @Description
 */

public class TakeoutOrderCommentObj {

    /**
     * content : 真好吃，下次还来
     * orderNo : 2021051120444612594
     * score : 5
     */

    public String content;
    public String orderNo;
    public int score;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
