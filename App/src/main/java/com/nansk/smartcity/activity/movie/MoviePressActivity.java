package com.nansk.smartcity.activity.movie;

import android.content.Intent;
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
import com.nansk.smartcity.adapter.movie.MoviePressListAdapter;
import com.nansk.smartcity.beans.movie.MoviePressCategoryBean;
import com.nansk.smartcity.beans.movie.MoviePressListBean;
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
 * @create 2021/10/14 17:55
 * @description
 */

public class MoviePressActivity extends BaseActivity {

    private TabLayout tabMenu;
    private RecyclerView bodyContainer;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_movie_press);
        setToolBarTitle("星闻");
        initView();
        initTabMenu();
        getPressList();
    }

    /**
     * @Create 2021/10/14 18:01
     * @Role
     * @Param
     */
    private void initTabMenu() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_PRESS_CATEGORY, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MoviePressCategoryBean json = MyApplication.gson.fromJson(response.body().string(), MoviePressCategoryBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            final List<MoviePressCategoryBean.DataBean> rows = json.getData();
                            tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });
                            for (int i = 0; i < rows.size(); i++) {
                                tabMenu.addTab(tabMenu.newTab().setText(rows.get(i).getName()));
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/14 18:37
     * @Role 获取新闻列表
     * @Param
     */
    private void getPressList() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_PRESS, "press/list");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MoviePressListBean json = MyApplication.gson.fromJson(response.body().string(), MoviePressListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            if (json.getTotal() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                List<MoviePressListBean.RowsBean> rows = json.getRows();
                                MoviePressListAdapter adapter = new MoviePressListAdapter(MoviePressActivity.this, rows);
                                bodyContainer.setAdapter(adapter);

                                adapter.setOnItemCallBack(new MoviePressListAdapter.OnItemCallBack() {
                                    @Override
                                    public void OnItemCallBack(int position, MoviePressListBean.RowsBean obj) {
                                        Intent intent = new Intent(MoviePressActivity.this, MoviePressDetailsActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("pressId", obj.getId());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                tipsTv.setText("暂无新闻内容");
                            }

                        }
                    }
                });
            }
        });
    }


    private void initView() {
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        tipsTv = (TextView) findViewById(R.id.tips_tv);

        bodyContainer.setLayoutManager(new LinearLayoutManager(MoviePressActivity.this));
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