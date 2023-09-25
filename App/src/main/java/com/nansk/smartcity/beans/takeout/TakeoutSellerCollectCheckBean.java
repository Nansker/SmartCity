package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/26 11:28
 * @Description 店家是否收藏Bean
 */

public class TakeoutSellerCollectCheckBean {

    /**
     * code : 200
     * isCollect : true
     * id : 4
     * msg : 已收藏
     */

    public int code;
    public boolean isCollect;
    public int id;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
