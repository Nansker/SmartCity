package com.nansk.smartcity.activity.metro;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nansk.smartcity.model.CheckLogin;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.UserInfoBean;
import com.nansk.smartcity.model.impl.CheckLoginUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.UserInfoUtils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 11:36
 * @description 城市地铁-个人中心
 */


public class MetroPersonFragment extends Fragment {
    private View view;
    private LinearLayout userInfoBox;
    private ImageView avatarIv;
    private TextView nickNameTv;
    private LinearLayout tabContainer;
    private LinearLayout tabMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_metro_person, container, false);
        initView();
        getUserInfo();
        return view;
    }

    /**
     * @Create 2021/10/11 21:32
     * @Role 获取已登录用户信息
     * @Param
     */
    private void getUserInfo() {
        CheckLoginUtils.isLogin(getContext(), false, new CheckLogin() {
            @Override
            public void onAlready() {
                UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(getContext());
                Glide.with(getContext()).load(MyApplication.getIP(getContext())+"/prod-api"+userInfo.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);
                nickNameTv.setText(userInfo.getNickName());
            }

            @Override
            public void onNone() {

            }
        });

    }

    /**
     * @Create 2021/10/11 16:17
     * @Role 设置tab点击事件
     * @Param
     */
    private void setTabAction() {
//        横向tab
        for (int i = 0; i < tabContainer.getChildCount(); i++) {
            final int finalI = i;
            tabContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    Bundle bundle;
                    switch (finalI) {
                        //失物招领
                        case 0:
                            intent = new Intent(getContext(), MetroNoticeActivity.class);
                            bundle = new Bundle();
                            bundle.putInt("dataType", 4);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        //乘车须知
                        case 1:
                            intent = new Intent(getContext(), MetroNoticeActivity.class);
                            bundle = new Bundle();
                            bundle.putInt("dataType", 2);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        //地铁热线
                        case 2:
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 0);
                            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01096165"));
                            startActivity(intent);
                            break;
                        //退出登录
                        case 3:
                            getActivity().finish();
                            break;
                    }
                }
            });
        }

        //纵向tab
        for (int i = 0; i < tabMenu.getChildCount(); i++) {
            final int finalI = i;
            tabMenu.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    Bundle bundle;
                    switch (finalI) {
                        case 0:

                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            intent = new Intent(getContext(), MetroAboutActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }

    }

    private void initView() {
        userInfoBox = (LinearLayout) view.findViewById(R.id.userInfo_box);
        avatarIv = (ImageView) view.findViewById(R.id.avatar_iv);
        nickNameTv = (TextView) view.findViewById(R.id.nickName_tv);
        tabContainer = (LinearLayout) view.findViewById(R.id.tab_container);
        tabMenu = (LinearLayout) view.findViewById(R.id.tab_menu);
        setTabAction();

    }


}