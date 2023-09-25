package com.nansk.smartcity.beans.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 17:28
 * @description
 */

import java.io.Serializable;

public class TrafficIllegalDetailsBean {

    /**
     * code : 200
     * data : {"id":2,"licencePlate":"京 FS1009","disposeState":"撤销中","badTime":"2020-10-23 00:00:00","money":"200","deductMarks":"6","illegalSites":"大连市星海公园","noticeNo":"12345712","engineNumber":"12345611","trafficOffence":"闯红灯","catType":"小型新能源汽车","performOffice":null,"performDate":null,"imgUrl":"/dev-api/profile/upload/image/2021/05/01/31fefb9a-a06b47bd-bf71-8624e89b5044.jpg"}
     * msg : 操作成功
     */

    public int code;
    public DataBean data;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * id : 2
         * licencePlate : 京 FS1009
         * disposeState : 撤销中
         * badTime : 2020-10-23 00:00:00
         * money : 200
         * deductMarks : 6
         * illegalSites : 大连市星海公园
         * noticeNo : 12345712
         * engineNumber : 12345611
         * trafficOffence : 闯红灯
         * catType : 小型新能源汽车
         * performOffice : null
         * performDate : null
         * imgUrl : /dev-api/profile/upload/image/2021/05/01/31fefb9a-a06b47bd-bf71-8624e89b5044.jpg
         */

        public int id;
        public String licencePlate;
        public String disposeState;
        public String badTime;
        public String money;
        public String deductMarks;
        public String illegalSites;
        public String noticeNo;
        public String engineNumber;
        public String trafficOffence;
        public String catType;
        public String performOffice;
        public String performDate;
        public String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLicencePlate() {
            return licencePlate;
        }

        public void setLicencePlate(String licencePlate) {
            this.licencePlate = licencePlate;
        }

        public String getDisposeState() {
            return disposeState;
        }

        public void setDisposeState(String disposeState) {
            this.disposeState = disposeState;
        }

        public String getBadTime() {
            return badTime;
        }

        public void setBadTime(String badTime) {
            this.badTime = badTime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDeductMarks() {
            return deductMarks;
        }

        public void setDeductMarks(String deductMarks) {
            this.deductMarks = deductMarks;
        }

        public String getIllegalSites() {
            return illegalSites;
        }

        public void setIllegalSites(String illegalSites) {
            this.illegalSites = illegalSites;
        }

        public String getNoticeNo() {
            return noticeNo;
        }

        public void setNoticeNo(String noticeNo) {
            this.noticeNo = noticeNo;
        }

        public String getEngineNumber() {
            return engineNumber;
        }

        public void setEngineNumber(String engineNumber) {
            this.engineNumber = engineNumber;
        }

        public String getTrafficOffence() {
            return trafficOffence;
        }

        public void setTrafficOffence(String trafficOffence) {
            this.trafficOffence = trafficOffence;
        }

        public String getCatType() {
            return catType;
        }

        public void setCatType(String catType) {
            this.catType = catType;
        }

        public String getPerformOffice() {
            return performOffice;
        }

        public void setPerformOffice(String performOffice) {
            this.performOffice = performOffice;
        }

        public String getPerformDate() {
            return performDate;
        }

        public void setPerformDate(String performDate) {
            this.performDate = performDate;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
