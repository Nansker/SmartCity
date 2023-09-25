package com.nansk.smartcity.design.protection;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.ProtectionNearListAdapter;
import com.nansk.smartcity.adapter.design.ProtectionRecyclingRecordAdapter;
import com.nansk.smartcity.beans.protection.ProtectionNearListBean;
import com.nansk.smartcity.beans.protection.ProtectionRecyclingSubmitBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 14:28
 * @description 回收历史
 */

public class ProtectRecyclingListActivity extends BaseActivity {

    private RecyclerView bodyContainer;
    private TextView tipsTv;

    private String dataType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_protect_recycling_list);

        initView();
        fillData();
    }

    /**
     * @Create 2021/10/17 15:50
     * @Role
     * @Param
     */
    private void fillData() {
        if (dataType.equals("history")) {
            setToolBarBackground("#22bf76");
            setToolBarTitle("回收历史");
            String recycling = (String) SharedPreferencesUtils.getRecord(ProtectRecyclingListActivity.this, "protection_recycling", "");
            List<ProtectionRecyclingSubmitBean> json = MyApplication.gson.fromJson(recycling, new TypeToken<List<ProtectionRecyclingSubmitBean>>() {
            }.getType());

            if (json != null) {
                if (json.size() > 0) {
                    tipsTv.setVisibility(View.GONE);
                    bodyContainer.setAdapter(new ProtectionRecyclingRecordAdapter(ProtectRecyclingListActivity.this, json));
                } else {
                    tipsTv.setVisibility(View.VISIBLE);
                    tipsTv.setText("暂无记录");
                }
            }

        } else if (dataType.equals("near")) {
            setToolBarTitle("附近回收机");
            String data = FileReadUtils.getData(ProtectRecyclingListActivity.this, R.raw.protection_near);
            List<ProtectionNearListBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<ProtectionNearListBean>>() {
            }.getType());
            if (json.size()>0){
                tipsTv.setVisibility(View.GONE);
                ProtectionNearListAdapter adapter = new ProtectionNearListAdapter(ProtectRecyclingListActivity.this, json);
                adapter.setOnItemCallBack(new OnItemCallBack() {
                    @Override
                    public void OnItemCallBack(int position, Object obj) {
                        ProtectionNearListBean object = (ProtectionNearListBean) obj;
                        Intent intent = new Intent(ProtectRecyclingListActivity.this, ProtectionNearDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("obj",object);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                bodyContainer.setAdapter(adapter);
            }else {
                tipsTv.setText("附近暂无回收机！");
            }
        }
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        tipsTv = (TextView) findViewById(R.id.tips_tv);

        bodyContainer.setLayoutManager(new LinearLayoutManager(ProtectRecyclingListActivity.this));
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

        dataType = getIntent().getStringExtra("dataType");
    }
}