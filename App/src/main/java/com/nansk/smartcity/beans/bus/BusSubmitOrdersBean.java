package com.nansk.smartcity.beans.bus;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 09:44
 * @Description
 */

public class BusSubmitOrdersBean {
    private String start;
    private String end;
    private String price;
    private String path;
    private int status;

    public BusSubmitOrdersBean() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
