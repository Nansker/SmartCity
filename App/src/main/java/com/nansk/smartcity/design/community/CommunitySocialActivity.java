package com.nansk.smartcity.design.community;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.CommunityTiebaListAdapter;
import com.nansk.smartcity.beans.community.CommunityTiebaBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 20:50
 * @description 友邻社交
 */

public class CommunitySocialActivity extends BaseActivity {
    private int SHARE = 121;
    private RecyclerView bodyContainer;
    private ImageView userIconIv;
    private TextView shareTv;
    private CommunityTiebaListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_community_social);
        setToolBarTitle("友领社交");
        setToolBarBackground("#FF9800");
        initView();
        fillData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHARE) {
            if (data != null) {
                CommunityTiebaBean shareObj = (CommunityTiebaBean) data.getSerializableExtra("shareObj");
                List<CommunityTiebaBean> adapterData = adapter.getData();
                adapterData.add(0, shareObj);
                adapter.setData(adapterData);
                showToast("发表成功！");
                addSocial(shareObj);
            }
        }
    }

    /**
     * @Create 2021/10/18 14:02
     * @Role 向sp添加一条动态
     * @Param
     */
    private void addSocial(CommunityTiebaBean shareObj) {
        String record = (String) SharedPreferencesUtils.getRecord(CommunitySocialActivity.this, "community_share", "");
        List<CommunityTiebaBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<CommunityTiebaBean>>() {
        }.getType());

        if (json != null) {
            if (json.size() > 0) {
                json.add(shareObj);
                SharedPreferencesUtils.addRecord(CommunitySocialActivity.this,"community_share",MyApplication.gson.toJson(json));
            }else {
                ArrayList<CommunityTiebaBean> tiebaBeans = new ArrayList<>();
                tiebaBeans.add(0,shareObj);
                SharedPreferencesUtils.addRecord(CommunitySocialActivity.this,"community_share",MyApplication.gson.toJson(tiebaBeans));
            }
        } else {
            ArrayList<CommunityTiebaBean> tiebaBeans = new ArrayList<>();
            tiebaBeans.add(0,shareObj);
            SharedPreferencesUtils.addRecord(CommunitySocialActivity.this,"community_share",MyApplication.gson.toJson(tiebaBeans));
        }
    }

    /**
     * @Create 2021/10/18 9:20
     * @Role
     * @Param
     */
    private void fillData() {
        String data = FileReadUtils.getData(CommunitySocialActivity.this, R.raw.community_tieba);
        List<CommunityTiebaBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<CommunityTiebaBean>>() {
        }.getType());

        String shares = (String) SharedPreferencesUtils.getRecord(CommunitySocialActivity.this, "community_share", "");
        if (!shares.equals("")){
            List<CommunityTiebaBean> shareList = MyApplication.gson.fromJson(shares, new TypeToken<List<CommunityTiebaBean>>() {
            }.getType());
            if (shareList.size()>0){
                for (int i =0;i<shareList.size();i++){
                    json.add(i,shareList.get(i));
                }
            }
        }

        adapter.setData(json);
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        userIconIv = (ImageView) findViewById(R.id.userIcon_iv);
        shareTv = (TextView) findViewById(R.id.share_tv);
        shareTv.setBackground(getDrawable("#ffffff", 20, 4, "#f2f2f2"));
        shareTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CommunitySocialActivity.this, CommunitySocialShareActivity.class), SHARE);
            }
        });

        bodyContainer.setLayoutManager(new LinearLayoutManager(CommunitySocialActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 10;
                outRect.top = 10;
                outRect.left = 30;
                outRect.right = 30;
            }
        });

        adapter = new CommunityTiebaListAdapter(CommunitySocialActivity.this);
        bodyContainer.setAdapter(adapter);
        adapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                CommunityTiebaBean object = (CommunityTiebaBean) obj;
                Intent intent = new Intent(CommunitySocialActivity.this, CommunitySocialDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj",object);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}