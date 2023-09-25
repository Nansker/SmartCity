package com.nansk.smartcity.activity.takeout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nansk.smartcity.R;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 14:18
 * @Description 商家简介
 */

public class TakeoutSellerIntroduceActivity extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_takeout_seller_introduce, container, false);

        return view;
    }
}