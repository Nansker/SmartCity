package com.nansk.smartcity.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.ToUserInfoEditObj;
import com.nansk.smartcity.beans.UploadFileObj;
import com.nansk.smartcity.beans.UploadFileResultBean;
import com.nansk.smartcity.beans.UserInfoBean;
import com.nansk.smartcity.beans.UserInfoEditBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.UserInfoUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/15 14:42
 * @Description 个人设置页
 */

public class UserInfoActivity extends BaseActivity {
    private int MODIFY_ICON = 121;
    private int MODIFY_INFO = 120;

    private LinearLayout tabMenu;
    private ImageView avatarIv;
    private TextView nickNameTv;
    private TextView sexTv;
    private TextView idCardTv;
    private TextView telTv;
    private UserInfoBean.UserBean userInfo;
    private TextView emailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_personal_settings);
        setToolBarTitle("个人信息");
        initView();
        initObject();
        fillData();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fillData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MODIFY_INFO) {
            modifyInfo();
        } else if (requestCode == MODIFY_ICON) {
            if (data == null) {
                return;
            } else {
                Uri uri = data.getData();

                String res = null;
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri, proj, null, null, null);

                if (cursor.moveToFirst()) {
                    int indexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    res = cursor.getString(indexOrThrow);
                }
                Log.i("test",res);
                File file = new File(res);
                modifyIcon(file);
                cursor.close();
            }
        }
    }

    /**
     * @Create 2021/10/21 11:01
     * @Role 修改头像
     * @Param
     */
    private void modifyIcon(File file) {
        if (file != null) {
            UploadFileObj fileObj = new UploadFileObj();
            fileObj.setFile(file);

            String url = ConnectInfo.getUrl(ConnectInfo.UPLOAD, "");
            OkHttpUtil.upload(url, MyApplication.getToken(UserInfoActivity.this), fileObj, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final UploadFileResultBean json = MyApplication.gson.fromJson(response.body().string(), UploadFileResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (json.getCode() == 200) {
                                showToast(json.getUrl());
                            } else {
                                Log.i("test",json.getCode() + json.getMsg());
                                showToast("图片上传失败！"+json.getCode());
                            }
                        }
                    });

                }
            });
        } else {
            showToast("请重新选择头像！");
        }
    }


    /**
     * @Create 2021/10/21 9:37
     * @Role //修改用户头像
     * @Param
     */
    private void openAlbum() {
        if (ContextCompat.checkSelfPermission(UserInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(UserInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0X11);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, MODIFY_ICON);
        }
    }

    /**
     * @Create 2021/10/21 8:51
     * @Role 修改性别
     * @Param
     */
    private void modifySex() {
        final AlertDialog dialog = new AlertDialog.Builder(UserInfoActivity.this).create();
        LinearLayout view = (LinearLayout) LayoutInflater.from(UserInfoActivity.this).inflate(R.layout.dialog_change_sex, null);
        LinearLayout changeBox = (LinearLayout) view.getChildAt(1);
        final ImageView man = changeBox.findViewById(R.id.man_iv);
        final ImageView woman = changeBox.findViewById(R.id.woman_iv);
        dialog.setView(view);
        dialog.show();
        if (userInfo.getSex().equals("0")) {
            man.setVisibility(View.VISIBLE);
            woman.setVisibility(View.INVISIBLE);
        } else if (userInfo.getSex().equals("1")) {
            woman.setVisibility(View.VISIBLE);
            man.setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < changeBox.getChildCount(); i++) {
            final int finalI = i;
            changeBox.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.toString(finalI).equals(userInfo.getSex())) {
                            dialog.dismiss();
                    } else {
                        UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(UserInfoActivity.this);
                        switch (finalI) {
                            case 0:
                                userInfo.setSex("0");
                                man.setVisibility(View.VISIBLE);
                                woman.setVisibility(View.INVISIBLE);
                                break;
                            case 2:
                                userInfo.setSex("1");
                                woman.setVisibility(View.VISIBLE);
                                man.setVisibility(View.INVISIBLE);
                                break;
                        }
                        SharedPreferencesUtils.addRecord(UserInfoActivity.this, "userInfo", MyApplication.gson.toJson(userInfo));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                modifyInfo();
                                dialog.dismiss();
                            }
                        }, 150);

                    }
                }
            });

        }
    }

    private void initObject() {

        for (int i = 0; i < tabMenu.getChildCount(); i++) {
            final int finalI = i;

            tabMenu.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //修改头像
                    if (finalI == 0) {
                        openAlbum();
                        //修改性别
                    } else if (finalI == 2) {
                        modifySex();
                    } else {
                        final Intent intent = new Intent(UserInfoActivity.this, UserInfoEditActivity.class);
                        final Bundle bundle = new Bundle();
                        final ToUserInfoEditObj editObj = new ToUserInfoEditObj();
                        switch (finalI) {
                            case 1:
                                editObj.setKey("nickName");
                                editObj.setValue(userInfo.getNickName());
                                break;
                            case 4:
                                editObj.setKey("idCard");
                                editObj.setValue(userInfo.getIdCard());
                                break;
                            case 3:
                                editObj.setKey("email");
                                editObj.setValue(userInfo.getEmail());
                                break;
                            case 5:
                                editObj.setKey("phonenumber");
                                editObj.setValue(userInfo.getPhonenumber());
                                break;
                        }
                        bundle.putSerializable("editObj", editObj);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, MODIFY_INFO);
                    }
                }
            });
        }

    }

    private void initView() {
        tabMenu = (LinearLayout) findViewById(R.id.tab_menu);

        avatarIv = (ImageView) findViewById(R.id.avatar_iv);
        nickNameTv = (TextView) findViewById(R.id.nickName_tv);
        sexTv = (TextView) findViewById(R.id.sex_tv);
        idCardTv = (TextView) findViewById(R.id.idCard_tv);
        telTv = (TextView) findViewById(R.id.tel_tv);
        emailTv = (TextView) findViewById(R.id.email_tv);
    }

    /**
     * @Create 2021/9/15 15:10
     * @Role 获取个人信息，显示到页面上
     * @Param
     */
    private void fillData() {
        userInfo = UserInfoUtils.getUserInfo(UserInfoActivity.this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Glide.with(UserInfoActivity.this).load(MyApplication.IP + "/prod-api" + userInfo.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).placeholder(R.drawable.user_icon).into(avatarIv);
                nickNameTv.setText(userInfo.getNickName());

                if (userInfo.getIdCard() != null) {
                    idCardTv.setText(SystemUtils.getValue(userInfo.getIdCard().substring(0, 2) + "************" + userInfo.getIdCard().substring(userInfo.getIdCard().length() - 4), ""));
                }

                telTv.setText(SystemUtils.getValue(userInfo.getPhonenumber(), ""));
                emailTv.setText(SystemUtils.getValue(userInfo.getEmail(), ""));

                if (userInfo.getSex().equals("0")) {
                    sexTv.setText("男");
                } else if (userInfo.getSex().equals("1")) {
                    sexTv.setText("女");
                }
            }
        });

    }

    /**
     * @Create 2021/9/15 15:37
     * @Role 修改用户信息
     * @Param
     */
    private void modifyInfo() {
        final UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(UserInfoActivity.this);

        final UserInfoEditBean editBean = new UserInfoEditBean();

        editBean.setNickName(userInfo.getNickName());
        editBean.setEmail(userInfo.getEmail());
        editBean.setIdCard(userInfo.getIdCard());
        editBean.setPhonenumber(userInfo.getPhonenumber());
        editBean.setSex(userInfo.getSex());

        String url = ConnectInfo.getUrl(ConnectInfo.USER_COMMON_INFO, "");

        OkHttpUtil.doPut(url, MyApplication.getToken(this), editBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            showToast("信息修改成功！", 1200);
                            fillData();
                            //重服务器出现获取信息
                            UserInfoUtils.updateUserInfo(UserInfoActivity.this);
                        } else {
                            showToast(jsonObj.getMsg(), 1200);
                        }

                    }
                });

            }
        });
    }

}