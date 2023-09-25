package com.nansk.smartcity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.nansk.smartcity.model.CheckLogin;
import com.nansk.smartcity.NetworkSettingsActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.AllOrdersActivity;
import com.nansk.smartcity.activity.ChangePasswordActivity;
import com.nansk.smartcity.activity.FeedBackActivity;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.activity.UserInfoActivity;
import com.nansk.smartcity.activity.BalanceTopUpActivity;
import com.nansk.smartcity.beans.UserInfoBean;
import com.nansk.smartcity.dialog.LoginOutConfirmDialog;
import com.nansk.smartcity.model.impl.CheckLoginUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.UserInfoUtils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 13:06
 * @Description 个人中心
 */

public class PersonFragment extends Fragment {
    private View view;
    private String[] funNames;

    private ImageView avatarIv;
    private TextView nickNameTv;
    private LinearLayout userInfoBox;
    private LinearLayout tabMenu;

    private Gson gson;
    private TextView logoTv;
    private LinearLayout loginBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, container, false);
        initView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        UpdatingUserStatus();
    }

    private void initView() {
        userInfoBox = (LinearLayout) view.findViewById(R.id.userInfo_box);
        tabMenu = (LinearLayout) view.findViewById(R.id.tab_menu);
        avatarIv = (ImageView) view.findViewById(R.id.avatar_iv);
        nickNameTv = (TextView) view.findViewById(R.id.nickName_tv);
        logoTv = (TextView) view.findViewById(R.id.logo_tv);
        loginBox = (LinearLayout) view.findViewById(R.id.login_box);
        nickNameTv.setTextColor(Color.WHITE);

        gson = MyApplication.gson;

        //点击事件(登录按钮单独设置监听事件)
        for (int i = 0; i < tabMenu.getChildCount() - 1; i++) {
            final int finalI = i;
            tabMenu.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMenuClick(finalI);
                }
            });
        }

        userInfoBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLoginUtils.isLogin(getContext(), false, new CheckLogin() {
                    @Override
                    public void onAlready() {
                        startActivity(new Intent(getContext(),UserInfoActivity.class));
                    }

                    @Override
                    public void onNone() {
                        startActivity(new Intent(getContext(),LoginActivity.class));
                    }
                });
            }
        });


    }

    /**
     * @Create 2021/9/14 20:38
     * @Role 更新用户状态，设置登录按钮状态样式点击事件
     * @Param
     */
    private void UpdatingUserStatus() {
        CheckLoginUtils.isLogin(getContext(), false, new CheckLogin() {
            @Override
            public void onAlready() {
                //查询用户信息
                UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(getContext());
                nickNameTv.setText(userInfo.getNickName());

                String uri = MyApplication.IP + "/prod-api" + userInfo.getAvatar();
                Glide.with(getContext()).load(uri).placeholder(R.drawable.user_icon).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);

                logoTv.setText("退出登录");
                logoTv.setTextColor(Color.parseColor("#ca062c"));
                loginBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginOut();
                    }
                });
            }

            @Override
            public void onNone() {
                Glide.with(getContext()).load(R.drawable.user_icon).placeholder(R.drawable.user_icon).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);
                nickNameTv.setText("登录/注册\n请点击头像登录");

                logoTv.setText("登录");
                logoTv.setTextColor(Color.parseColor("#333333"));
                loginBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    /**
     * @Create 2021/9/14 14:53
     * @Role 菜单栏点击事件
     * @Param
     */
    private void setMenuClick(final int position) {
        if (position != 6) {
            CheckLoginUtils.isLogin(getContext(), false, new CheckLogin() {
                @Override
                public void onAlready() {
                    Intent intent;
                    switch (position) {
                        //个人设置
                        case 0:
                            intent = new Intent(getContext(), UserInfoActivity.class);
                            startActivity(intent);
                            break;
                        //我的钱包
                        case 1:
                            intent = new Intent(getContext(), BalanceTopUpActivity.class);
                            startActivity(intent);
                            break;
                        //我的积分
                        case 2:
                            break;
                        //我的订单
                        case 3:
                            intent = new Intent(getContext(), AllOrdersActivity.class);
                            startActivity(intent);
                            break;

                        //修改密码
                        case 4:
                            intent = new Intent(getContext(), ChangePasswordActivity.class);
                            startActivity(intent);
                            break;
                        //意见反馈
                        case 5:
                            intent = new Intent(getContext(), FeedBackActivity.class);
                            startActivity(intent);
                            break;

                    }
                }

                @Override
                public void onNone() {
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }
            });
            //网络设置
        } else {
            startActivity(new Intent(getContext(), NetworkSettingsActivity.class));
        }
    }

    /**
     * @Create 2021/10/12 10:16
     * @Role 退出登录
     * @Param
     */
    private void LoginOut() {
        final LoginOutConfirmDialog confirmDialog = new LoginOutConfirmDialog();
        confirmDialog.DialogCallBack(new LoginOutConfirmDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(int position) {
                switch (position) {
                    //退出登录
                    case 0:
                        confirmDialog.dismiss();
                        MyApplication.setIsLogin(getContext(), false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                UpdatingUserStatus();
                            }
                        }, 600);

                        break;
                    //返回桌面
                    case 1:
                        confirmDialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                startActivity(intent);
                            }
                        }, 300);

                        break;
                    case 2:
                        confirmDialog.dismiss();
                        break;
                }
            }
        });
        confirmDialog.show(getFragmentManager(), "dialog");
    }


}