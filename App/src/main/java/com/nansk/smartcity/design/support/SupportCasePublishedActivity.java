package com.nansk.smartcity.design.support;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/21 13:04
 * @description 案例发布
 */

public class SupportCasePublishedActivity extends BaseActivity {

    private EditText titleEt;
    private EditText contentEt;
    private GridLayout imageContainer;
    private LinearLayout addImageBox;

    private List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_case_published);
        setToolBarTitle("发布案例");
        setToolBarRightButton(true, getDrawable(getResources().getString(R.string.theme_party), 100), "#ffffff", "发布", new MyCallBack() {
            @Override
            public void CallBack() {
                published();
            }
        });
        initView();
    }

    /**
     * @Create 2021/10/21 13:53
     * @Role
     * @Param
     */
    private void published() {
        if (!titleEt.getText().toString().trim().equals("")){
            if (!contentEt.getText().toString().trim().equals("")){
                showToast("发布成功！");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1200);
            }else {
                showToast("请输入案例内容");
            }
        }else {
            showToast("请输入案例名称");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 121:
                if (data != null){
                    Uri uri = data.getData();
                    if (images.size() <= 8) {
                        images.add(uri.toString());
                        showImage();
                    } else {
                        showToast("最多只能选择九张图片！");
                    }
                }
                break;
        }
    }

    private void showImage() {
        int windowWidth = WindowMangerUtils.getWindowSize(SupportCasePublishedActivity.this, 0)-100;
        if (images.size() > 0) {
            imageContainer.removeAllViews();
            for (int i = 0; i < images.size(); i++) {
                ImageView imageView = new ImageView(SupportCasePublishedActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.leftMargin = 5;
                layoutParams.rightMargin = 5;
                layoutParams.topMargin = 5;
                layoutParams.bottomMargin = 5;

                layoutParams.width = windowWidth /3;
                layoutParams.height = windowWidth /3;


                Glide.with(SupportCasePublishedActivity.this).load(images.get(i)).into(imageView);
                imageContainer.addView(imageView,i,layoutParams);
                setContainerHeight(images.size());
            }
        }
    }


    private void setContainerHeight(int num) {
        int windowWidth = WindowMangerUtils.getWindowSize(SupportCasePublishedActivity.this, 0)-100;
        ViewGroup.LayoutParams imagesParams = imageContainer.getLayoutParams();

        switch (num){
            case 1:
                imagesParams.height = windowWidth/3;
                break;
            case 4:
                imagesParams.height = windowWidth*2/3;
                break;
            case 7:
                imagesParams.height = windowWidth;
                break;
        }
        imageContainer.setLayoutParams(imagesParams);
    }

    /**
     * @Create 2021/10/18 10:42
     * @Role 添加照片
     * @Param
     */
    private void addImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 121);
    }

    private void openAlbum() {
        if (ContextCompat.checkSelfPermission(SupportCasePublishedActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SupportCasePublishedActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 121);
        } else {
            addImage();
        }
    }
    /**
     * @Create 2021/10/21 13:34
     * @Role
     * @Param
     */
    private void initView() {

        titleEt = (EditText) findViewById(R.id.title_et);
        contentEt = (EditText) findViewById(R.id.content_et);
        imageContainer = (GridLayout) findViewById(R.id.image_container);
        addImageBox = (LinearLayout) findViewById(R.id.add_image_box);

        int windowWidth= WindowMangerUtils.getWindowSize(SupportCasePublishedActivity.this, 0)-100;
        ViewGroup.LayoutParams layoutParams = addImageBox.getLayoutParams();
        layoutParams.width = windowWidth / 3;
        layoutParams.height = windowWidth / 3;
        addImageBox.setBackground(getDrawable("#ffffff", 15, 4, "#f2f2f2"));
        addImageBox.setLayoutParams(layoutParams);
        addImageBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });

        images = new ArrayList<>();
    }
}