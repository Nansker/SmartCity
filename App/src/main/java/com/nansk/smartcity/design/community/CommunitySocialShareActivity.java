package com.nansk.smartcity.design.community;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.community.CommunityTiebaBean;
import com.nansk.smartcity.utils.UserInfoUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 22:12
 * @description  发状态
 */

public class CommunitySocialShareActivity extends BaseActivity {
    private int SHARE = 121;
    private EditText titleEt;
    private EditText contentEt;
    private GridLayout imageContainer;
    private LinearLayout addImageBox;

    private List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_commnuioty_social_share);
        setToolBarTitle("发表动态");

        setToolBarRightButton(true, getDrawable("#FF9800",100), "#ffffff", "发表", new MyCallBack() {
            @Override
            public void CallBack() {
                share();
            }
        });
        initView();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 121:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, 121);
                } else {
                    showToast("暂未授权,如需再次使用，请在系统设置中授权打开相册的相关权限", 1200);
                }
        }
    }

    /**
     * @Create 2021/10/18 10:21
     * @Role
     * @Param
     */
    private void share() {
        String title = titleEt.getText().toString().trim();
        String content = contentEt.getText().toString().trim();
        if (!title.isEmpty()) {
            if (!content.isEmpty()) {
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                SimpleDateFormat format = new SimpleDateFormat("YYYY年MM日dd日");
                String time = format.format(date);

                CommunityTiebaBean bean = new CommunityTiebaBean();
                bean.setContent(content);
                bean.setTitle(title);
                bean.setLikeNum(0);
                bean.setReadNum(0);
                bean.setTime(time);
                if (images.size()>0){
                    bean.setImages(images);
                }

                bean.setNickName(UserInfoUtils.getUserInfo(CommunitySocialShareActivity.this).getNickName());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("shareObj",bean);
                intent.putExtras(bundle);
                setResult(SHARE, intent);

                finish();
            } else {
                showToast("请输入要分享的内容", 1200);
            }
        } else {
            showToast("请输入标题", 1200);
        }

    }

    private void showImage() {
        int windowWidth = WindowMangerUtils.getWindowSize(CommunitySocialShareActivity.this, 0)-100;
        if (images.size() > 0) {
            imageContainer.removeAllViews();
            for (int i = 0; i < images.size(); i++) {
                ImageView imageView = new ImageView(CommunitySocialShareActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.leftMargin = 5;
                layoutParams.rightMargin = 5;
                layoutParams.topMargin = 5;
                layoutParams.bottomMargin = 5;

                layoutParams.width = windowWidth /3;
                layoutParams.height = windowWidth /3;


                Glide.with(CommunitySocialShareActivity.this).load(images.get(i)).into(imageView);
                imageContainer.addView(imageView,i,layoutParams);
                setContainerHeight(images.size());
            }
        }
    }


    private void setContainerHeight(int num) {
        int windowWidth = WindowMangerUtils.getWindowSize(CommunitySocialShareActivity.this, 0)-100;
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
        if (ContextCompat.checkSelfPermission(CommunitySocialShareActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CommunitySocialShareActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 121);
        } else {
            addImage();
        }
    }

    private void initView() {
        titleEt = (EditText) findViewById(R.id.title_et);
        contentEt = (EditText) findViewById(R.id.content_et);
        imageContainer = (GridLayout) findViewById(R.id.image_container);
        addImageBox = (LinearLayout) findViewById(R.id.add_image_box);

        int windowWidth = WindowMangerUtils.getWindowSize(CommunitySocialShareActivity.this, 0)-100;
        int windowHeight = WindowMangerUtils.getWindowSize(CommunitySocialShareActivity.this, 1);

        ViewGroup.LayoutParams contentEtLayoutParams = contentEt.getLayoutParams();
        contentEtLayoutParams.height = windowHeight / 5;
        contentEt.setLayoutParams(contentEtLayoutParams);

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