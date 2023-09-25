package com.nansk.smartcity.beans;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 11:37
 * @description 服务器获取到的用户信息
 */

import java.io.Serializable;

public class UserInfoBean {

    /**
     * msg : 操作成功
     * code : 200
     * user : {"userId":2,"userName":"test01","nickName":"测试用户 01","email":"ljxl@qq.com","phonenumber":"13800000000","sex":"0","avatar":"","idCard":"210211199909090014","balance":9800,"score":10000}
     */

    public String msg;
    public int code;
    public UserBean user;

    public static class UserBean implements Serializable {
        /**
         * userId : 2
         * userName : test01
         * nickName : 测试用户 01
         * email : ljxl@qq.com
         * phonenumber : 13800000000
         * sex : 0
         * avatar :
         * idCard : 210211199909090014
         * balance : 9800
         * score : 10000
         */

        public int userId;
        public String userName;
        public String nickName;
        public String email;
        public String phonenumber;
        public String sex;
        public String avatar;
        public String idCard;
        public Number balance;
        public int score;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public Number getBalance() {
            return balance;
        }

        public void setBalance(Number balance) {
            this.balance = balance;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
