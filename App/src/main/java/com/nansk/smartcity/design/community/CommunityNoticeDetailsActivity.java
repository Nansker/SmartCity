package com.nansk.smartcity.design.community;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.community.CommunityNoticeBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 21:42
 * @description 通知详情
 */

public class CommunityNoticeDetailsActivity extends BaseActivity {

    private ImageView imageIv;
    private TextView contentTv;

    private int noticeId;
    private TextView titleTv;
    private TextView timeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_commounity_notice_details);
        setToolBarTitle("通知详情");
        initView();
        fillData();
    }

    /**
     * @Create 2021/10/17 21:48
     * @Role
     * @Param
     */
    private void fillData() {
        String data = FileReadUtils.getData(CommunityNoticeDetailsActivity.this, R.raw.community_notice);
        final List<CommunityNoticeBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<CommunityNoticeBean>>() {
        }.getType());
        for (int i = 0; i < json.size(); i++) {
            if (json.get(i).getId() == noticeId) {
                contentTv.setText(json.get(i).getContent());
                titleTv.setText(json.get(i).getTitle());
                timeTv.setText(json.get(i).getTime());
                imageIv.setImageResource(getNoticeImage(noticeId));
            }
        }
    }

    /**
     * @Create 2021/10/17 21:53
     * @Role
     * @Param
     */
    private int getNoticeImage(int noticeId) {
        int id;
        switch (noticeId) {
            case 1:
                id = R.drawable.community_notice_img1;
                break;
            case 2:
                id = R.drawable.community_notice_img2;
                break;
            case 3:
                id = R.drawable.community_notice_img3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + noticeId);
        }
        return id;
    }

    private void initView() {
        imageIv = (ImageView) findViewById(R.id.image_iv);
        contentTv = (TextView) findViewById(R.id.content_tv);
        noticeId = getIntent().getIntExtra("noticeId", 0);
        titleTv = (TextView) findViewById(R.id.title_tv);
        timeTv = (TextView) findViewById(R.id.time_tv);
    }
}