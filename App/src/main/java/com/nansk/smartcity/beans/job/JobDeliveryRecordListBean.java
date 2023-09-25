package com.nansk.smartcity.beans.job;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/10/02 14:42
 * @Description 历史投递简历列表
 */

import java.util.List;

public class JobDeliveryRecordListBean {

    /**
     * code : 200
     * rows : [{"id":6,"userId":1,"postId":1,"companyId":3,"companyName":"虎鱼科技","money":"4000","satrTime":"2021-09-10","userName":"admin"}]
     * msg : 查询成功
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
         * id : 6
         * userId : 1
         * postId : 1
         * companyId : 3
         * companyName : 虎鱼科技
         * money : 4000
         * satrTime : 2021-09-10
         * userName : admin
         */

        public String searchValue;
        public String createBy;
        public String createTime;
        public String updateBy;

        public int id;
        public int userId;
        public int postId;
        private String postName;
        public int companyId;
        public String companyName;
        public String money;
        public String satrTime;
        public String userName;

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

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

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getSatrTime() {
            return satrTime;
        }

        public void setSatrTime(String satrTime) {
            this.satrTime = satrTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
