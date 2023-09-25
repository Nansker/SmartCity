package com.nansk.smartcity.model;
/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 17:23
 * @description
 */

public interface OnItemCallBack<T> {
    void OnItemCallBack(int position, T obj);
}
