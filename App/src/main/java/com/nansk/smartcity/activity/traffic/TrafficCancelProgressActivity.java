package com.nansk.smartcity.activity.traffic;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.traffic.TrafficCancelProgressListAdapter;
import com.nansk.smartcity.beans.traffic.TrafficAppleListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 09:52
 * @description 违章撤销进度页面
 */

public class TrafficCancelProgressActivity extends BaseActivity {

    private TabLayout tabMenu;
    private TextView tipsTv;
    private RecyclerView bodyContainer;

    private String[] tabNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_cancel_progress);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("撤销申请进度");
        initView();
        initTabMenu();
    }

    /**
     * @Create 2021/10/8 11:39
     * @Role 未处理的违章信息列表
     * @Param
     */
    private void fillData() {
            String state = tabNames[tabMenu.getSelectedTabPosition()];
            String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "apply/list/?status="+state);
            OkHttpUtil.doGet(url, MyApplication.getToken(TrafficCancelProgressActivity.this), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final TrafficAppleListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TrafficAppleListBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200) {
                                List<TrafficAppleListBean.RowsBean> rows = jsonObj.getRows();
                                TrafficCancelProgressListAdapter adapter = new TrafficCancelProgressListAdapter(TrafficCancelProgressActivity.this, rows);
                                bodyContainer.setAdapter(adapter);
                                if (jsonObj.getTotal() > 0) {
                                    tipsTv.setVisibility(View.GONE);
                                } else {
                                    tipsTv.setText("暂无记录");
                                }
                            } else {
                                tipsTv.setText(jsonObj.getMsg());
                            }
                        }
                    });
                }
            });
        }


    private void initTabMenu() {
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fillData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i =0;i <tabNames.length;i++){
            tabMenu.addTab(tabMenu.newTab().setText(tabNames[i]));
        }

    }

    private void initView() {

        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        tabNames = new String[]{"申请中","驳回","通过"};

        bodyContainer.setLayoutManager(new LinearLayoutManager(this));
        bodyContainer.setLayoutManager(new LinearLayoutManager(TrafficCancelProgressActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
            }
        });
    }
}