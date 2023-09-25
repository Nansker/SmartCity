package com.nansk.smartcity.beans.park;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 14:53
 * @Description
 */

import java.util.List;

public class ParkRecordListBean {

    /**
     * code : 200
     * msg : 查询成功
     * rows : [{"id":1,"lotId":4,"entryTime":"2021-04-11 17:24:37","outTime":"2021-04-11 18:24:45","plateNumber":"辽 B12345","monetary":"5","parkName":"天津市邦仓储威物流停车场","parkNo":"1","address":"天津市邦仓储威物流"}]
     * total : 1
     */

    public int code;
    public String msg;
    public String total;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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
         * lotId : 4
         * entryTime : 2021-04-11 17:24:37
         * outTime : 2021-04-11 18:24:45
         * plateNumber : 辽 B12345
         * monetary : 5
         * parkName : 天津市邦仓储威物流停车场
         * parkNo : 1
         * address : 天津市邦仓储威物流
         */

        public int id;
        public int lotId;
        public String entryTime;
        public String outTime;
        public String plateNumber;
        public String monetary;
        public String parkName;
        public String parkNo;
        public String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLotId() {
            return lotId;
        }

        public void setLotId(int lotId) {
            this.lotId = lotId;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public String getMonetary() {
            return monetary;
        }

        public void setMonetary(String monetary) {
            this.monetary = monetary;
        }

        public String getParkName() {
            return parkName;
        }

        public void setParkName(String parkName) {
            this.parkName = parkName;
        }

        public String getParkNo() {
            return parkNo;
        }

        public void setParkNo(String parkNo) {
            this.parkNo = parkNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
