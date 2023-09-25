package com.nansk.smartcity.beans;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 21:36
 * @description
 */

public class PositionCurrent {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"area":"鹿城区","province":"浙江","city":"温州","location":"温州市人民医院"}
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
         * area : 鹿城区
         * province : 浙江
         * city : 温州
         * location : 温州市人民医院
         */

        public String area;
        public String province;
        public String city;
        public String location;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
