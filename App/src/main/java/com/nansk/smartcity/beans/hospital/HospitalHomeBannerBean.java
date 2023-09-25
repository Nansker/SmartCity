package com.nansk.smartcity.beans.hospital;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/21 15:15
 * @description
 */

import java.util.List;

public class HospitalHomeBannerBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/921ee654-d6c3-4876-8450-16ac272e18df.jpg","hospitalId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/4d41e85e-9099-4de6-b3c5-5e97123b1734.jpg","hospitalId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/32b46ab7-a004-4c81-abb5-4f6a87d1fd76.jpg","hospitalId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":4,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/a6edd299-feaa-42b0-b118-ed3c771bae54.jpg","hospitalId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":5,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/6075c57e-d4c9-484e-8c00-8c7cd95abbd3.jpg","hospitalId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":6,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/34b4a16c-295e-48f7-8103-1799a43b329a.jpg","hospitalId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/d3271979-2e07-4860-9c61-4e51ddc1526f.jpg","hospitalId":3},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/8c0f7adf-3562-4ff6-a9c8-bd08cd7b38d3.jpg","hospitalId":3},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/8a04728d-c97c-4447-8362-5240c165e8e7.jpg","hospitalId":3},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/a6d641d0-141d-4205-9502-dfe5ca22ed61.jpg","hospitalId":4},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/b677d870-295b-4451-a1bb-32775d23517d.jpg","hospitalId":4},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/5b8f85cf-9bfb-4829-b648-afd6bc0f2106.jpg","hospitalId":4},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":13,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/434b666e-48fc-407b-a4bd-a32b8b0333f6.jpg","hospitalId":5},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":14,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/bbeecbd2-cf12-42ba-998c-4446f1f3da4c.jpg","hospitalId":5},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":15,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/01b5b30d-caf6-4a04-bf19-a2afa503a8c7.jpg","hospitalId":5},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":16,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/190942d9-a0d3-4f20-872e-ea953125abaa.jpg","hospitalId":6},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/929d044a-8828-441a-a203-1f8c8dc32167.jpg","hospitalId":6},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":18,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/051923d8-0cac-4ae0-83d5-ec7d054792c9.jpg","hospitalId":6},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":19,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/30280537-8988-4a7f-b70e-6cd37a28411d.jpg","hospitalId":7},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":20,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/07fb85e9-7e2e-4235-96cc-5e2d5fefc3fe.jpg","hospitalId":7},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":21,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/f8b5992d-4a17-40a7-9bdb-3cd3700d08a8.jpg","hospitalId":7},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":22,"imgUrl":"/profile/ho10.jpg","hospitalId":8},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":23,"imgUrl":"/profile/ho11.jpg","hospitalId":8},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":24,"imgUrl":"/profile/ho12.jpg","hospitalId":8},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":32,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/79741f97-f133-4aae-971e-43def8f66933.jpg","hospitalId":9},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":33,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/7c4f86ae-a114-49be-ab91-7675273da183.jpg","hospitalId":9},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":34,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/cfc6d606-dcd0-40d2-8dac-53d0512b7135.jpg","hospitalId":9},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":35,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/be95bed3-c1ae-4aa8-b57d-aecc4bd87d5e.jpg","hospitalId":10},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":36,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/be7b2d12-5199-4ba4-beac-4adc02938013.jpg","hospitalId":10},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":37,"imgUrl":"/prod-api/profile/upload/image/2021/05/11/50d65245-170d-47ec-839e-c9d2fa526d4c.jpg","hospitalId":10}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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
         * imgUrl : /prod-api/profile/upload/image/2021/05/11/921ee654-d6c3-4876-8450-16ac272e18df.jpg
         * hospitalId : 1
         */

        public Object searchValue;
        public Object createBy;
        public Object createTime;
        public Object updateBy;
        public Object updateTime;
        public Object remark;
        public int id;
        public String imgUrl;
        public int hospitalId;

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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }
    }
}
