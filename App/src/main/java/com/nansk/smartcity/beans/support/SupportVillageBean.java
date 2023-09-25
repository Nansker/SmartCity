package com.nansk.smartcity.beans.support;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 16:58
 * @description
 */

import java.io.Serializable;

public class SupportVillageBean implements Serializable {

    /**
     * id : 1
     * name : 小池镇姚湖村村情、村貌简介
     * introduce : 姚湖村地处三省交汇、九省通衢的九江长江大桥二桥脚下，位于105国道北侧，东与万家村接壤，南与汪列村毗邻，现有耕地面积1213亩，其中水田590亩，农产结构主要以粮、棉、菜为主。全村总人口1065人，263户，辖5个村民小组，32名党员，3个党小组。
     * address : 南昌市高新区紫阳大道318号
     * readNum : 255
     * author : 南山客
     * date : 2021-10-20
     */

    public int id;
    public String name;
    public String introduce;
    public String address;
    public int readNum;
    public String author;
    public String date;

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
