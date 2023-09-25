package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 14:22
 * @Description
 */

import java.util.List;

public class TakeoutOrderDetailsBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"sellerInfo":{"id":1,"name":"尊宝比萨","address":"辽宁省大连市高新技术园区软景中心南门","introduction":"各种披萨","themeId":3,"score":4.9,"saleQuantity":379,"deliveryTime":30,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/08/9e062202-b89d-412c-ae02-5370bb3b309b.jpg","avgCost":45,"other":null,"recommend":"N","distance":620,"saleNum3hour":21},"orderInfo":{"id":15,"orderNo":"2021051120444612594","userId":2,"sellerId":1,"amount":64,"postage":null,"status":"待支付","paymentType":null,"payTime":null,"sendTime":null,"receiverName":"王先生","receiverPhone":"13800000000","receiverAddress":"大连理工大学 教学楼 A3-118","receiverLabel":null,"houseNumber":null,"orderItemList":[{"id":11,"userId":2,"orderNo":"2021051120444612594","productId":1,"productName":"柚见初夏套餐","productImage":"http://118.190.154.52:7777/profile/upload/ image/2021/05/08/88bcec33-736a-4128-ab8a-12f65776b6bd.jpg","productPrice":12,"quantity":2,"totalPrice":24},{"id":12,"userId":2,"orderNo":"2021051120444612594","productId":30,"productName":"10 寸人气爆款比萨","productImage":"http://118.190.154.52:7777/profile/upload/ image/2021/05/08/9aace014-d039-45a6-bf58-c5b27861b277.jpg","productPrice":40,"quantity":1,"totalPrice":40}]}}
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
         * sellerInfo : {"id":1,"name":"尊宝比萨","address":"辽宁省大连市高新技术园区软景中心南门","introduction":"各种披萨","themeId":3,"score":4.9,"saleQuantity":379,"deliveryTime":30,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021 /05/08/9e062202-b89d-412c-ae02-5370bb3b309b.jpg","avgCost":45,"other":null,"recommend":"N","distance":620,"saleNum3hour":21}
         * orderInfo : {"id":15,"orderNo":"2021051120444612594","userId":2,"sellerId":1,"amount":64,"postage":null,"status":"待支付","paymentType":null,"payTime":null,"sendTime":null,"receiverName":"王先生","receiverPhone":"13800000000","receiverAddress":"大连理工大学 教学楼 A3-118","receiverLabel":null,"houseNumber":null,"orderItemList":[{"id":11,"userId":2,"orderNo":"2021051120444612594","productId":1,"productName":"柚见初夏套餐","productImage":"http://118.190.154.52:7777/profile/upload/ image/2021/05/08/88bcec33-736a-4128-ab8a-12f65776b6bd.jpg","productPrice":12,"quantity":2,"totalPrice":24},{"id":12,"userId":2,"orderNo":"2021051120444612594","productId":30,"productName":"10 寸人气爆款比萨","productImage":"http://118.190.154.52:7777/profile/upload/ image/2021/05/08/9aace014-d039-45a6-bf58-c5b27861b277.jpg","productPrice":40,"quantity":1,"totalPrice":40}]}
         */

        public SellerInfoBean sellerInfo;
        public OrderInfoBean orderInfo;

        public SellerInfoBean getSellerInfo() {
            return sellerInfo;
        }

        public void setSellerInfo(SellerInfoBean sellerInfo) {
            this.sellerInfo = sellerInfo;
        }

        public OrderInfoBean getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfoBean orderInfo) {
            this.orderInfo = orderInfo;
        }

