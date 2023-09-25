package com.nansk.smartcity.activity.house;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.house.HouseListAdapter;
import com.nansk.smartcity.beans.home.HomeBannerJsonObj;
import com.nansk.smartcity.beans.home.HomeBannerJsonRows;
import com.nansk.smartcity.beans.house.HouseListBean;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;
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
 * @Create 2021/09/26 20:22
 * @Description 找房子主页
 */

public class HouseActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private LinearLayout searchContainer;
    private SearchView searchView;
    private Button cancelBtn;
    private Banner banner;
    private TabLayout tabMenu;
    private RecyclerView bodyContainer;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_house);
        setToolBarTitle("找房子");
        setToolBarBackground("#37c2bb");
        initView();
        initTabMenu();
        initBanner();
    }

    private void initView() {

        searchContainer = (LinearLayout) findViewById(R.id.search_container);
        searchView = (SearchView) findViewById(R.id.search_view);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        banner = (Banner) findViewById(R.id.banner);
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);


        cancelBtn.setVisibility(View.GONE);
        //        搜索框焦点获取监听
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cancelBtn.setVisibility(View.VISIBLE);
                } else {
                    cancelBtn.setVisibility(View.GONE);
                }
            }
        });

        searchView.setOnQueryTextListener(this);

        //搜索框取消按钮点击事件
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索框清除内容和焦点
                searchView.setQuery("", false);
                searchView.clearFocus();
                WindowMangerUtils.closeKeyboard(HouseActivity.this, searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        gson = new Gson();
        bodyContainer.setLayoutManager(new LinearLayoutManager(HouseActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
                outRect.bottom = 15;
                outRect.left = 40;
                outRect.right = 40;
            }
        });

        BannerSetUtils.setBannerStyle(HouseActivity.this, banner);
    }

    /**
     * @Create 2021/9/26 20:37
     * @Role 初始化分类栏
     * @Param
     */
    private void initTabMenu() {
        final String[] tabNames = getResources().getStringArray(R.array.house_menu_names);
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fillData(0, tabNames[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < tabNames.length; i++) {
            tabMenu.addTab(tabMenu.newTab().setText(tabNames[i]));
        }

    }

    /**
     * @Create 2021/9/26 21:11
     * @Role 获取房源列表
     * @Param action要执行的操作 0=获取房源列表 1=搜索
     * @Param condition 条件
     */
    private void fillData(int action, String condition) {
        String url = null;
        if (action == 0) {
            url = ConnectInfo.getUrl(ConnectInfo.HOUSE_HOUSING, "list?houseType=" + condition);
        } else if (action == 1) {
            url = ConnectInfo.getUrl(ConnectInfo.HOUSE_HOUSING, "list?sourceName=" + condition);
        }
        if (url != null) {
            OkHttpUtil.doGet(url, "", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    HouseListBean jsonObj = gson.fromJson(response.body().string(), HouseListBean.class);
                    if (jsonObj.getCode() == 200) {
                        final List<HouseListBean.RowsBean> rows = jsonObj.getRows();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (rows.size() > 0) {
                                    HouseListAdapter adapter = new HouseListAdapter(HouseActivity.this, rows);
                                    bodyContainer.setAdapter(adapter);
                                }
                            }
                        });

                    }
                }
            });
        } else {
            Toast.makeText(this, "未获取到相关检索条件", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Create 2021/9/27 11:16
     * @Role 获取幻灯片数据
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOME_BANNER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Gson解析
                HomeBannerJsonObj jsonObj = gson.fromJson(response.body().string(), HomeBannerJsonObj.class);
                final List<HomeBannerJsonRows> rows = jsonObj.getRows();
                final List<String> pagers = new ArrayList<>();
                //去掉开屏广告页
                for (int i = 0; i < rows.size(); i++) {
                    if (!rows.get(i).getAdvTitle().equals("开屏广告")) {
                        pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(pagers);
                        banner.start();
                    }
                });

            }

        });

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        WindowMangerUtils.closeKeyboard(HouseActivity.this, searchView);
        fillData(1, query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}