package com.nansk.smartcity.design.pension;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 16:23
 * @description 根据对象自动评估并反馈结果
 */

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.pension.PensionAssessBean;
import com.nansk.smartcity.beans.pension.PensionServiceObj;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class AutoAssessUtils {
    public static List<PensionServiceObj.RowsBean> getResult(Context context, PensionAssessBean obj) {
        List<PensionServiceObj.RowsBean> serviceList = new ArrayList<>();

        String data = FileReadUtils.getData(context, R.raw.pension_assess);
        List<PensionServiceObj> json = MyApplication.gson.fromJson(data, new TypeToken<List<PensionServiceObj>>() {
        }.getType());
        switch (obj.getBasicStatus()){
            case "良好":
            case "一般":
                serviceList.add(json.get(1).getRows().get(0));
                serviceList.add(json.get(3).getRows().get(0));
                serviceList.add(json.get(3).getRows().get(1));
                serviceList.add(json.get(4).getRows().get(1));
                break;
            case "欠佳":
                serviceList.add(json.get(1).getRows().get(0));
                serviceList.add(json.get(1).getRows().get(1));
                serviceList.add(json.get(3).getRows().get(0));
                serviceList.add(json.get(3).getRows().get(1));
                serviceList.add(json.get(3).getRows().get(2));
                break;
        }

        return serviceList;

    }

}
