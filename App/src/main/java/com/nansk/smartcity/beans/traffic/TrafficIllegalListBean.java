package com.nansk.smartcity.beans.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 14:36
 * @description
 */

import java.io.Serializable;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 11:55
 * @description
 */

public class TrafficIllegalListBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":1,"licencePlate":"辽 B123456","disposeState":"已缴款","badTime":"2021-04-20 11:51:17","money":"200","deductMarks":"6","illegalSites":"大连市万达广场","noticeNo":"2021042110040387379","engineNumber":"12345611","trafficOffence":"闯红灯","catType":"大型汽车","performOffice":"交警队","performDate":"2021-04-20","imgUrl":"/dev-api/profile/upload/image/2021/05/01/31fefb9a-a06 b-47bd-bf71-8624e89b5044.jpg"}]
     * total : 1
     */

    public int code;
    public String msg;
    public int total;
    public List<RowsBean> rows;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
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

        /**
         * id : 1
         * licencePlate : 辽 B123456
         * disposeState : 已缴款
         * badTime : 2021-04-20 11:51:17
         * money : 200
         * deductMarks : 6
         * illegalSites : 大连市万达广场
         * noticeNo : 2021042110040387379
         * engineNumber : 12345611
         * trafficOffence : 闯红灯
         * catType : 大型汽车
         * performOffice : 交警队
         * performDate : 2021-04-20
         * imgUrl : /dev-api/profile/upload/image/2021/05/01/31fefb9a-a06 b-47bd-bf71-8624e89b5044.jpg
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
    }
}
