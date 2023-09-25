package com.nansk.smartcity.beans.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 19:57
 * @description
 */

public class MetroCodeInfoBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":1,"userName":"王大卫","idCard":"210211199909090014","phonenumber":"13800000000","cardNum":"2021042710153823761","userId":2}
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
         * userName : 王大卫
         * idCard : 210211199909090014
         * phonenumber : 13800000000
         * cardNum : 2021042710153823761
         * userId : 2
         */

        public int id;
        public String userName;
        public String idCard;
        public String phonenumber;
        public String cardNum;
        public int userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
