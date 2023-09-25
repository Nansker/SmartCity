package com.nansk.smartcity.beans.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/08 19:46
 * @description 提交撤销申请
 */

public class TrafficIllegalCancelBean {

    /**
     * applyDate : 2021-09-10
     * illegalId : 2
     * photo : http://localhost/pic/car.jpg
     * reason : 没违章
     */

    public String applyDate;
    public int illegalId;
    public String photo;
    public String reason;

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public int getIllegalId() {
        return illegalId;
    }

    public void setIllegalId(int illegalId) {
        this.illegalId = illegalId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
