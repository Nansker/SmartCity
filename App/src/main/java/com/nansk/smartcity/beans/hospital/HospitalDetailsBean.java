package com.nansk.smartcity.beans.hospital;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/21 16:03
 * @description
 */

public class HospitalDetailsBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":1,"hospitalName":"大连市儿童医院","brief":"<p>大连市儿童医院创建于 1952 年 6 月 1 日，经过几代儿医人的 艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设 备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛 18 岁以下儿童的 医疗、预防、康复、保健任务。2013 年 2 月 6 日，签约成为大连医科大学附属 大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿 科、儿外科住院医师规范化培训基地。医院设有 36 个学科，大连市快速提升医 疗软实力建设项目小儿心脏病诊疗基地 1 个，大连市一级医学重点学科 6 个。< /p>","level":3,"imgUrl":"http://118.190.154.52:7777/profile/upload/image/2021/0 5/08/5398a863-df13-445e-a21c-7be32a81b461.jpg"}
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
         * hospitalName : 大连市儿童医院
         * brief : <p>大连市儿童医院创建于 1952 年 6 月 1 日，经过几代儿医人的 艰苦奋斗，已成长为一所学科门类齐全、师资力量雄厚、医疗技术精湛、诊疗设 备先进的辽宁省规模最大的综合性儿童医院。担负着辽东半岛 18 岁以下儿童的 医疗、预防、康复、保健任务。2013 年 2 月 6 日，签约成为大连医科大学附属 大连市儿童医院。系国家儿科、儿外科住院医师规范化培训基地，辽宁省首批儿 科、儿外科住院医师规范化培训基地。医院设有 36 个学科，大连市快速提升医 疗软实力建设项目小儿心脏病诊疗基地 1 个，大连市一级医学重点学科 6 个。< /p>
         * level : 3
         * imgUrl : http://118.190.154.52:7777/profile/upload/image/2021/0 5/08/5398a863-df13-445e-a21c-7be32a81b461.jpg
         */

        public int id;
        public String hospitalName;
        public String brief;
        public int level;
        public String imgUrl;

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
