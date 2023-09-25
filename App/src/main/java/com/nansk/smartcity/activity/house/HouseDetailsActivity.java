package com.nansk.smartcity.activity.house;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.house.HouseDetailsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 10:02
 * @Description 房源信息详情页
 */

public class HouseDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageIv;
    private TextView houseTypeTv;
    private TextView sourceNameTv;
    private TextView addressTv;
    private TextView descriptionTv;
    private TextView priceTv;
    private TextView areaSizeTv;
    private Button backBtn;
    private Button phoneBtn;

    private String houseId;
    private Gson gson;
    private TextView phoneTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_house_details);
        setToolBarTitle("房源详情");
        setToolBarBackground("#37c2bb");
        initView();
        if (houseId != null) {
            fillData(houseId);
        } else {
            finish();
        }

    }


    private void initView() {

        imageIv = (ImageView) findViewById(R.id.image_iv);
        houseTypeTv = (TextView) findViewById(R.id.houseType_tv);
        sourceNameTv = (TextView) findViewById(R.id.sourceName_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);
        descriptionTv = (TextView) findViewById(R.id.description_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);
        areaSizeTv = (TextView) findViewById(R.id.areaSize_tv);
        backBtn = (Button) findViewById(R.id.back_btn);
        phoneBtn = (Button) findViewById(R.id.phone_btn);
        phoneTv = (TextView) findViewById(R.id.phone_tv);

        backBtn.setOnClickListener(this);
        phoneBtn.setOnClickListener(this);

        Intent intent = getIntent();
        houseId = intent.getStringExtra("houseId");

        gson = new Gson();
    }

    private void fillData(String id) {
        String url = ConnectInfo.getUrl(ConnectInfo.HOUSE_HOUSING, id);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final HouseDetailsBean json = gson.fromJson(response.body().string(), HouseDetailsBean.class);
                if (json.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final HouseDetailsBean.DataBean data = json.getData();
                            Glide.with(HouseDetailsActivity.this).load(MyApplication.IP + data.getPic()).into(imageIv);
                            priceTv.setText(data.getPrice());
                            houseTypeTv.setText(data.getHouseType());
                            sourceNameTv.setText(data.getSourceName());
                            addressTv.setText(data.getAddress());
                            descriptionTv.setText("房屋简介："+data.getDescription());
                            areaSizeTv.setText(data.getAreaSize() + "平方米");
                            phoneTv.setText("联系方式："+data.getTel());

                            phoneBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.getTel()));

                                    startActivity(intent);
                                }
                            });

                        }
                    });
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
        }
    }
}