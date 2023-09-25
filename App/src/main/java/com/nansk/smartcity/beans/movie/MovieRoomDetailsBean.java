package com.nansk.smartcity.beans.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 17:13
 * @description
 */

public class MovieRoomDetailsBean {

    /**
     * code : 200
     * data : {"id":3,"theaterId":1,"name":"2 厅","status":"N"}
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
         * id : 3
         * theaterId : 1
         * name : 2 厅
         * status : N
         */

        public int id;
        public int theaterId;
        public String name;
        public String status;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