        public static class SellerInfoBean {
            /**
             * id : 1
             * name : 尊宝比萨
             * address : 辽宁省大连市高新技术园区软景中心南门
             * introduction : 各种披萨
             * themeId : 3
             * score : 4.9
             * saleQuantity : 379
             * deliveryTime : 30
             * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021 /05/08/9e062202-b89d-412c-ae02-5370bb3b309b.jpg
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
            public Number score;
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

            public Number getScore() {
                return score;
            }

            public void setScore(Number score) {
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

        public static class OrderInfoBean {
            /**
             * id : 15
             * orderNo : 2021051120444612594
             * userId : 2
             * sellerId : 1
             * amount : 64
             * postage : null
             * status : 待支付
             * paymentType : null
             * payTime : null
             * sendTime : null
             * receiverName : 王先生
             * receiverPhone : 13800000000
             * receiverAddress : 大连理工大学 教学楼 A3-118
             * receiverLabel : null
             * houseNumber : null
             * orderItemList : [{"id":11,"userId":2,"orderNo":"2021051120444612594","productId":1,"productName":"柚见初夏套餐","productImage":"http://118.190.154.52:7777/profile/upload/ image/2021/05/08/88bcec33-736a-4128-ab8a-12f65776b6bd.jpg","productPrice":12,"quantity":2,"totalPrice":24},{"id":12,"userId":2,"orderNo":"2021051120444612594","productId":30,"productName":"10 寸人气爆款比萨","productImage":"http://118.190.154.52:7777/profile/upload/ image/2021/05/08/9aace014-d039-45a6-bf58-c5b27861b277.jpg","productPrice":40,"quantity":1,"totalPrice":40}]
             */

            public int id;
            public String orderNo;
            public int userId;
            public int sellerId;
            public Number amount;
            public Number postage;
            public String status;
            public String paymentType;
            public String payTime;
            public String sendTime;
            public String receiverName;
            public String receiverPhone;
            public String receiverAddress;
            public String receiverLabel;
            public String houseNumber;
            public List<OrderItemListBean> orderItemList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
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

            public Number getAmount() {
                return amount;
            }

            public void setAmount(Number amount) {
                this.amount = amount;
            }

            public Number getPostage() {
                return postage;
            }

            public void setPostage(Number postage) {
                this.postage = postage;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPaymentType() {
                return paymentType;
            }

            public void setPaymentType(String paymentType) {
                this.paymentType = paymentType;
            }

            public String getPayTime() {
                return payTime;
            }

            public void setPayTime(String payTime) {
                this.payTime = payTime;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getReceiverPhone() {
                return receiverPhone;
            }

            public void setReceiverPhone(String receiverPhone) {
                this.receiverPhone = receiverPhone;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getReceiverLabel() {
                return receiverLabel;
            }

            public void setReceiverLabel(String receiverLabel) {
                this.receiverLabel = receiverLabel;
            }

            public String getHouseNumber() {
                return houseNumber;
            }

            public void setHouseNumber(String houseNumber) {
                this.houseNumber = houseNumber;
            }

            public List<OrderItemListBean> getOrderItemList() {
                return orderItemList;
            }

            public void setOrderItemList(List<OrderItemListBean> orderItemList) {
                this.orderItemList = orderItemList;
            }

            public static class OrderItemListBean {
                /**
                 * id : 11
                 * userId : 2
                 * orderNo : 2021051120444612594
                 * productId : 1
                 * productName : 柚见初夏套餐
                 * productImage : http://118.190.154.52:7777/profile/upload/ image/2021/05/08/88bcec33-736a-4128-ab8a-12f65776b6bd.jpg
                 * productPrice : 12
                 * quantity : 2
                 * totalPrice : 24
                 */

                public int id;
                public int userId;
                public String orderNo;
                public int productId;
                public String productName;
                public String productImage;
                public Number productPrice;
                public int quantity;
                public Number totalPrice;

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

                public String getOrderNo() {
                    return orderNo;
                }

                public void setOrderNo(String orderNo) {
                    this.orderNo = orderNo;
                }

                public int getProductId() {
                    return productId;
                }

                public void setProductId(int productId) {
                    this.productId = productId;
                }

                public String getProductName() {
                    return productName;
                }

                public void setProductName(String productName) {
                    this.productName = productName;
                }

                public String getProductImage() {
                    return productImage;
                }

                public void setProductImage(String productImage) {
                    this.productImage = productImage;
                }

                public Number getProductPrice() {
                    return productPrice;
                }

                public void setProductPrice(Number productPrice) {
                    this.productPrice = productPrice;
                }

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }

                public Number getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(Number totalPrice) {
                    this.totalPrice = totalPrice;
                }
            }
        }
    }
}
