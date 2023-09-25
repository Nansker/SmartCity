package com.nansk.smartcity.beans.protection;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 17:05
 * @description
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 17:05
 * @description
 */

public class ProtectionNearListBean implements Serializable {

    /**
     * id : 079100259
     * address : 江西工业贸易职业技术技术学院学生宿舍11栋
     * from : 1.23km
     * time : 周一至周日 05:30-22:30
     * class : [{"name":"饮料瓶","price":"0.02元/个"},{"name":"纸类","price":"0.50/公斤"},{"name":"纺织物","price":"0.10/公斤"},{"name":"金属","price":"0.40元/公斤"},{"name":"塑料","price":"0.10元/公斤"}]
     */

    public String id;
    public String address;
    public String from;
    public String time;
    @SerializedName("class")
    public List<ClassBean> classX;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ClassBean> getClassX() {
        return classX;
    }

    public void setClassX(List<ClassBean> classX) {
        this.classX = classX;
    }

    public static class ClassBean implements Serializable{
        /**
         * name : 饮料瓶
         * price : 0.02元/个
         */

        public String name;
        public String price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
