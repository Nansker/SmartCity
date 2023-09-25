package com.nansk.smartcity.beans.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/05 14:57
 * @description 获取当前登录人绑定的驾驶证信息
 */

public class TrafficLicenseBean {

    /**
     * applyDate : 2021-09-10
     * auditOffice : 交警队
     * contact : 13800000000
     * fileNo : 12312312312
     * idCard : 12323123123123
     * licenseLevel : C1
     * licenseNo : 1231321123212
     * timeout : 否
     * verifyDate : 2027-09-10
     */

    public String applyDate;
    public String auditOffice;
    public String contact;
    public String fileNo;
    public String idCard;
    public String licenseLevel;
    public String licenseNo;
    public String timeout;
    public String verifyDate;

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
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

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLicenseLevel() {
        return licenseLevel;
    }

    public void setLicenseLevel(String licenseLevel) {
        this.licenseLevel = licenseLevel;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }
}
