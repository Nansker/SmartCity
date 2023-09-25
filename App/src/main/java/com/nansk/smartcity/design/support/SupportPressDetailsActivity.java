package com.nansk.smartcity.design.support;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.support.SupportPressBean;
import com.nansk.smartcity.design.DesignResources;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 15:56
 * @description 新闻详情页
 */

public class SupportPressDetailsActivity extends BaseActivity {
    private SupportPressBean pressObj;
    private TextView titleTv;
    private TextView authorTv;
    private TextView dateTv;
    private TextView readNumTv;
    private ImageView imageIv;
    private TextView contentTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_press_details);
        setToolBarTitle("新闻详情");
        initView();
    }

    private void initView() {
        titleTv = (TextView) findViewById(R.id.title_tv);
        authorTv = (TextView) findViewById(R.id.author_tv);
        dateTv = (TextView) findViewById(R.id.date_tv);
        readNumTv = (TextView) findViewById(R.id.readNum_tv);
        imageIv = (ImageView) findViewById(R.id.image_iv);
        contentTv = (TextView) findViewById(R.id.content_tv);

        pressObj = (SupportPressBean) getIntent().getSerializableExtra("pressObj");
        titleTv.setText(pressObj.getTitle());
        readNumTv.setText(pressObj.getRedNum()+"");
        authorTv.setText(pressObj.getAuthor());
        dateTv.setText(pressObj.getData());
        contentTv.setText(pressObj.getContent());
        Glide.with(SupportPressDetailsActivity.this).load(DesignResources.getSupportPressImage(pressObj.getId())).placeholder(R.drawable.default_img).into(imageIv);
    }

}