package com.nansk.smartcity.beans.hospital;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 10:45
 * @Description
 */

import java.io.Serializable;
import java.util.List;

public class HospitalListBean {

    /**
     * total : 9
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"hospitalName":"大连市儿童医院","brief":"<p>大连市儿童医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务。2013年2月6日，签约成为大连医科大学附属大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿科、儿外科住院医师规范化培训基地。医院设有36个学科，大连市快速提升医疗软实力建设项目小儿心脏病诊疗基地1个，大连市一级医学重点学科6个。<\/p>","level":3,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/5398a863-df13-445e-a21c-7be32a81b461.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"hospitalName":"大连市中山医院","brief":"<p>大连大学附属中山医院(沈阳铁路局大连医院)创建于1907年,是一所集医疗、教学、科研为一体的综合性大学附属医院。占地面积九万余平方米,建筑面积七万余平方米<\/p>","level":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/6c53e293-7922-4e07-8a73-2c6fcee353e2.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"hospitalName":"大连市大一医院","brief":"<p>大连市大一医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务<\/p>","level":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/62a9b3a1-aef2-421e-a8b7-268247f02579.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"hospitalName":"大连市人民医院","brief":"<p>大连市人民医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务<\/p>","level":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/2014d591-512f-4662-b544-d5133dbc58c9.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"hospitalName":"大连市医大二院","brief":"<p>大连市医大二院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务<\/p>","level":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/7f849651-6e85-42e3-b86d-2e184c4b0115.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"hospitalName":"大连市妇女儿童医院","brief":"<p>大连市妇女儿童医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务<\/p>","level":4,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/f2fe5af8-e213-4deb-8ec1-73426438f519.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"hospitalName":"大连市眼科医院","brief":"<p>大连市眼科医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务<\/p>","level":3,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/c293140b-66d3-46bd-9171-860b4f71f681.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"hospitalName":"大连市肿瘤医院","brief":"<p>大连市肿瘤医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务<\/p>","level":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/3d5142ef-38a7-4637-b9bd-ed6e88dd1c46.jpg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"hospitalName":"大连市专科医院","brief":"<p>大连市专科创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务<\/p>","level":4,"imgUrl":"/prod-api/profile/upload/image/2021/05/08/e5d369ab-6f50-4f06-9b8d-515ddd15d887.jpg"}]
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
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * hospitalName : 大连市儿童医院
         * brief : <p>大连市儿童医院创建于1952年6月1日，经过几代儿医人的艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛18岁以下儿童的医疗、预防、康复、保健任务。2013年2月6日，签约成为大连医科大学附属大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿科、儿外科住院医师规范化培训基地。医院设有36个学科，大连市快速提升医疗软实力建设项目小儿心脏病诊疗基地1个，大连市一级医学重点学科6个。</p>
         * level : 3
         * imgUrl : /prod-api/profile/upload/image/2021/05/08/5398a863-df13-445e-a21c-7be32a81b461.jpg
         */

        public Object searchValue;
        public Object createBy;
        public Object createTime;
        public Object updateBy;
        public Object updateTime;
        public Object remark;
        public int id;
        public String hospitalName;
        public String brief;
        public int level;
        public String imgUrl;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
