package com.nansk.smartcity.activity.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.takeout.TakeoutSellerListAdapter;
import com.nansk.smartcity.adapter.takeout.TakeoutSellerSearchAdapter;
import com.nansk.smartcity.beans.takeout.TakeOutThemeBean;
import com.nansk.smartcity.beans.takeout.TakeoutSellerBean;
import com.nansk.smartcity.beans.takeout.TakeoutSellerSearchBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/21 23:06
 * @Description 店家列表页
 */

public class TakeoutSellerListActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private LinearLayout searchContainer;
    private SearchView searchView;
    private Button cancelBtn;
    private RecyclerView bodyContainer;

    private Gson gson;
    private TakeOutThemeBean.DataBean themeObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_seller_list);
        setToolBarBackground("#FFC107",0);
        setToolBarTitle("店家列表");
        initView();


    }


    private void initView() {
        searchContainer = (LinearLayout) findViewById(R.id.search_container);
        searchView = (SearchView) findViewById(R.id.search_view);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        searchView.setBackgroundResource(R.drawable.takeout_search_view_style);
        //搜索框焦点获取监听
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
        cancelBtn.setVisibility(View.GONE);
        //搜索框取消按钮点击事件
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索框清除内容和焦点
                searchView.setQuery("", false);
                searchView.clearFocus();
                WindowMangerUtils.closeKeyboard(TakeoutSellerListActivity.this, searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        bodyContainer.setLayoutManager(new LinearLayoutManager(TakeoutSellerListActivity.this));
        bodyContainer.addItemDecoration(new DividerItemDecoration(TakeoutSellerListActivity.this, DividerItemDecoration.VERTICAL));

        gson = new Gson();

        Intent intent = getIntent();
        themeObj = (TakeOutThemeBean.DataBean) intent.getSerializableExtra("themeObj");
        if (themeObj != null) {
            initTheme(themeObj.getId(), null);
        } else {
            String query = intent.getStringExtra("query");
            searchView.setQuery(query, true);
        }
    }

    /**
     * @Create 2021/9/21 23:52
     * @Role 获取店家列表
     * @Param
     */
    private void initTheme(int themeId, String sellerName) {
        String condition = null;
        if (themeId != 0) {
            condition = "?themeId=" + themeId;
        } else if (sellerName != null) {
            condition = "?name=" + sellerName;
        }
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_LIST, condition);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TakeoutSellerBean jsonObj = gson.fromJson(response.body().string(), TakeoutSellerBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<TakeoutSellerBean.RowsBean> rows = jsonObj.getRows();
                            if (rows.size() > 0) {
                                final TakeoutSellerListAdapter adapter = new TakeoutSellerListAdapter(TakeoutSellerListActivity.this, rows);
                                bodyContainer.setAdapter(adapter);
                            }
                        } else {
                            Toast.makeText(TakeoutSellerListActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    /**
     * @Create 2021/9/26 16:44
     * @Role 根据菜品搜索店家列表
     * @Param
     */

    public void searchSeller(String query) {
        if (!query.isEmpty()) {
            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_SEARCH, "?keyword=" + query);
            OkHttpUtil.doGet(url, "", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final TakeoutSellerSearchBean jsonObj = gson.fromJson(response.body().string(), TakeoutSellerSearchBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200) {
                                List<TakeoutSellerSearchBean.RowsBean> rows = jsonObj.getRows();
                                TakeoutSellerSearchAdapter adapter = new TakeoutSellerSearchAdapter(TakeoutSellerListActivity.this, rows);
                                bodyContainer.setAdapter(adapter);
                            }
                        }
                    });
                }
            });

        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchSeller(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}