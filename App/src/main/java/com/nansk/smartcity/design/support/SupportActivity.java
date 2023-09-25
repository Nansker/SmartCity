package com.nansk.smartcity.design.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.SupportPressListAdapter;
import com.nansk.smartcity.beans.support.SupportPressBean;
import com.nansk.smartcity.design.DesignResources;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.youth.banner.Banner;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 13:38
 * @description 精准扶贫
 */

public class SupportActivity extends BaseActivity {

    private Banner banner;
    private LinearLayout menuContainer;
    private RecyclerView pressContainer;
    private SupportPressListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support);
        setToolBarTitle("精准扶贫");
        setToolBarBackground(getResources().getString(R.string.theme_party));
        initView();
        getPressList();
    }

    /**
     * @Create 2021/10/20 15:10
     * @Role
     * @Param
     */
    private void getPressList() {
        String data = FileReadUtils.getData(SupportActivity.this, R.raw.support_press);
        List<SupportPressBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<SupportPressBean>>() {
        }.getType());
        adapter.setData(json);
    }

    private void initView() {

        banner = (Banner) findViewById(R.id.banner);
        menuContainer = (LinearLayout) findViewById(R.id.menu_container);
        pressContainer = (RecyclerView) findViewById(R.id.press_container);

        BannerSetUtils.setBannerStyle(SupportActivity.this, banner);
        banner.setImages(DesignResources.getSupportBannerPagers());
        banner.start();

        for (int i = 0; i < menuContainer.getChildCount(); i++) {
            final int finalI = i;
            menuContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI){
                        case 0:
                            startActivity(new Intent(SupportActivity.this,SupportPressActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(SupportActivity.this,SupportCaseActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(SupportActivity.this,SupportVillageActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(SupportActivity.this,SupportWorkbenchActivity.class));
                            break;
                    }
                }
            });
        }

        adapter = new SupportPressListAdapter(SupportActivity.this);
        pressContainer.setAdapter(adapter);
        pressContainer.setLayoutManager(new LinearLayoutManager(SupportActivity.this));
        pressContainer.addItemDecoration(new DividerItemDecoration(SupportActivity.this,DividerItemDecoration.VERTICAL));

        adapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                SupportPressBean pressObj = (SupportPressBean) obj;
                Intent intent = new Intent(SupportActivity.this, SupportPressDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pressObj",pressObj);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}