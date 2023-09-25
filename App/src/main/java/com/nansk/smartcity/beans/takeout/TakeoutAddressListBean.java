package com.nansk.smartcity.beans.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 14:40
 * @Description
 */

import java.io.Serializable;
import java.util.List;

public class TakeoutAddressListBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":8,"userId":104,"name":"王大卫","phone":"15898135276","addressDetail":"大连理工大学 教学楼 A3-118","label":"学校"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 8
         * userId : 104
         * name : 王大卫
         * phone : 15898135276
         * addressDetail : 大连理工大学 教学楼 A3-118
         * label : 学校
         */

        public int id;
        public int userId;
        public String name;
        public String phone;
        public String addressDetail;
        public String label;

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
    }
}
