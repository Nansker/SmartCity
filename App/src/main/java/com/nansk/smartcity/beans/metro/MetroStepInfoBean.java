package com.nansk.smartcity.beans.metro;

import java.util.List;
/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 13:44
 * @description.根据站点名和线路编号查找站点信息
 */
public class MetroStepInfoBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"stepInfo":{"name":"东直门","crowd":1},"lineList":[{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 14:53:43","remark":null,"params":{},"id":31,"name":"2号线","first":"内环(积水潭)","end":"积水潭","startTime":"05:10","endTime":"22:20","cityId":1,"cityName":"北京市"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 14:53:43","remark":null,"params":{},"id":32,"name":"机场线","first":"东直门","end":"东直门","startTime":"05:40","endTime":"23:00","cityId":1,"cityName":"北京市"}]}
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
         * stepInfo : {"name":"东直门","crowd":1}
         * lineList : [{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 14:53:43","remark":null,"params":{},"id":31,"name":"2号线","first":"内环(积水潭)","end":"积水潭","startTime":"05:10","endTime":"22:20","cityId":1,"cityName":"北京市"},{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:40","updateBy":null,"updateTime":"2021-04-05 14:53:43","remark":null,"params":{},"id":32,"name":"机场线","first":"东直门","end":"东直门","startTime":"05:40","endTime":"23:00","cityId":1,"cityName":"北京市"}]
         */

        public StepInfoBean stepInfo;
        public List<LineListBean> lineList;

        public StepInfoBean getStepInfo() {
            return stepInfo;
        }

        public void setStepInfo(StepInfoBean stepInfo) {
            this.stepInfo = stepInfo;
        }

        public List<LineListBean> getLineList() {
            return lineList;
        }

        public void setLineList(List<LineListBean> lineList) {
            this.lineList = lineList;
        }

        public static class StepInfoBean {
            /**
             * name : 东直门
             * crowd : 1
             */

            public String name;
            public int crowd;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCrowd() {
                return crowd;
            }

            public void setCrowd(int crowd) {
                this.crowd = crowd;
            }
        }

        public static class LineListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2018-07-23 02:28:40
             * updateBy : null
             * updateTime : 2021-04-05 14:53:43
             * remark : null
             * params : {}
             * id : 31
             * name : 2号线
             * first : 内环(积水潭)
             * end : 积水潭
             * startTime : 05:10
             * endTime : 22:20
             * cityId : 1
             * cityName : 北京市
             */

            public Object searchValue;
            public Object createBy;
            public String createTime;
            public Object updateBy;
            public String updateTime;
            public Object remark;
            public int id;
            public String name;
            public String first;
            public String end;
            public String startTime;
            public String endTime;
            public int cityId;
            public String cityName;

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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFirst() {
                return first;
            }

            public void setFirst(String first) {
                this.first = first;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }
        }
    }
}
