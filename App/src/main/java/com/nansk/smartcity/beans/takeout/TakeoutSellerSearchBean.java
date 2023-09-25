package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/26 16:48
 * @Description
 */

import java.util.List;

public class TakeoutSellerSearchBean {

    /**
     * total : 4
     * rows : [{"name":"MrBurger 汉堡披萨意面","address":null,"introduction":null,"score":4.7,"saleQuantity":null,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/09/81783889-66f7-4078-916f-07f073ae9200.jpg","avgCost":30,"deliveryTime":32,"distance":1800,"productList":[{"searchValue":null,"createBy":null,"createTime":"2021-05-09 15:24:58","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":238,"categoryId":38,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/ 2021/05/09/8f5a26d8-63b0-4c52-965b-4bbcbdaf9b84.jpg","name":"意大利香肠披萨","price":58.5,"detail":null,"status":"1","saleQuantity":5,"favorRate":98,"sellerId":12}]}]
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

    public static class RowsBean {
        /**
         * name : MrBurger 汉堡披萨意面
         * address : null
         * introduction : null
         * score : 4.7
         * saleQuantity : null
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021 /05/09/81783889-66f7-4078-916f-07f073ae9200.jpg
         * avgCost : 30
         * deliveryTime : 32
         * distance : 1800
         * productList : [{"searchValue":null,"createBy":null,"createTime":"2021-05-09 15:24:58","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":238,"categoryId":38,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/ 2021/05/09/8f5a26d8-63b0-4c52-965b-4bbcbdaf9b84.jpg","name":"意大利香肠披萨","price":58.5,"detail":null,"status":"1","saleQuantity":5,"favorRate":98,"sellerId":12}]
         */

        public String name;
        public Object address;
        public Object introduction;
        public double score;
        public int saleQuantity;
        public String imgUrl;
        public Number avgCost;
        public int deliveryTime;
        public int distance;
        public List<ProductListBean> productList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getIntroduction() {
            return introduction;
        }

        public void setIntroduction(Object introduction) {
            this.introduction = introduction;
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

        public int getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(int deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class ProductListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2021-05-09 15:24:58
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : 238
             * categoryId : 38
             * imgUrl : http://118.190.154.52:7777/profile/upload/image/ 2021/05/09/8f5a26d8-63b0-4c52-965b-4bbcbdaf9b84.jpg
             * name : 意大利香肠披萨
             * price : 58.5
             * detail : null
             * status : 1
             * saleQuantity : 5
             * favorRate : 98
             * sellerId : 12
             */

            public Object searchValue;
            public Object createBy;
            public String createTime;
            public Object updateBy;
            public Object updateTime;
            public Object remark;
            public int id;
            public int categoryId;
            public String imgUrl;
            public String name;
            public Number price;
            public Object detail;
            public String status;
            public int saleQuantity;
            public Number favorRate;
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

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
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

            public Number getPrice() {
                return price;
            }

            public void setPrice(Number price) {
                this.price = price;
            }

            public Object getDetail() {
                return detail;
            }

            public void setDetail(Object detail) {
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

            public Number getFavorRate() {
                return favorRate;
            }

            public void setFavorRate(Number favorRate) {
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
}
