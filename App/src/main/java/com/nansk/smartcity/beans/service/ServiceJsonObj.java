package com.nansk.smartcity.beans.service;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 16:52
 * @Description
 */

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 21:00
 * @Description
 */

public class ServiceJsonObj {

    public int total;
    public int code;
    public String msg;
    public List<ServiceJsonRows> rows;

    public ServiceJsonObj() {
    }

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

    public List<ServiceJsonRows> getRows() {
        return rows;
    }

    public void setRows(List<ServiceJsonRows> rows) {
        this.rows = rows;
    }
}
