package com.nansk.smartcity.beans.job;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 23:44
 * @Description
 */

public class JobCompanyBean {

    /**
     * code : 200
     * data : {"companyName":"恒但科技公司","id":4,"introductory":"恒但科技始终坚持\u201c踏实、诚信、合作"}
     * msg : 操作成功
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
         * companyName : 恒但科技公司
         * id : 4
         * introductory : 恒但科技始终坚持“踏实、诚信、合作
         */

        public String companyName;
        public int id;
        public String introductory;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroductory() {
            return introductory;
        }

        public void setIntroductory(String introductory) {
            this.introductory = introductory;
        }
    }

}
