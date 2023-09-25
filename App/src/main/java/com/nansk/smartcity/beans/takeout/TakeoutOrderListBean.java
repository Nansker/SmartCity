package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 21:46
 * @Description
 */

import java.util.List;

public class TakeoutOrderListBean {

    /**
     * total : 1
     * rows : [{"sellerInfo":{"id":14,"name":"九叶牛肉面","address":"沙河口区西南路 29-1 号","introduction":"用心做好面","themeId":5,"score":4.6,"saleQuantity":1635,"deliveryTime":42,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/b19808eb-8de1-4355-ace0-6cc801f00988.jpg","avgCost":30,"other":null,"recommend":"N","distance":3100,"saleNum3hour":25},"orderInfo":{"id":19,"orderNo":"2021051121282519510","userId":2,"sellerId":14,"amount":40,"postage":null,"status":"待支付","paymentType":null,"payTime":null,"sendTime":null,"receiverName":"王先生","receiverPhone":"13800000000","receiverAddress":"大连理工大学 教学楼 A3-118","receiverLabel":null,"houseNumber":null,"orderItemList":[{"id":20,"userId":2,"orderNo":"2021051121282519510","productId":169,"productName":"鱿鱼炒面","productImage":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/c025e6a0-f91a-4965-835d-17ce2e7efe44.jpg","productPrice":20,"quantity":1,"totalPrice":20},{"id":21,"userId":2,"orderNo":"2021051121282519510","productId":170,"productName":"芸豆蚬子打卤面","productImage":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/882a3bb6-6893-4658-aead-6ace164d0da0.jpg","productPrice":20,"quantity":1,"totalPrice":20}]}}]
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
         * sellerInfo : {"id":14,"name":"九叶牛肉面","address":"沙河口区西南路 29-1 号","introduction":"用心做好面","themeId":5,"score":4.6,"saleQuantity":1635,"deliveryTime":42,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/b19808eb-8de1-4355-ace0-6cc801f00988.jpg","avgCost":30,"other":null,"recommend":"N","distance":3100,"saleNum3hour":25}
         * orderInfo : {"id":19,"orderNo":"2021051121282519510","userId":2,"sellerId":14,"amount":40,"postage":null,"status":"待支付","paymentType":null,"payTime":null,"sendTime":null,"receiverName":"王先生","receiverPhone":"13800000000","receiverAddress":"大连理工大学 教学楼 A3-118","receiverLabel":null,"houseNumber":null,"orderItemList":[{"id":20,"userId":2,"orderNo":"2021051121282519510","productId":169,"productName":"鱿鱼炒面","productImage":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/c025e6a0-f91a-4965-835d-17ce2e7efe44.jpg","productPrice":20,"quantity":1,"totalPrice":20},{"id":21,"userId":2,"orderNo":"2021051121282519510","productId":170,"productName":"芸豆蚬子打卤面","productImage":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/882a3bb6-6893-4658-aead-6ace164d0da0.jpg","productPrice":20,"quantity":1,"totalPrice":20}]}
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
             * id : 14
             * name : 九叶牛肉面
             * address : 沙河口区西南路 29-1 号
             * introduction : 用心做好面
             * themeId : 5
             * score : 4.6
             * saleQuantity : 1635
             * deliveryTime : 42
             * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021/05/09/b19808eb-8de1-4355-ace0-6cc801f00988.jpg
             * avgCost : 30
             * other : null
             * recommend : N
             * distance : 3100
             * saleNum3hour : 25
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

        public static class OrderInfoBean {
            /**
             * id : 19
             * orderNo : 2021051121282519510
             * userId : 2
             * sellerId : 14
             * amount : 40
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
             * orderItemList : [{"id":20,"userId":2,"orderNo":"2021051121282519510","productId":169,"productName":"鱿鱼炒面","productImage":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/c025e6a0-f91a-4965-835d-17ce2e7efe44.jpg","productPrice":20,"quantity":1,"totalPrice":20},{"id":21,"userId":2,"orderNo":"2021051121282519510","productId":170,"productName":"芸豆蚬子打卤面","productImage":"http://118.190.154.52:7777/profile/upload/image/2021/05/09/882a3bb6-6893-4658-aead-6ace164d0da0.jpg","productPrice":20,"quantity":1,"totalPrice":20}]
             */

            public int id;
            public String orderNo;
            public int userId;
            public int sellerId;
            public Number amount;
            public Object postage;
            public String status;
            public String paymentType;
            public String payTime;
            public String sendTime;
            public String receiverName;
            public String receiverPhone;
            public String receiverAddress;
            public Object receiverLabel;
            public Object houseNumber;
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

            public Object getPostage() {
                return postage;
            }

            public void setPostage(Object postage) {
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

            public Object getReceiverLabel() {
                return receiverLabel;
            }

            public void setReceiverLabel(Object receiverLabel) {
                this.receiverLabel = receiverLabel;
            }

            public Object getHouseNumber() {
                return houseNumber;
            }

            public void setHouseNumber(Object houseNumber) {
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
                 * id : 20
                 * userId : 2
                 * orderNo : 2021051121282519510
                 * productId : 169
                 * productName : 鱿鱼炒面
                 * productImage : http://118.190.154.52:7777/profile/upload/image/2021/05/09/c025e6a0-f91a-4965-835d-17ce2e7efe44.jpg
                 * productPrice : 20
                 * quantity : 1
                 * totalPrice : 20
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

                public void setProductPrice(int productPrice) {
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
