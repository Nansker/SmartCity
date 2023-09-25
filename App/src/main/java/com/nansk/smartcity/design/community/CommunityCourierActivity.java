package com.nansk.smartcity.design.community;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.CommunityExpressAdapter;
import com.nansk.smartcity.beans.community.CommunityExpressBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 19:47
 * @description 快件管理
 */

public class CommunityCourierActivity extends BaseActivity {

    private TextView siteNameTv;
    private TextView addressTv;
    private TextView openTimeTv;
    private RecyclerView recordContainer;
    private LinearLayout getBox;

    private CommunityExpressAdapter adapter;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_commounity_courier);
        setToolBarTitle("快件管理");
        initView();
        getHistoryList();
    }

    /**
     * @Create 2021/10/18 20:57
     * @Role
     * @Param
     */
    private void getHistoryList() {
        String data = FileReadUtils.getData(CommunityCourierActivity.this, R.raw.community_express);
        List<CommunityExpressBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<CommunityExpressBean>>() {
        }.getType());
        adapter.setData(json);
        tipsTv.setVisibility(View.GONE);
    }

    public void initView() {
        siteNameTv = (TextView) findViewById(R.id.siteName_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);
        openTimeTv = (TextView) findViewById(R.id.openTime_tv);
        recordContainer = (RecyclerView) findViewById(R.id.record_container);
        getBox = (LinearLayout) findViewById(R.id.get_box);

        recordContainer.setLayoutManager(new LinearLayoutManager(CommunityCourierActivity.this));
        adapter = new CommunityExpressAdapter(CommunityCourierActivity.this);
        recordContainer.setAdapter(adapter);
        tipsTv = (TextView) findViewById(R.id.tips_tv);

        getBox.setBackground(getDrawable("#ffffff",100,2,"#5badf6"));
    }
}