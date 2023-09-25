package com.nansk.smartcity.activity.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.activity.AcitvityListAdapter;
import com.nansk.smartcity.beans.activity.ActivityBannerBean;
import com.nansk.smartcity.beans.activity.ActivityCetegoryBean;
import com.nansk.smartcity.beans.activity.ActivityListBean;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 14:18
 * @Description 活动主页
 */

public class HuodongActivity extends BaseActivity {


    private Banner banner;
    private TabLayout tabMenu;
    private RecyclerView bodyContainer;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_huodong);
        setToolBarTitle("活动");
        setToolBarBackground("#007aff");

        initView();
        initBanner();
        initTabMenu();
    }


    private void initView() {

        banner = (Banner) findViewById(R.id.banner);
        tabMenu = findViewById(R.id.tab_menu);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        gson = new Gson();
        BannerSetUtils.setBannerStyle(HuodongActivity.this, banner);

        bodyContainer.setLayoutManager(new LinearLayoutManager(HuodongActivity.this));
        bodyContainer.addItemDecoration(new DividerItemDecoration(HuodongActivity.this,DividerItemDecoration.VERTICAL));
    }

    /**
     * @Create 2021/9/27 14:33
     * @Role 初始化banner
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_BANNER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ActivityBannerBean jsonBean = gson.fromJson(response.body().string(), ActivityBannerBean.class);
                if (jsonBean.getCode() == 200) {
                    List<ActivityBannerBean.RowsBean> rows = jsonBean.getRows();
                    final ArrayList<String> images = new ArrayList<>();
                    for (int i = 0; i < rows.size(); i++) {
                        images.add(MyApplication.IP + rows.get(i).getAdvImg());
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.setImages(images);
                            banner.start();
                        }
                    });

                }
            }
        });
    }

    /**
     * @Create 2021/9/27 15:16
     * @Role
     * @Param
     */
    private void initTabMenu() {
        String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_CATEGORY, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ActivityCetegoryBean jsonObj = gson.fromJson(response.body().string(), ActivityCetegoryBean.class);
                if (jsonObj.getCode() == 200){
                    final List<ActivityCetegoryBean.DataBean> data = jsonObj.getData();
                    if (data.size()>0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTabMenu(data);
                            }
                        });
                    }

                }
            }
        });
    }

    /**
     * @Create 2021/9/27 16:03
     * @Role 设置tab内容，点击事件
     * @Param
     * @param data
     */
    private void setTabMenu(final List<ActivityCetegoryBean.DataBean> data) {
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fillData(data.get(tab.getPosition()).getId());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0;i<data.size();i++){
            tabMenu.addTab(tabMenu.newTab().setText(data.get(i).getName()));
        }

    }

    /**
     * @Create 2021/9/27 16:24
     * @Role 获取列表数据
     * @Param
     * @param id
     */
    private void fillData(int id) {
        String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_LIST, "?categoryId=" + id);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ActivityListBean jsonObj = gson.fromJson(response.body().string(), ActivityListBean.class);
                if (jsonObj.getCode() == 200){
                    final List<ActivityListBean.RowsBean> data = jsonObj.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AcitvityListAdapter adapter = new AcitvityListAdapter(HuodongActivity.this, data);
                            bodyContainer.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }


}