package com.nansk.smartcity.beans;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/14 16:05
 * @Description
 */

public class UserLoginBean {
    private int code;
    private String msg;
    private String token;

    public UserLoginBean() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
