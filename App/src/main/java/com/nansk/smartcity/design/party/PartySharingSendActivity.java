package com.nansk.smartcity.design.party;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 12:13
 * @description
 */

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.DesignSharingBean;
import com.nansk.smartcity.dialog.ToastDialog;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartySharingSendActivity extends BaseActivity {
    private int SHARE = 121;
    private EditText titleEt;
    private EditText contentEt;
    private GridLayout imageContainer;
    private LinearLayout addImageBox;
    private List<String> images;

    private ToastDialog toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_sharing_send);
        setToolBarTitle("发现身边事");
        setToolBarRightButton(true, getDrawable(getResources().getString(R.string.theme_party), 100), "#ffffff", "发送", new MyCallBack() {
            @Override
            public void CallBack() {
                sharing();
            }
        });
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 121:
                if (data != null) {
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
    private void sharing() {
        String title = titleEt.getText().toString().trim();
        String content = contentEt.getText().toString().trim();
        if (!title.isEmpty()) {
            if (!content.isEmpty()) {
                DesignSharingBean sharingBean = new DesignSharingBean();
                sharingBean.setTitle(title);
                sharingBean.setContent(content);
                sharingBean.setNickName(UserInfoUtils.getUserInfo(PartySharingSendActivity.this).getNickName());

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                long timeMillis = System.currentTimeMillis();
                Date date = new Date(timeMillis);
                sharingBean.setDate(format.format(date));

                sharingBean.setLikeNum(0);
                sharingBean.setReadNum(0);

                if (images.size() > 0) {
                    sharingBean.setImages(images);
                }

                List<DesignSharingBean> list = new ArrayList<>();
                list.add(sharingBean);

                String record = (String) SharedPreferencesUtils.getRecord(PartySharingSendActivity.this, "party_sharing", "");

                if (!record.equals("")) {
                    List<DesignSharingBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<DesignSharingBean>>() {
                    }.getType());
                    for (int i = 0; i < json.size(); i++) {
                        list.add(json.get(i));
                    }
                }
                SharedPreferencesUtils.addRecord(PartySharingSendActivity.this, "party_sharing", MyApplication.gson.toJson(list));

                toast.showReveal("发送中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.setMsg("发送成功！");
                    }
                }, 800);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("isUpdate", true);
                        setResult(SHARE, intent);
                        finish();
                    }
                }, 1200);

            } else {
                showToast("请输入要分享的内容", 1200);
            }
        } else {
            showToast("请输入标题", 1200);
        }

    }

    private void showImage() {
        int windowWidth = WindowMangerUtils.getWindowSize(PartySharingSendActivity.this, 0) - 100;
        if (images.size() > 0) {
            imageContainer.removeAllViews();
            for (int i = 0; i < images.size(); i++) {
                ImageView imageView = new ImageView(PartySharingSendActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.leftMargin = 5;
                layoutParams.rightMargin = 5;
                layoutParams.topMargin = 5;
                layoutParams.bottomMargin = 5;

                layoutParams.width = windowWidth / 3;
                layoutParams.height = windowWidth / 3;

                Glide.with(PartySharingSendActivity.this).load(images.get(i)).into(imageView);
                imageContainer.addView(imageView, i, layoutParams);
                setContainerHeight(images.size());
            }
        }
    }


    private void setContainerHeight(int num) {
        int windowWidth = WindowMangerUtils.getWindowSize(PartySharingSendActivity.this, 0) - 100;
        ViewGroup.LayoutParams imagesParams = imageContainer.getLayoutParams();

        switch (num) {
            case 1:
                imagesParams.height = windowWidth / 3;
                break;
            case 4:
                imagesParams.height = windowWidth * 2 / 3;
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
        if (ContextCompat.checkSelfPermission(PartySharingSendActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PartySharingSendActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 121);
        } else {
            addImage();
        }
    }

    private void initView() {
        titleEt = (EditText) findViewById(R.id.title_et);
        contentEt = (EditText) findViewById(R.id.content_et);
        imageContainer = (GridLayout) findViewById(R.id.image_container);
        addImageBox = (LinearLayout) findViewById(R.id.add_image_box);

        int windowWidth = WindowMangerUtils.getWindowSize(PartySharingSendActivity.this, 0) - 100;

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
        toast = new ToastDialog(PartySharingSendActivity.this);
    }

}