package com.nansk.smartcity.beans.job;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 23:31
 * @Description
 */

public class JobPostDetailsBean {

    /**
     * code : 200
     * data : {"id":1,"companyId":3,"professionId":1,"contacts":"张先生","name":"软件开发","obligation":"负责软件的设计开发测试以及上线","address":"大连市万达广场","need":"工作经验 1-2 年","salary":"5000","companyName":null,"professionName":null}
     * msg :
     */

    public int code;
    public DataBean data;
    public String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
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
         * companyName : null
         * professionName : null
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
