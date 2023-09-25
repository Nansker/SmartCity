package com.nansk.smartcity.beans;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/21 11:28
 * @description
 */

public class ModifyPasswordBean {
    private String newPassword;
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
