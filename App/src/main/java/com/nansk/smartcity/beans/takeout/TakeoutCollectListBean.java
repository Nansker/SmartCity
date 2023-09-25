package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 21:13
 * @Description
 */

import java.util.List;

public class TakeoutCollectListBean {

    /**
     * total : 5
     * rows : [{"id":3,"userId":2,"sellerId":2,"sellerName":"皖北刘哥牛肉板面","address":"辽宁省大连市甘井子区新新园 79 号","imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021/05/08/30258b9d-3cea-44a8-9ce1-03c4790870d8.jpg","score":4.5,"saleQuantity":838,"userName":"test01","nickName":"测试用户 01"}]
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
         * id : 3
         * userId : 2
         * sellerId : 2
         * sellerName : 皖北刘哥牛肉板面
         * address : 辽宁省大连市甘井子区新新园 79 号
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021/05/08/30258b9d-3cea-44a8-9ce1-03c4790870d8.jpg
         * score : 4.5
         * saleQuantity : 838
         * userName : test01
         * nickName : 测试用户 01
         */

        public int id;
        public int userId;
        public int sellerId;
        public String sellerName;
        public String address;
        public String imgUrl;
        public double score;
        public int saleQuantity;
        public String userName;
        public String nickName;

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

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
