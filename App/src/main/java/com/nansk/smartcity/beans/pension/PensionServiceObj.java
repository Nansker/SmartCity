package com.nansk.smartcity.beans.pension;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 16:26
 * @description
 */

import java.util.List;

public class PensionServiceObj {

    /**
     * name : 住宿服务
     * rows : [{"obj":"定时免费洗浴,服务员24小时值班，每天进行房间卫生清洁","price":31},{"obj":"定期更换床上用品","price":12},{"obj":"定期换洗窗帘","price":12}]
     */

    public String name;
    public List<RowsBean> rows;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * obj : 定时免费洗浴,服务员24小时值班，每天进行房间卫生清洁
         * price : 31
         */

        public String obj;
        public int price;

        public String getObj() {
            return obj;
        }

        public void setObj(String obj) {
            this.obj = obj;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
