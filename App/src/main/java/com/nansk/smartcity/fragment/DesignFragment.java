package com.nansk.smartcity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nansk.smartcity.R;
import com.nansk.smartcity.design.community.CommunityActivity;
import com.nansk.smartcity.design.party.PartyActivity;
import com.nansk.smartcity.design.pension.PensionActivity;
import com.nansk.smartcity.design.protection.ProtectionMainActivity;
import com.nansk.smartcity.design.support.SupportActivity;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 08:33
 * @description  自主设计
 */

public class DesignFragment extends Fragment {
    private View view;
    private GridLayout tabContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_design, container, false);
        initView();
        return view;
    }

    private void initView() {
        tabContainer = (GridLayout) view.findViewById(R.id.tab_container);
        String[] colors = new String[]{"#5badf6", "#dd5952","#f19f77", "#b07eef", "#7dd193"};
        for (int i = 0; i < tabContainer.getChildCount(); i++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(20);
            gradientDrawable.setColor(Color.parseColor(colors[i]));
            tabContainer.getChildAt(i).setBackground(gradientDrawable);
            final int finalI = i;
            tabContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        //智慧党建
                        case 0:
                            startActivity(new Intent(getContext(), PartyActivity.class));
                            break;
                        //精准扶贫
                        case 1:
                            startActivity(new Intent(getContext(), SupportActivity.class));
                            break;
                        //智慧社区
                        case 2:
                            startActivity(new Intent(getContext(), CommunityActivity.class));
                            break;
                        //智慧养老
                        case 3:
                            startActivity(new Intent(getContext(), PensionActivity.class));
                            break;
                        //智慧环保
                        case 4:
                            startActivity(new Intent(getContext(), ProtectionMainActivity.class));
                            break;
                    }
                }
            });
        }
    }
}