package com.nansk.smartcity.beans.home;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 17:00
 * @Description
 */

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 21:00
 * @Description
 */

public class HomeBannerJsonRows {
    /**
     * searchValue : null
     * createBy : admin
     * createTime : 2021-05-06 15:39:53
     * updateBy : B018
     * updateTime : 2021-09-05 17:57:34
     * remark : null
     * params : {}
     * id : 24
     * appType : smart_city
     * status : 1
     * sort : 1
     * advTitle : 开屏广告
     * advImg : /prod-api/profile/upload/image/2021/05/06/d2aeef1a-7c47-46bc-8534-20b3d14cd8eb.png
     * servModule :
     * targetId : null
     * type : 1
     */

    public Object searchValue;
    public String remark;
    public int id;
    public String appType;
    public String status;
    public int sort;
    public String advTitle;
    public String advImg;
    public String servModule;
    public int targetId;
    public String type;

    public HomeBannerJsonRows() {
    }

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getServModule() {
        return servModule;
    }

    public void setServModule(String servModule) {
        this.servModule = servModule;
    }

    public Object getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
