package com.nansk.smartcity.beans.activity;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 20:36
 * @Description
 */

public class ActivitySifnupCheckBean {

    /**
     * msg : 已报名
     * isSignup : true
     * id : 3
     * code : 200
     */

    public String msg;
    public boolean isSignup;
    public int id;
    public int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSignup() {
        return isSignup;
    }

    public void setSignup(boolean signup) {
        isSignup = signup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
