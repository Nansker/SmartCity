package com.nansk.smartcity.beans.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/07 15:03
 * @description
 */

public class TrafficNoticeDetailsBean {

    /**
     * code : 200
     * data : [{"id":2,"illegalId":1,"userId":1,"code":"2021042110040387379","licenseNo":"210211199909090014","licenseLevel":"C1","carType":"大型汽车","plateNo":"辽 B123456","illegalDate":"2021-04-20","illegalEven":"闯红灯","illegalAddress":"大连市万达广场","money":200,"deductMarks":6,"performDate":"2021-04-20","performOffice":"沙河口交警队","fileNo":"210211199909090014","auditOffice":"沙河口交警队","contact":"13800000000","userName":null}]
     * msg : 操作成功
     */

    public int code;
    public String msg;
    public DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * illegalId : 1
         * userId : 1
         * code : 2021042110040387379
         * licenseNo : 210211199909090014
         * licenseLevel : C1
         * carType : 大型汽车
         * plateNo : 辽 B123456
         * illegalDate : 2021-04-20
         * illegalEven : 闯红灯
         * illegalAddress : 大连市万达广场
         * money : 200
         * deductMarks : 6
         * performDate : 2021-04-20
         * performOffice : 沙河口交警队
         * fileNo : 210211199909090014
         * auditOffice : 沙河口交警队
         * contact : 13800000000
         * userName : null
         */

        public int id;
        public int illegalId;
        public int userId;
        public String code;
        public String licenseNo;
        public String licenseLevel;
        public String carType;
        public String plateNo;
        public String illegalDate;
        public String illegalEven;
        public String illegalAddress;
        public int money;
        public int deductMarks;
        public String performDate;
        public String performOffice;
        public String fileNo;
        public String auditOffice;
        public String contact;
        public String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIllegalId() {
            return illegalId;
        }

        public void setIllegalId(int illegalId) {
            this.illegalId = illegalId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getIllegalDate() {
            return illegalDate;
        }

        public void setIllegalDate(String illegalDate) {
            this.illegalDate = illegalDate;
        }

        public String getIllegalEven() {
            return illegalEven;
        }

        public void setIllegalEven(String illegalEven) {
            this.illegalEven = illegalEven;
        }

        public String getIllegalAddress() {
            return illegalAddress;
        }

        public void setIllegalAddress(String illegalAddress) {
            this.illegalAddress = illegalAddress;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getDeductMarks() {
            return deductMarks;
        }

        public void setDeductMarks(int deductMarks) {
            this.deductMarks = deductMarks;
        }

        public String getPerformDate() {
            return performDate;
        }

        public void setPerformDate(String performDate) {
            this.performDate = performDate;
        }

        public String getPerformOffice() {
            return performOffice;
        }

        public void setPerformOffice(String performOffice) {
            this.performOffice = performOffice;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
