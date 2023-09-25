package com.nansk.smartcity.design.support;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.SupportVillageAdapter;
import com.nansk.smartcity.beans.support.SupportVillageBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 16:40
 * @description 村情村貌
 */

public class SupportVillageActivity extends BaseActivity {


    private RecyclerView bodyContainer;
    private SupportVillageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_village);
        setToolBarTitle("村情村貌");
        initView();
        getVillageList();
    }

    /**
     * @Create 2021/10/20 17:15
     * @Role
     * @Param
     */
    private void getVillageList() {
        String data = FileReadUtils.getData(SupportVillageActivity.this, R.raw.support_village);
        List<SupportVillageBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<SupportVillageBean>>() {
        }.getType());
        adapter.setData(json);
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        bodyContainer.setLayoutManager(new LinearLayoutManager(SupportVillageActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 30;
                outRect.right = 30;
                outRect.top = 15;
                outRect.bottom = 15;
            }
        });

        adapter = new SupportVillageAdapter(SupportVillageActivity.this);
        bodyContainer.setAdapter(adapter);

        adapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                SupportVillageBean villageObj = (SupportVillageBean) obj;
                Intent intent = new Intent(SupportVillageActivity.this, SupportVillageDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("villageObj",villageObj);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}