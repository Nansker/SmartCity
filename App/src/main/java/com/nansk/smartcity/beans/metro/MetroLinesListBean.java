package com.nansk.smartcity.beans.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 17:51
 * @description 所有线路列表
 */

import java.util.List;

public class MetroLinesListBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"lineId":1,"lineName":"16 号线"},{"lineId":2,"lineName":"s1 线"}]
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
         * lineId : 1
         * lineName : 16 号线
         */

        public int lineId;
        public String lineName;

        public int getLineId() {
            return lineId;
        }

        public void setLineId(int lineId) {
            this.lineId = lineId;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }
    }
}
