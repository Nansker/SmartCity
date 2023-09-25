package com.nansk.smartcity.beans.pension;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 16:07
 * @description
 */

import java.io.Serializable;

public class PensionAssessBean implements Serializable {
    private String basicStatus;//基本情况
    private String mindStatus;//精神状态
    private String activityPower;//活动能力
    private String ability;//生活能力

    public String getBasicStatus() {
        return basicStatus;
    }

    public void setBasicStatus(String basicStatus) {
        this.basicStatus = basicStatus;
    }

    public String getMindStatus() {
        return mindStatus;
    }

    public void setMindStatus(String mindStatus) {
        this.mindStatus = mindStatus;
    }

    public String getActivityPower() {
        return activityPower;
    }

    public void setActivityPower(String activityPower) {
        this.activityPower = activityPower;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}
