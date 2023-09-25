package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 15:40
 * @Description 菜品列表
 */

import java.util.List;

public class TakeoutProductBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":"2021-04-14 08:18:24","updateBy":null,"updateTime":"2021-05-09 14:25:52","remark":null,"params":{},"id":1,"categoryId":19,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/88bcec33-736a-4128-ab8a-12f65776b6bd.jpg","name":"柚见初夏套餐","price":12,"detail":"八寸香辣至尊比萨*1+新奥尔良烤翅+薯条+韩式柚子茶*2","status":"1","saleQuantity":2,"favorRate":98,"sellerId":1},{"searchValue":null,"createBy":null,"createTime":"2021-04-14 08:43:13","updateBy":null,"updateTime":"2021-05-09 14:27:42","remark":null,"params":{},"id":2,"categoryId":19,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/eb6c1883-40af-4ff4-a64e-f98cdbf06050.jpg","name":"拼拼有你甜蜜套餐","price":68.8,"detail":"10寸苏丹王榴莲半个/水果什锦半个+劲爆鸡米花+柠檬翡翠茉莉*2","status":"1","saleQuantity":0,"favorRate":98,"sellerId":1},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 20:27:51","updateBy":null,"updateTime":"2021-05-09 14:25:47","remark":null,"params":{},"id":26,"categoryId":19,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/85e87a75-4d9b-48d6-9c9e-07831c9773d7.jpg","name":"春日榴莲荟套餐","price":69.9,"detail":"10寸苏丹王榴莲比萨+薯条+雪碧*2","status":"1","saleQuantity":3,"favorRate":98,"sellerId":1},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 20:32:35","updateBy":null,"updateTime":"2021-05-09 14:24:40","remark":null,"params":{},"id":27,"categoryId":19,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/b5d54ce8-8611-44f7-905c-c1a888be8470.jpg","name":"尊宝小食桶","price":39.9,"detail":"黄金蝴蝶虾+劲爆鸡米花+脆汁炸鸡腿+薯格","status":"1","saleQuantity":2,"favorRate":99,"sellerId":1},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 20:37:37","updateBy":null,"updateTime":"2021-05-09 14:26:02","remark":null,"params":{},"id":32,"categoryId":19,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/556f97c8-5def-4ab5-ae15-5a81b54b9144.jpg","name":"超团结力量餐","price":54.54,"detail":"10寸新奥尔良烤肉比萨+咔滋鸡块+劲爆鸡米花+可乐*2","status":"1","saleQuantity":1,"favorRate":98,"sellerId":1},{"searchValue":null,"createBy":null,"createTime":"2021-05-08 20:42:04","updateBy":null,"updateTime":"2021-05-09 14:26:27","remark":null,"params":{},"id":37,"categoryId":19,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/274dae63-1ced-486e-8337-29a12c99f1e4.jpg","name":"尊宝双重奏比萨赠薯条一份","price":43.6,"detail":"五香牛肉/夏威夷比萨/和风照烧鸡+薯条","status":"1","saleQuantity":0,"favorRate":98,"sellerId":1}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-04-14 08:18:24
         * updateBy : null
         * updateTime : 2021-05-09 14:25:52
         * remark : null
         * params : {}
         * id : 1
         * categoryId : 19
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/88bcec33-736a-4128-ab8a-12f65776b6bd.jpg
         * name : 柚见初夏套餐
         * price : 12.0
         * detail : 八寸香辣至尊比萨*1+新奥尔良烤翅+薯条+韩式柚子茶*2
         * status : 1
         * saleQuantity : 2
         * favorRate : 98.0
         * sellerId : 1
         */

        public Object searchValue;
        public Object createBy;
        public String createTime;
        public Object updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public int categoryId;
        public String imgUrl;
        public String name;
        public double price;
        public String detail;
        public String status;
        public int saleQuantity;
        public double favorRate;
        public int sellerId;

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

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSaleQuantity() {
            return saleQuantity;
        }

        public void setSaleQuantity(int saleQuantity) {
            this.saleQuantity = saleQuantity;
        }

        public double getFavorRate() {
            return favorRate;
        }

        public void setFavorRate(double favorRate) {
            this.favorRate = favorRate;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }
    }
}
