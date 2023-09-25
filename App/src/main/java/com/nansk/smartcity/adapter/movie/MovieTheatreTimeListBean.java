package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 10:01
 * @description 电影场次
 */

import java.io.Serializable;
import java.util.List;

public class MovieTheatreTimeListBean {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":21,"theaterId":16,"roomId":35,"movieId":5,"startTime":"04:30","endTime":"06:00","price":36,"playType":"1","playDate":"2021-05-05","saleNum":69,"totalNum":80,"status":"0","theatreName":"CGV影城","roomName":"5号厅","movieName":" 你的婚礼"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":3,"theaterId":3,"roomId":15,"movieId":5,"startTime":"16:45","endTime":"18:45","price":42,"playType":"1","playDate":"2021-05-09","saleNum":50,"totalNum":80,"status":"0","theatreName":"万达影城","roomName":"5号厅","movieName":" 你的婚礼"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":32,"theaterId":6,"roomId":44,"movieId":5,"startTime":"05:15","endTime":"07:45","price":25,"playType":"1","playDate":"2021-05-13","saleNum":58,"totalNum":80,"status":"1","theatreName":"中影华臣影城","roomName":"3号厅","movieName":" 你的婚礼"}]
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
         * id : 21
         * theaterId : 16
         * roomId : /prod-api/api/movie/theatre/list4times
         * movieId : 5
         * startTime : 04:30
         * endTime : 06:00
         * price : 36.0
         * playType : 1
         * playDate : 2021-05-05
         * saleNum : 69
         * totalNum : 80
         * status : 0
         * theatreName : CGV影城
         * roomName : 5号厅
         * movieName :  你的婚礼
         */

        public Object searchValue;
        public Object createBy;
        public Object createTime;
        public Object updateBy;
        public Object updateTime;
        public Object remark;
        public int id;
        public int theaterId;
        public int roomId;
        public int movieId;
        public String startTime;
        public String endTime;
        public double price;
        public String playType;
        public String playDate;
        public int saleNum;
        public int totalNum;
        public String status;
        public String theatreName;
        public String roomName;
        public String movieName;

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

        public int getTheaterId() {
            return theaterId;
        }

        public void setTheaterId(int theaterId) {
            this.theaterId = theaterId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPlayType() {
            return playType;
        }

        public void setPlayType(String playType) {
            this.playType = playType;
        }

        public String getPlayDate() {
            return playDate;
        }

        public void setPlayDate(String playDate) {
            this.playDate = playDate;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTheatreName() {
            return theatreName;
        }

        public void setTheatreName(String theatreName) {
            this.theatreName = theatreName;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }
    }
}
