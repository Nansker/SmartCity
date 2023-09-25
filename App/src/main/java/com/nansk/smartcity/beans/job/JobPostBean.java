package com.nansk.smartcity.beans.job;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 20:06
 * @Description
 */

import java.util.List;

public class JobPostBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":1,"companyId":3,"professionId":1,"contacts":"张先生","name":"软件开发","obligation":"负责软件的设计开发测试以及上线","address":"大连市万达广场","need":"工作经验 1-2 年","salary":"5000","companyName":"虎鱼科技","professionName":"java 开发工程师"}]
     * total : 1
     */

    public int code;
    public String msg;
    public int total;
    public List<RowsBean> rows;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * companyId : 3
         * professionId : 1
         * contacts : 张先生
         * name : 软件开发
         * obligation : 负责软件的设计开发测试以及上线
         * address : 大连市万达广场
         * need : 工作经验 1-2 年
         * salary : 5000
         * companyName : 虎鱼科技
         * professionName : java 开发工程师
         */

        public int id;
        public int companyId;
        public int professionId;
        public String contacts;
        public String name;
        public String obligation;
        public String address;
        public String need;
        public String salary;
        public String companyName;
        public String professionName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getProfessionId() {
            return professionId;
        }

        public void setProfessionId(int professionId) {
            this.professionId = professionId;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObligation() {
            return obligation;
        }

        public void setObligation(String obligation) {
            this.obligation = obligation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNeed() {
            return need;
        }

        public void setNeed(String need) {
            this.need = need;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getProfessionName() {
            return professionName;
        }

        public void setProfessionName(String professionName) {
            this.professionName = professionName;
        }
    }
}
