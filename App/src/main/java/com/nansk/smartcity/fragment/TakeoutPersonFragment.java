package com.nansk.smartcity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.activity.takeout.TakeoutAddressManagerActivity;
import com.nansk.smartcity.activity.takeout.TakeoutCollectActivity;
import com.nansk.smartcity.activity.takeout.TakeoutMainActivity;
import com.nansk.smartcity.beans.UserInfoBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 17:26
 * @Description
 */

public class TakeoutPersonFragment extends Fragment{
    private View view;
    private LinearLayout userInfoBox;
    private ImageView avatarIv;
    private TextView nickNameTv;
    private LinearLayout tabMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_take_out_person, container, false);

        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdatingUserStatus();
    }

    private void initView() {
        userInfoBox = (LinearLayout) view.findViewById(R.id.userInfo_box);
        avatarIv = (ImageView) view.findViewById(R.id.avatar_iv);
        nickNameTv = (TextView) view.findViewById(R.id.nickName_tv);
        tabMenu = (LinearLayout) view.findViewById(R.id.tab_menu);

        for (int i=0;i<tabMenu.getChildCount();i++){
            final int finalI = i;
            tabMenu.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent;
                        switch (finalI) {
                            case 0:
                                intent = new Intent(getContext(), TakeoutAddressManagerActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(getContext(), TakeoutCollectActivity.class);
                                startActivity(intent);
                                break;
                            case 1:
                                TakeoutMainActivity.bottomTabLayout.getTabAt(1).select();
                                break;
                        }
                }
            });
        }
    }

    /**
     * @Create 2021/9/23 14:08
     * @Role 更新用户状态
     * @Param
     */
    private void UpdatingUserStatus() {
        if ( MyApplication.isLogin(getContext())) {
            UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(getContext());

            nickNameTv.setText(userInfo.getNickName());
            String uri = MyApplication.IP + "/prod-api" + userInfo.getAvatar();
            Glide.with(getContext()).load(uri).placeholder(R.drawable.user_icon).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);

        } else {
            Glide.with(getContext()).load(R.drawable.user_icon).placeholder(R.drawable.user_icon).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatarIv);
            nickNameTv.setText("登录/注册\n请点击头像登录");
        }
    }


}