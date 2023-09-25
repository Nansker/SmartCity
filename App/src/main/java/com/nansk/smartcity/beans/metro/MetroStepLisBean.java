package com.nansk.smartcity.beans.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 19:09
 * @description 站点列表
 */

import java.io.Serializable;
import java.util.List;

public class MetroStepLisBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"name":"北安河","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"稻香湖路","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"马连洼","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"农大南路","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"屯佃","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"温阳路","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"西北旺","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"西苑","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":15,"lineName":"4号线大兴线"},{"lineId":25,"lineName":"4号线大兴线"},{"lineId":30,"lineName":"16号线"}]},{"name":"永丰","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]},{"name":"永丰南","lines":[{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]}]
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

    public static class DataBean implements Serializable {
        /**
         * name : 北安河
         * lines : [{"lineId":1,"lineName":"16号线"},{"lineId":30,"lineName":"16号线"}]
         */

        public String name;
        public List<LinesBean> lines;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<LinesBean> getLines() {
            return lines;
        }

        public void setLines(List<LinesBean> lines) {
            this.lines = lines;
        }

        public static class LinesBean implements Serializable{
            /**
             * lineId : 1
             * lineName : 16号线
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
}
