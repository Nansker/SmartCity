package com.nansk.smartcity.design.support;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.SupportPressListAdapter;
import com.nansk.smartcity.beans.support.SupportPressBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 14:57
 * @description 扶贫新闻
 */

public class SupportPressActivity extends BaseActivity {


    private RecyclerView bodyContainer;
    private SupportPressListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_press);
        setToolBarTitle("扶贫新闻");
        initView();
        getPressList();
    }

    /**
     * @Create 2021/10/20 15:10
     * @Role
     * @Param
     */
    private void getPressList() {
        String data = FileReadUtils.getData(SupportPressActivity.this, R.raw.support_press);
        List<SupportPressBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<SupportPressBean>>() {
        }.getType());
        adapter.setData(json);
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        bodyContainer.setLayoutManager(new LinearLayoutManager(SupportPressActivity.this));
        adapter = new SupportPressListAdapter(SupportPressActivity.this);
        bodyContainer.setAdapter(adapter);
        bodyContainer.addItemDecoration(new DividerItemDecoration(SupportPressActivity.this,DividerItemDecoration.VERTICAL));

        adapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                SupportPressBean pressObj = (SupportPressBean) obj;
                Intent intent = new Intent(SupportPressActivity.this, SupportPressDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pressObj",pressObj);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}