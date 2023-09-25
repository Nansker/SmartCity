package com.nansk.smartcity.beans.house;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 10:36
 * @Description
 */

public class HouseDetailsBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"sourceName":"泉水B6区泉水小学北站附近多层一室一厅得房率高\n","address":"泉水B6区泉水小学北站附近多层一室一厅得房率高","areaSize":52,"tel":"18546474545","price":"16262/㎡","houseType":"二手","pic":"/prod-api/profile/upload/image/2021/05/17/71ac2d26-4504-412d-81f1-0749f64b42d7.png","description":"房主现在比较着急 房子如果真看好的话 价格可议 手续这边齐全 房子没有抵押 产权证在手 随时配合过户 房主现在已经搬走了 房子现在是空置状态"}
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
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1
         * sourceName : 泉水B6区泉水小学北站附近多层一室一厅得房率高
         * address : 泉水B6区泉水小学北站附近多层一室一厅得房率高
         * areaSize : 52
         * tel : 18546474545
         * price : 16262/㎡
         * houseType : 二手
         * pic : /prod-api/profile/upload/image/2021/05/17/71ac2d26-4504-412d-81f1-0749f64b42d7.png
         * description : 房主现在比较着急 房子如果真看好的话 价格可议 手续这边齐全 房子没有抵押 产权证在手 随时配合过户 房主现在已经搬走了 房子现在是空置状态
         */

        public Object searchValue;
        public Object createBy;
        public Object createTime;
        public Object updateBy;
        public Object updateTime;
        public Object remark;
        public int id;
        public String sourceName;
        public String address;
        public int areaSize;
        public String tel;
        public String price;
        public String houseType;
        public String pic;
        public String description;

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

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAreaSize() {
            return areaSize;
        }

        public void setAreaSize(int areaSize) {
            this.areaSize = areaSize;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
