package com.nansk.smartcity.design.support;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.SupportMyHelpAdapter;
import com.nansk.smartcity.adapter.design.SupportSeekHelpAdapter;
import com.nansk.smartcity.beans.support.SupportMyHelpBean;
import com.nansk.smartcity.beans.support.SupportSeekHelpBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 19:03
 * @description
 */

public class SupportHelpActivity extends BaseActivity {
    private String dataType;
    private RecyclerView bodyContainer;

    private SupportSeekHelpAdapter seekHelpAdapter;
    private SupportMyHelpAdapter myHelpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_help);
        initView();
        fillData();
    }

    /**
     * @Create 2021/10/20 19:12
     * @Role
     * @Param
     */
    private void fillData() {
        dataType = getIntent().getStringExtra("dataType");
        switch (dataType) {
            case "seekHelp":
                setToolBarTitle("收到的求助");
                getSeekHelpList();
                break;
            case "help":
                setToolBarTitle("我帮扶的");
                getHelpList();
                break;
        }
    }

    /**
     * @Create 2021/10/20 20:08
     * @Role 帮助列表
     * @Param
     */
    private void getHelpList() {
        SupportMyHelpBean myHelp = new SupportMyHelpBean();
        myHelp.setName("自然幸福村");
        myHelp.setAddress("江西省南昌市高新区紫阳大道318号");
        myHelp.setDate("2021-10-20");
        myHelp.setIntroduction("制造强国，严学以正");

        ArrayList<SupportMyHelpBean> data = new ArrayList<>();
        data.add(myHelp);

        bodyContainer.setAdapter(myHelpAdapter);
        String record = (String) SharedPreferencesUtils.getRecord(SupportHelpActivity.this, "support_myhelp", "");
        if (!record.equals("")) {
            List<SupportMyHelpBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<SupportMyHelpBean>>() {
            }.getType());

            for (int i = 0; i < json.size(); i++) {
                data.add(0, json.get(i));
            }
            myHelpAdapter.setData(data);
        } else {
            myHelpAdapter.setData(data);
        }
    }

    /**
     * @Create 2021/10/20 19:13
     * @Role 求助列表
     * @Param
     */
    private void getSeekHelpList() {
        String data = FileReadUtils.getData(SupportHelpActivity.this, R.raw.supoort_seek_help);
        List<SupportSeekHelpBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<SupportSeekHelpBean>>() {
        }.getType());
        bodyContainer.setAdapter(seekHelpAdapter);
        seekHelpAdapter.setData(json);
    }

    /**
     * @Create 2021/10/20 19:08
     * @Role
     * @Param
     */
    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        seekHelpAdapter = new SupportSeekHelpAdapter(SupportHelpActivity.this);
        myHelpAdapter = new SupportMyHelpAdapter(SupportHelpActivity.this);

        bodyContainer.setLayoutManager(new LinearLayoutManager(SupportHelpActivity.this));
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
    }


}