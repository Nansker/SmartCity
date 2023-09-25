package com.nansk.smartcity.design.pension;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 10:28
 * @description 集中检测
 */

public class PensionFocusActivity extends BaseActivity {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private CardView viewBox;
    private ImageView iconIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension_focus);
        setToolBarTitle("集中检测");

        initView();
    }

    private void initView() {

        image1 = (ImageView) findViewById(R.id.image_1);
        image2 = (ImageView) findViewById(R.id.image_2);
        image3 = (ImageView) findViewById(R.id.image_3);
        image4 = (ImageView) findViewById(R.id.image_4);
        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);

        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setColorFilter(Color.parseColor("#007aff"));
        }
        viewBox = (CardView) findViewById(R.id.view_box);
        iconIv = (ImageView) findViewById(R.id.icon_iv);

        Glide.with(PensionFocusActivity.this).load(R.drawable.pension_banner4).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iconIv);
        viewBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PensionFocusActivity.this,PensionHealthDataActivity.class));
            }
        });

    }

    ;
}