package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 21:38
 * @Description 店家信息
 */

import java.io.Serializable;
import java.util.List;

public class TakeoutSellerBean {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2021-04-30 12:01:35","updateBy":null,"updateTime":"2021-05-11 15:40:46","remark":null,"params":{},"id":3,"name":"贡茶","address":"尖山街131号1-2","introduction":"黑石礁奶茶第二名","themeId":2,"score":4.8,"saleQuantity":3737,"deliveryTime":38,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/a42956a6-6ca5-47a1-8778-c93d8deba365.jpg","avgCost":20,"other":null,"recommend":"Y","distance":3700,"saleNum3hour":66},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 15:07:16","updateBy":null,"updateTime":"2021-05-12 12:42:19","remark":null,"params":{},"id":4,"name":"田玉麟黄焖鸡","address":"大连甘井子区凌水镇新新园18号楼8号公建","introduction":"田玉麟黄焖鸡成立于2015年，是大连销量遥遥领先的黄焖鸡连锁品牌","themeId":1,"score":4.7,"saleQuantity":668,"deliveryTime":30,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/6cf580bd-9418-43a7-8fd4-05798463616d.jpg","avgCost":20,"other":null,"recommend":"Y","distance":510,"saleNum3hour":15},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 22:41:33","updateBy":null,"updateTime":"2021-05-11 15:41:22","remark":null,"params":{},"id":8,"name":"串歹不胖","address":"凌水镇理工大学车站","introduction":"凌水镇炸物小吃第四名","themeId":4,"score":4.9,"saleQuantity":520,"deliveryTime":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/d8611695-af7e-4dba-a2ed-c691ab7bb80e.jpg","avgCost":20,"other":null,"recommend":"Y","distance":3000,"saleNum3hour":20},{"searchValue":null,"createBy":null,"createTime":"2021-05-09 03:59:34","updateBy":null,"updateTime":"2021-05-12 12:43:04","remark":null,"params":{},"id":16,"name":"Dance cream网红生日蛋糕","address":"辽宁省大连市沙河口区杨树东街35号1单元5楼","introduction":"所有蛋糕都含动物奶油","themeId":2,"score":4.9,"saleQuantity":1482,"deliveryTime":42,"imgUrl":"/prod-api/profile/upload/image/2021/05/09/21c135fa-2f5c-4a1e-9d95-2d7da72b6e74.jpg","avgCost":50,"other":null,"recommend":"Y","distance":2500,"saleNum3hour":36}]
     * code : 200
     * msg : 查询成功
     */

    public int total;
    public int code;
    public String msg;
    public List<RowsBean> rows;

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-04-30 12:01:35
         * updateBy : null
         * updateTime : 2021-05-11 15:40:46
         * remark : null
         * params : {}
         * id : 3
         * name : 贡茶
         * address : 尖山街131号1-2
         * introduction : 黑石礁奶茶第二名
         * themeId : 2
         * score : 4.8
         * saleQuantity : 3737
         * deliveryTime : 38
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/a42956a6-6ca5-47a1-8778-c93d8deba365.jpg
         * avgCost : 20.0
         * other : null
         * recommend : Y
         * distance : 3700.0
         * saleNum3hour : 66
         */

        public Object searchValue;
        public Object createBy;
        public String createTime;
        public Object updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public String name;
        public String address;
        public String introduction;
        public int themeId;
        public double score;
        public int saleQuantity;
        public int deliveryTime;
        public String imgUrl;
        public Number avgCost;
        public Object other;
        public String recommend;
        public double distance;
        public int saleNum3hour;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

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

        public Number getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(Number avgCost) {
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

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
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
