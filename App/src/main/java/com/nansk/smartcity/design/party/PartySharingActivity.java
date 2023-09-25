package com.nansk.smartcity.design.party;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.PartySharingListAdapter;
import com.nansk.smartcity.beans.DesignSharingBean;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 11:57
 * @description 随手拍
 */

public class PartySharingActivity extends BaseActivity {
    private int SHARING = 121;

    private ImageView userIconIv;
    private TextView shareTv;
    private RecyclerView bodyContainer;

    private PartySharingListAdapter sharingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_sharing);
        setToolBarTitle("随手拍");
        setToolBarBackground(getResources().getString(R.string.theme_party));
        initView();
        getSharingList();
    }

    /**
     * @Create 2021/10/24 12:49
     * @Role
     * @Param
     */
    private void getSharingList() {
        List<DesignSharingBean> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);

        for (int i = 0; i < 3; i++) {
            DesignSharingBean sharingBean = new DesignSharingBean();
            sharingBean.setTitle("测试标题" + (i + 1));
            sharingBean.setContent("测试内容");
            sharingBean.setLikeNum(0);
            sharingBean.setReadNum(0);
            sharingBean.setDate(format.format(date));
            sharingBean.setNickName("测试用户名称");
            list.add(sharingBean);
        }

        String record = (String) SharedPreferencesUtils.getRecord(PartySharingActivity.this, "party_sharing", "");

        if (!record.equals("")) {
            List<DesignSharingBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<DesignSharingBean>>() {
            }.getType());
            for (int i = 0; i < json.size(); i++) {
                DesignSharingBean designSharingBean = json.get(i);
                list.add(0,designSharingBean);
            }
        }
        sharingAdapter.setData(list);
    }

    private void initView() {
        userIconIv = (ImageView) findViewById(R.id.userIcon_iv);
        shareTv = (TextView) findViewById(R.id.share_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        bodyContainer.setLayoutManager(new LinearLayoutManager(PartySharingActivity.this));
        sharingAdapter = new PartySharingListAdapter(PartySharingActivity.this);
        bodyContainer.setAdapter(sharingAdapter);

        sharingAdapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {

            }
        });

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
            }
        });


        shareTv.setBackground(getDrawable("#ffffff", 20, 4, "#f2f2f2"));
        shareTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PartySharingActivity.this, PartySharingSendActivity.class), SHARING);
            }
        });
    }
}