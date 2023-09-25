package com.nansk.smartcity.design.pension;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.pension.PensionAssessBean;
import com.nansk.smartcity.beans.pension.PensionServiceObj;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 15:52
 * @description 评估结果
 */

public class PensionAssessResultsActivity extends BaseActivity {
    private PensionAssessBean assessObj;
    private LinearLayout serviceContainer;
    private TextView countTv;
    private TextView priceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension_assess_results);
        setToolBarBackground("#b07eef");
        setToolBarTitle("评估结果");
        initView();
    }

    /**
     * @Create 2021/10/19 16:21
     * @Role
     * @Param
     */
    private void initView() {
        assessObj = (PensionAssessBean) getIntent().getSerializableExtra("obj");
        serviceContainer = (LinearLayout) findViewById(R.id.service_container);
        countTv = (TextView) findViewById(R.id.count_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);

        List<PensionServiceObj.RowsBean> result = AutoAssessUtils.getResult(PensionAssessResultsActivity.this, assessObj);

        int price = 0;
        for (int i = 0; i < result.size(); i++) {
            View view = getServiceView(result.get(i).getObj());
            serviceContainer.addView(view);
            price = price + result.get(i).getPrice();
        }
        countTv.setText(Integer.toString(result.size()));
        priceTv.setText(price+"元");


    }

    /**
     * @Create 2021/10/19 19:02
     * @Role
     * @Param
     */
    private View getServiceView(String name) {
        View view = LayoutInflater.from(PensionAssessResultsActivity.this).inflate(R.layout.item_pension_service, null);
        TextView text = view.findViewById(R.id.name_tv);
        text.setText(name);
        return view;
    }


}