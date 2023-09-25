package com.nansk.smartcity.design.protection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.protection.ProtectionNearListBean;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 17:28
 * @description
 */

public class ProtectionNearDetailsActivity extends BaseActivity {
    private ProtectionNearListBean object;
    private TextView idTv;
    private TextView fromTv;
    private TextView timeTv;
    private GridLayout classContainer;
    private TextView addressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_protection_near_details);
        setToolBarTitle("回收机详情");
        initView();
        fillData();
    }

    /**
     * @Create 2021/10/17 17:43
     * @Role
     * @Param
     */
    private void fillData() {
        idTv.setText("机器编号：" + object.getId());
        addressTv.setText(object.getAddress());
        timeTv.setText(object.getTime());
        for (int i = 0;i<object.getClassX().size();i++){
            classContainer.addView(getView(object.getClassX().get(i)));
        }
    }

    /**
     * @Create 2021/10/17 17:56
     * @Role
     * @Param
     */
    private View getView(ProtectionNearListBean.ClassBean classBean) {
        View view = LayoutInflater.from(ProtectionNearDetailsActivity.this).inflate(R.layout.item_protection_price, null);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.topMargin =10;
        layoutParams.bottomMargin = 10;
        view.setLayoutParams(layoutParams);

        ImageView imageIv = view.findViewById(R.id.image_iv);
        TextView nameTv = view.findViewById(R.id.name_tv);
        TextView priceTv = view.findViewById(R.id.price_tv);

        nameTv.setText(classBean.getName());
        priceTv.setText(classBean.getPrice());

        switch (classBean.getName()){
            case "饮料瓶":
                imageIv.setImageResource(R.drawable.ep_group101);
                break;
            case "纸类":
                imageIv.setImageResource(R.drawable.ep_group103);
                break;
            case "纺织物":
                imageIv.setImageResource(R.drawable.ep_group102);
                break;
            case "金属":
                imageIv.setImageResource(R.drawable.ep_group104);
                break;
            case "塑料":
                imageIv.setImageResource(R.drawable.ep_group105);
                break;
        }
        return view;
    }

    private void initView() {
        idTv = (TextView) findViewById(R.id.id_tv);
        fromTv = (TextView) findViewById(R.id.from_tv);
        timeTv = (TextView) findViewById(R.id.time_tv);
        classContainer = (GridLayout) findViewById(R.id.class_container);

        object = (ProtectionNearListBean) getIntent().getSerializableExtra("obj");

        addressTv = (TextView) findViewById(R.id.address_tv);
    }
}