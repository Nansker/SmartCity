package com.nansk.smartcity.beans.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/05 15:24
 * @description
 */

import java.io.Serializable;
import java.util.List;

public class TrafficLicenseUserBean {

    /**
     * code : 200
     * data : {"id":1,"userId":2,"licenseNo":"210211199909090014","licenseLevel":"C1","idCard":"210211199909090014","score":6,"applyDate":"2020-02-12","verifyDate":"10 年","timeout":"N","userName":null,"fileNo":"210211199909090014","auditOffice":"大连市沙河口区交警队","contact":"13800000000"}
     * msg : 操作成功
     */

    public int code;
    public List<DataBean> data;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * userId : 2
         * licenseNo : 210211199909090014
         * licenseLevel : C1
         * idCard : 210211199909090014
         * score : 6
         * applyDate : 2020-02-12
         * verifyDate : 10 年
         * timeout : N
         * userName : null
         * fileNo : 210211199909090014
         * auditOffice : 大连市沙河口区交警队
         * contact : 13800000000
         */

        public int id;
        public int userId;
        public String licenseNo;
        public String licenseLevel;
        public String idCard;
        public int score;
        public String applyDate;
        public String verifyDate;
        public String timeout;
        public String userName;
        public String fileNo;
        public String auditOffice;
        public String contact;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getLicenseLevel() {
            return licenseLevel;
        }

        public void setLicenseLevel(String licenseLevel) {
            this.licenseLevel = licenseLevel;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getVerifyDate() {
            return verifyDate;
        }

        public void setVerifyDate(String verifyDate) {
            this.verifyDate = verifyDate;
        }

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFileNo() {
            return fileNo;
        }

        public void setFileNo(String fileNo) {
            this.fileNo = fileNo;
        }

        public String getAuditOffice() {
            return auditOffice;
        }

        public void setAuditOffice(String auditOffice) {
            this.auditOffice = auditOffice;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }
}
