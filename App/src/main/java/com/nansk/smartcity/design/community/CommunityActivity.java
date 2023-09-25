package com.nansk.smartcity.design.community;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.community.CommunityNoticeBean;
import com.nansk.smartcity.design.DesignResources;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.youth.banner.Banner;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 19:28
 * @description 智慧社区
 */

public class CommunityActivity extends BaseActivity {

    private Banner banner;
    private LinearLayout noticeBox;
    private ViewFlipper noticeVf;
    private GridLayout menuContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_community);
        setToolBarTitle("智慧社区");
        setToolBarBackground("#FF9800");
        initView();
        setNotice();
    }

    /**
     * @Create 2021/10/17 21:24
     * @Role
     * @Param
     */
    private void setNotice() {
        String data = FileReadUtils.getData(CommunityActivity.this, R.raw.community_notice);
        final List<CommunityNoticeBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<CommunityNoticeBean>>() {
        }.getType());

        for (int i =0;i<json.size();i++){
            TextView noticeTv = new TextView(CommunityActivity.this);
            noticeTv.setText(json.get(i).getTitle()+"\u3000"+json.get(i).getTime());
            noticeTv.setTextColor(Color.parseColor("#333333"));
            noticeTv.setTextSize(14);
            noticeVf.addView(noticeTv);
            final int finalI = i;
            noticeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CommunityActivity.this, CommunityNoticeDetailsActivity.class);
                    intent.putExtra("noticeId",json.get(finalI).getId());
                    startActivity(intent);
                }
            });
        }

    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        noticeBox = (LinearLayout) findViewById(R.id.notice_box);
        noticeVf = (ViewFlipper) findViewById(R.id.notice_vf);
        menuContainer = (GridLayout) findViewById(R.id.menu_container);

        BannerSetUtils.setBannerStyle(CommunityActivity.this,banner);
        banner.setImages(DesignResources.getCommunityBannerPagers());
        banner.start();

        noticeVf.setAutoStart(true);
        noticeVf.setFlipInterval(4000);
        noticeVf.setInAnimation(CommunityActivity.this,R.anim.wisdom_ontice_in);
        noticeVf.setOutAnimation(CommunityActivity.this,R.anim.wisdom_ontice_in);

        String[] colors = new String[]{"#5badf6", "#f19f77", "#b07eef", "#7dd193", "#5fc3e7"};
        for (int i = 0; i < menuContainer.getChildCount(); i++) {
            menuContainer.getChildAt(i).setBackground(getDrawable(colors[i], 20));
            final int finalI = i;
            menuContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        //友邻社交
                        case 0:
                            startActivity(new Intent(CommunityActivity.this,CommunitySocialActivity.class));
                            break;
                        //物业服务
                        case 1:
                            startActivity(new Intent(CommunityActivity.this,CommunityPropertyActivity.class));
                            break;
                        //配送服务
                        case 2:
                            startActivity(new Intent(CommunityActivity.this,CommunityCourierActivity.class));
                            break;
                        //车辆管理
                        case 3:
                            startActivity(new Intent(CommunityActivity.this,CommunityCarManageActivity.class));
                            break;
                    }
                }
            });
        }
    }
}