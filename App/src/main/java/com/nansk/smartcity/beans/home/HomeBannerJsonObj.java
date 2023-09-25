package com.nansk.smartcity.beans.home;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 16:52
 * @Description
 */

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 21:00
 * @Description
 */

public class HomeBannerJsonObj {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:39:53","updateBy":"B018","updateTime":"2021-09-05 17:57:34","remark":null,"params":{},"id":24,"appType":"smart_city","status":"1","sort":1,"advTitle":"开屏广告","advImg":"/prod-api/profile/upload/image/2021/05/06/d2aeef1a-7c47-46bc-8534-20b3d14cd8eb.png","servModule":"","targetId":null,"type":"1"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:40:17","updateBy":"1","updateTime":"2021-06-25 11:02:40","remark":null,"params":{},"id":25,"appType":"smart_city","status":"1","sort":2,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/b9d9f081-8a76-41dc-8199-23bcb3a64fcc.png","servModule":"新闻详情","targetId":28,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:40:59","updateBy":"1111129","updateTime":"2021-06-25 11:02:51","remark":null,"params":{},"id":26,"appType":"smart_city","status":"1","sort":3,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/e614cb7f-63b0-4cda-bf47-db286ea1b074.png","servModule":"新闻详情","targetId":29,"type":"2"},{"searchValue":null,"createBy":"admin","createTime":"2021-05-06 15:41:20","updateBy":"1111129","updateTime":"2021-06-25 11:03:10","remark":null,"params":{},"id":27,"appType":"smart_city","status":"1","sort":4,"advTitle":"首页轮播","advImg":"/prod-api/profile/upload/image/2021/05/06/242e06f7-9fb0-4e16-b197-206f999c98f2.png","servModule":"新闻详情","targetId":30,"type":"2"}]
     * code : 200
     * msg : 查询成功
     */
    public int total;
    public int code;
    public String msg;
    public List<HomeBannerJsonRows> rows;

    public HomeBannerJsonObj() {
    }

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

    public List<HomeBannerJsonRows> getRows() {
        return rows;
    }

    public void setRows(List<HomeBannerJsonRows> rows) {
        this.rows = rows;
    }
}
