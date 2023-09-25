package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 10:08
 * @Description 生成订单对象
 */

import java.io.Serializable;
import java.util.List;

public class TakeoutOrderObj implements Serializable {

    /**
     * addressDetail : 大连理工大学 教学楼 A3-118
     * label : 学校
     * name : 王先生
     * phone : 13800000000
     * amount : 64
     * orderItemList : [{"productId":1,"quantity":2},{"productId":30,"quantity":1}]
     * sellerId : 1
     */

    public String addressDetail;
    public String label;
    public String name;
    public String phone;
    public Number amount;
    private int number;
    public int sellerId;
    public String sellerName;
    public List<OrderItemListBean> orderItemList;

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public List<OrderItemListBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemListBean> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public static class OrderItemListBean implements Serializable {
        /**
         * productId : 1
         * quantity : 2
         */

        public int productId;
        public int quantity;

        private double price;
        private String imgUrl;
        private String productName;
        private int number;

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
