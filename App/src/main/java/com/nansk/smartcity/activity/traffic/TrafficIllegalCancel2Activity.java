package com.nansk.smartcity.activity.traffic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.UploadFileObj;
import com.nansk.smartcity.beans.UploadFileResultBean;
import com.nansk.smartcity.beans.traffic.TrafficIllegalCancelBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/08 19:25
 * @description 撤销申请提交页面
 */

public class TrafficIllegalCancel2Activity extends BaseActivity {
    private int illegalId;

    private EditText bodyEt;
    private Button submitBtn;
    private ImageView addPhotoIv;
    private ImageView imageIv;

    private TrafficIllegalCancelBean cancelBean;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_illegal_cancel2);
        setToolBarTitle("提交撤销申请");
        setToolBarBackground("#2c65a8");
        initView();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0x11:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 0x11);
                } else {
                    showToast("暂未授权,如需再次使用，请在系统设置中授权打开相册的相关权限");
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x11 && resultCode == RESULT_OK) {
            if (data != null) {
                //获取图片路径
                Uri photoUri = data.getData();
                photoPath = photoUri.getPath();

                Glide.with(TrafficIllegalCancel2Activity.this).load(photoUri).into(imageIv);
                File file = new File(photoPath);
                uploadPhoto(file);
            }
        }
    }

    /**
     * @Create 2021/10/8 20:36
     * @Role 从相册选择图片并上传到服务器
     * @Param
     */
    private void selectPhoto() {

        if (ContextCompat.checkSelfPermission(TrafficIllegalCancel2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TrafficIllegalCancel2Activity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    0x11);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 0x11);
        }
    }

    /**
     * @Create 2021/10/8 19:41
     * @Role 判断用户输入内容，上传照片
     * @Param
     */
    private void uploadPhoto(File file) {
        //先上传图片
        if (file != null) {
            UploadFileObj fileObj = new UploadFileObj();
            fileObj.setFile(file);
            String url = ConnectInfo.getUrl(ConnectInfo.UPLOAD, "");
            OkHttpUtil.doPost(url, MyApplication.getToken(this), file, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    final UploadFileResultBean jsonObj = MyApplication.gson.fromJson(string, UploadFileResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200) {
                                showToast("图片上传成功！");
                                Glide.with(TrafficIllegalCancel2Activity.this).load(jsonObj.getUrl()).into(imageIv);
                                cancelBean.setPhoto(jsonObj.getUrl());
                            } else {
                                showToast("图片上传失败！"+jsonObj.getCode());
                            }
                        }
                    });
                }
            });

        } else {
            showToast("请先选择举证图片");
        }
    }


    /**
     * @Create 2021/10/8 21:21
     * @Role 提交申请
     * @Param
     */
    private void submit() {
        String body = bodyEt.getText().toString().trim();
        //获取系统时间
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(timeMillis);
        String format = simpleDateFormat.format(date);

        cancelBean.setIllegalId(illegalId);
        cancelBean.setReason(body);
        cancelBean.setApplyDate(format);

        //提交申请
        String cancelUrl = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "/cancel");
        OkHttpUtil.doPost(cancelUrl, MyApplication.getToken(TrafficIllegalCancel2Activity.this), cancelBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            showToast("申请提交成功！");
                        } else {
                            showToast(jsonObj.getMsg());
                        }
                    }
                });
            }
        });

    }

    private void initView() {

        addPhotoIv = (ImageView) findViewById(R.id.addPhoto_iv);
        imageIv = (ImageView) findViewById(R.id.image_iv);

        bodyEt = (EditText) findViewById(R.id.body_et);
        submitBtn = (Button) findViewById(R.id.submit_btn);

        Intent intent = getIntent();
        illegalId = intent.getIntExtra("illegalId", 0);
        cancelBean = new TrafficIllegalCancelBean();

        submitBtn.setBackground(getDrawable("#2c65a8", 10));
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bodyEt.getText().toString().trim().equals("")) {
                    if (photoPath != null) {
                        submit();
                    } else {
                        showToast("请添加举证照片！");
                    }
                } else {
                    showToast("撤销原因不能为空！");
                }
            }
        });

        addPhotoIv.setBackgroundResource(R.color.divider_gray);
        addPhotoIv.setColorFilter(Color.parseColor("#999999"));
        addPhotoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });

    }
}