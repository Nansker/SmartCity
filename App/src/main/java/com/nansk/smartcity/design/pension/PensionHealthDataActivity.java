package com.nansk.smartcity.design.pension;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 21:52
 * @description 健康数据
 */

public class PensionHealthDataActivity extends BaseActivity {

    private ImageView iconIv;
    private ImageView dateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension_health_data);
        setToolBarTitle("健康数据");
        setToolBarBackground("#b07eef");
        initView();
    }

    private void initView() {

        iconIv = (ImageView) findViewById(R.id.icon_iv);
        dateTv = (ImageView) findViewById(R.id.date_tv);

        Glide.with(PensionHealthDataActivity.this).load(R.drawable.pension_banner4).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iconIv);
        dateTv.setColorFilter(Color.parseColor("#007aff"));
    }
}