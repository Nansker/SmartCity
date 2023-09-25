package com.nansk.smartcity.beans.hospital;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 15:14
 * @Description 就诊人卡片
 */

import java.io.Serializable;
import java.util.List;

public class PatientListBean {

    /**
     * total : 3
     * rows : [{"id":1,"name":"王大卫","cardId":"210211199909090014","tel":"13800000000","sex":"0","birthday":"1999-09-09","imgUrl":"/updata/1.jpg","address":"大连市高新区","userId":2}]
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
         * id : 1
         * name : 王大卫
         * cardId : 210211199909090014
         * tel : 13800000000
         * sex : 0
         * birthday : 1999-09-09
         * imgUrl : /updata/1.jpg
         * address : 大连市高新区
         * userId : 2
         */

        public int id;
        public String name;
        public String cardId;
        public String tel;
        public String sex;
        public String birthday;
        public String imgUrl;
        public String address;
        public int userId;

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

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
