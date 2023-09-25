package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 16:06
 * @Description
 */

public class TakeoutSellerDetailsBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":1,"name":"尊宝比萨","address":"辽宁省大连市高新技术园区软景中心南门","introduction":"各种披萨","themeId":3,"score":4.9,"saleQuantity":379,"deliveryTime":30,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021/05/08/9e062202-b89d-412c-ae02-5370bb3b309b.jpg","avgCost":45,"other":null,"recommend":"N","distance":620,"saleNum3hour":21}
     */

    public String msg;
    public int code;
    public DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    public static class DataBean {
        /**
         * id : 1
         * name : 尊宝比萨
         * address : 辽宁省大连市高新技术园区软景中心南门
         * introduction : 各种披萨
         * themeId : 3
         * score : 4.9
         * saleQuantity : 379
         * deliveryTime : 30
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021/05/08/9e062202-b89d-412c-ae02-5370bb3b309b.jpg
         * avgCost : 45
         * other : null
         * recommend : N
         * distance : 620
         * saleNum3hour : 21
         */

        public int id;
        public String name;
        public String address;
        public String introduction;
        public int themeId;
        public double score;
        public int saleQuantity;
        public int deliveryTime;
        public String imgUrl;
        public int avgCost;
        public Object other;
        public String recommend;
        public int distance;
        public int saleNum3hour;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public int getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(int deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(int avgCost) {
            this.avgCost = avgCost;
        }

        public Object getOther() {
            return other;
        }

        public void setOther(Object other) {
            this.other = other;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getSaleNum3hour() {
            return saleNum3hour;
        }

        public void setSaleNum3hour(int saleNum3hour) {
            this.saleNum3hour = saleNum3hour;
        }
    }
}
