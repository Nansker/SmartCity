package com.nansk.smartcity.design.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.support.SupportVillageBean;
import com.nansk.smartcity.design.DesignResources;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 17:40
 * @description
 */

public class SupportVillageDetailsActivity extends BaseActivity {
    private SupportVillageBean villageObj;

    private TextView titleTv;
    private ImageView imageIv;
    private TextView contentTv;
    private TextView authorTv;
    private TextView dateTv;
    private TextView readNumTv;
    private Button planBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_village_details);
        setToolBarTitle("村庄详情");
        initView();
    }

    private void initView() {

        titleTv = (TextView) findViewById(R.id.title_tv);
        imageIv = (ImageView) findViewById(R.id.image_iv);
        contentTv = (TextView) findViewById(R.id.content_tv);
        authorTv = (TextView) findViewById(R.id.author_tv);
        dateTv = (TextView) findViewById(R.id.date_tv);
        readNumTv = (TextView) findViewById(R.id.readNum_tv);
        planBtn = (Button) findViewById(R.id.plan_btn);

        villageObj = (SupportVillageBean) getIntent().getSerializableExtra("villageObj");

        titleTv.setText(villageObj.getName());
        readNumTv.setText(villageObj.getReadNum() + "");
        authorTv.setText(villageObj.getAuthor());
        dateTv.setText(villageObj.getDate());
        contentTv.setText(villageObj.getIntroduce());
        Glide.with(SupportVillageDetailsActivity.this).load(DesignResources.getSupportPressImage(villageObj.getId())).placeholder(R.drawable.default_img).into(imageIv);

        planBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party),100));
        planBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportVillageDetailsActivity.this, SupportVillagePlanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("villageObj",villageObj);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}