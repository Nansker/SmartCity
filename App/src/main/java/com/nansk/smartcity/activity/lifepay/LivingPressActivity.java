package com.nansk.smartcity.activity.lifepay;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.lifepay.LivingPressListAdapter;
import com.nansk.smartcity.beans.lifepay.LivingPressCategoryBean;
import com.nansk.smartcity.beans.lifepay.LivingPressListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 19:08
 * @Description 生活资讯
 */

public class LivingPressActivity extends BaseActivity {

    private TabLayout tabMenu;
    private TextView tipsTv;
    private RecyclerView bodyContainer;

    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_living_press);
        setToolBarTitle("生活资讯");
        initView();
        initTabMenu();
    }

    private void initView() {

        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        gson = new Gson();
        bodyContainer.setLayoutManager(new LinearLayoutManager(LivingPressActivity.this));
        bodyContainer.addItemDecoration(new DividerItemDecoration(LivingPressActivity.this,DividerItemDecoration.VERTICAL));
    }

    private void initTabMenu() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIVING_PRESS, "category/list");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LivingPressCategoryBean jsonObj = gson.fromJson(response.body().string(), LivingPressCategoryBean.class);
                if (jsonObj.getCode() == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final List<LivingPressCategoryBean.DataBean> data = jsonObj.getData();

                            tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    initPressList(data.get(tab.getPosition()).getId());
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });

                            for (int i =0;i<data.size();i++){
                                tabMenu.addTab(tabMenu.newTab().setText(data.get(i).getName()));
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * @Create 2021/9/28 14:23
     * @Role 获取资讯列表
     * @Param
     */
    private void initPressList(int id) {
        String url = ConnectInfo.getUrl(ConnectInfo.LIVING_PRESS, "press/list?type="+id);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LivingPressListBean jsonObj = gson.fromJson(response.body().string(), LivingPressListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<LivingPressListBean.RowsBean> rows = jsonObj.getRows();
                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                LivingPressListAdapter adapter = new LivingPressListAdapter(LivingPressActivity.this, rows);
                                bodyContainer.setAdapter(adapter);
                            }
                        }
                    }
                });

            }
        });
    }

}