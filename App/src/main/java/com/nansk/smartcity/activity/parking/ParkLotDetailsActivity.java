package com.nansk.smartcity.activity.parking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.park.ParkLotListBean;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 11:39
 * @Description
 */

public class ParkLotDetailsActivity extends BaseActivity {
    private ParkLotListBean.RowsBean parkLotObj;

    private TextView parkNameTv;
    private TextView distanceTv;
    private TextView openTv;
    private TextView addressTv;
    private TextView ratesTv;
    private TextView vacancyTv;
    private TextView allParkTv;
    private TextView priceCapsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_park_lot_details);
        setToolBarTitle("停车场详情");

        Intent intent = getIntent();
        parkLotObj = (ParkLotListBean.RowsBean) intent.getSerializableExtra("parkLotObj");

        initView();

        fillData();
    }


    private void initView() {

        parkNameTv = (TextView) findViewById(R.id.parkName_tv);
        distanceTv = (TextView) findViewById(R.id.distance_tv);
        openTv = (TextView) findViewById(R.id.open_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);
        ratesTv = (TextView) findViewById(R.id.rates_tv);
        vacancyTv = (TextView) findViewById(R.id.vacancy_tv);
        allParkTv = (TextView) findViewById(R.id.allPark_tv);
        priceCapsTv = (TextView) findViewById(R.id.priceCaps_tv);


    }


    private void fillData() {
        parkNameTv.setText(parkLotObj.getParkName());
        distanceTv.setText(parkLotObj.getDistance()+"km");

        if (parkLotObj.getOpen().equals("Y")){
            openTv.setText("对外开放");
        }else if (parkLotObj.getOpen().equals("N")){
            openTv.setText("暂时关闭");
        }

        addressTv.setText(parkLotObj.getAddress());
        ratesTv.setText(parkLotObj.getRates()+"元");
        vacancyTv.setText(parkLotObj.getVacancy()+"个");
        allParkTv.setText("/"+parkLotObj.getAllPark());
        priceCapsTv.setText("每小时"+parkLotObj.getRates()+"元，最高"+parkLotObj.getPriceCaps()+"元一天");
    }
}