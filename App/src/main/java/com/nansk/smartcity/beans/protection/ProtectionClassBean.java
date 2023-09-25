package com.nansk.smartcity.beans.protection;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 12:40
 * @description
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProtectionClassBean {

    /**
     * class : 可回收物
     * params : 可回收物主要包括废纸、塑料、玻璃、金属和布料五大类。
     * note : 这些垃圾通过综合处理回收利用，可以减少污染，节省资源。如每回收1吨废纸可造好纸850公斤，节省木材300公斤，比等量生产减少污染74%；每回收1吨塑料饮料瓶可获得0.7吨二级原料；每回收1吨废钢铁可炼好钢0.9吨，比用矿石冶炼节约成本47%，减少空气污染75%，减少97%的水污染和固体废物。
     * rows : [{"content":"废纸：主要包括报纸、期刊、图书、各种包装纸等。但是，要注意纸巾和厕所纸由于水溶性太强不可回收。"},{"content":"塑料：各种塑料袋、塑料泡沫、塑料包装（快递包装纸是其他垃圾/干垃圾）、一次性塑料餐盒餐具、硬塑料、塑料牙刷、塑料杯子、矿泉水瓶等。"},{"content":"玻璃：主要包括各种玻璃瓶、碎玻璃片、暖瓶等。（镜子是其他垃圾/干垃圾）"},{"content":"布料：主要包括废弃衣服、桌布、洗脸巾、书包、鞋等。"}]
     */

    @SerializedName("class")
    public String classX;
    public String params;
    public String note;
    public List<RowsBean> rows;

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * content : 废纸：主要包括报纸、期刊、图书、各种包装纸等。但是，要注意纸巾和厕所纸由于水溶性太强不可回收。
         */

        public String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
