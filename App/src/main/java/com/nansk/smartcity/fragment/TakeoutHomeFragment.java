package com.nansk.smartcity.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.takeout.TakeoutSellerDetailsActivity;
import com.nansk.smartcity.activity.takeout.TakeoutSellerListActivity;
import com.nansk.smartcity.adapter.takeout.TakeoutRecommSellerAdapter;
import com.nansk.smartcity.adapter.takeout.TakeoutSellerListAdapter;
import com.nansk.smartcity.adapter.takeout.TakeoutThemeAdapter;
import com.nansk.smartcity.beans.takeout.TakeOutRotationBean;
import com.nansk.smartcity.beans.takeout.TakeOutThemeBean;
import com.nansk.smartcity.beans.takeout.TakeoutSellerBean;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 17:23
 * @Description 外卖订餐首页
 */

public class TakeoutHomeFragment extends Fragment implements SearchView.OnQueryTextListener {
    private View view;
    private SearchView searchView;
    private Button cancelBtn;
    private Banner banner;

    private Gson gson;

    private RecyclerView themeList;
    private RecyclerView recommendsList;
    private RecyclerView nearbyShopsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_take_out_home, container, false);
        initView();
        initBanner();
        initTheme();
        initRecommend();
        initSellerNear();
        return view;
    }


    private void initView() {
        searchView = (SearchView) view.findViewById(R.id.search_view);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);

        banner = (Banner) view.findViewById(R.id.banner);
        themeList = (RecyclerView) view.findViewById(R.id.theme_list);
        recommendsList = (RecyclerView) view.findViewById(R.id.recommends_list);
        nearbyShopsList = (RecyclerView) view.findViewById(R.id.nearbyShops_list);

        cancelBtn.setVisibility(View.GONE);
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

        searchView.setBackgroundResource(R.drawable.takeout_search_view_style);
        searchView.setOnQueryTextListener(this);
        //搜索框取消按钮点击事件
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索框清除内容和焦点
                searchView.setQuery("", false);
                searchView.clearFocus();
                WindowMangerUtils.closeKeyboard(getContext(), searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        BannerSetUtils.setBannerStyle(getContext(), banner);

        gson = new Gson();

        //设置布局管理器
        themeList.setLayoutManager(new GridLayoutManager(getContext(), 5));
        recommendsList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        nearbyShopsList.setLayoutManager(new LinearLayoutManager(getContext()));
        nearbyShopsList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recommendsList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 30;
                outRect.bottom = 30;
                if (parent.getChildLayoutPosition(view) % 2 != 0) {
                    outRect.right = 30;
                }

            }
        });
    }

    /**
     * @Create 2021/9/18 20:05
     * @Role 设置轮播图
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ROTATION_LIST, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeOutRotationBean jsonObj = gson.fromJson(response.body().string(), TakeOutRotationBean.class);
                if (jsonObj.getCode() == 200) {
                    final List<TakeOutRotationBean.RowsBean> rows = jsonObj.getRows();
                    if (rows.size() > 0) {
                        final ArrayList<String> imgUrls = new ArrayList<>();
                        for (int i = 0; i < rows.size(); i++) {
                            imgUrls.add(MyApplication.IP + rows.get(i).getAdvImg());
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                banner.setImages(imgUrls);

                                banner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int i) {
                                        Intent intent = new Intent(getContext(), TakeoutSellerDetailsActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("sellerId", Integer.toString(rows.get(i).getTargetId()));
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                                banner.start();
                            }
                        });

                    }

                }
            }
        });
    }

    /**
     * @Create 2021/9/18 21:08
     * @Role 查询主题分类
     * @Param
     */
    private void initTheme() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_THEME_LIST, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeOutThemeBean jsonObj = gson.fromJson(response.body().string(), TakeOutThemeBean.class);
                if (jsonObj.code == 200) {
                    List<TakeOutThemeBean.DataBean> data = jsonObj.getData();
                    if (data.size() > 0) {
                        final TakeoutThemeAdapter adapter = new TakeoutThemeAdapter(getContext(), data);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                themeList.setAdapter(adapter);
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * @Create 2021/9/18 21:32
     * @Role 设置推荐店家
     * @Param
     */
    private void initRecommend() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_LIST, "?recommend=Y");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeoutSellerBean jsonObj = gson.fromJson(response.body().string(), TakeoutSellerBean.class);
                if (jsonObj.getCode() == 200) {
                    List<TakeoutSellerBean.RowsBean> rows = jsonObj.getRows();
                    if (rows.size() > 0) {
                        final TakeoutRecommSellerAdapter adapter = new TakeoutRecommSellerAdapter(getContext(), rows);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recommendsList.setAdapter(adapter);
                            }
                        });

                    }

                }
            }
        });

    }

    /**
     * @Create 23:42
     * @Role 查询附近商家
     * @Param
     */
    private void initSellerNear() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_NEAR, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeoutSellerBean jsonObj = gson.fromJson(response.body().string(), TakeoutSellerBean.class);
                if (jsonObj.getCode() == 200) {
                    List<TakeoutSellerBean.RowsBean> rows = jsonObj.getRows();
                    if (rows.size() > 0) {
                        final TakeoutSellerListAdapter adapter = new TakeoutSellerListAdapter(getContext(), rows);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                nearbyShopsList.setAdapter(adapter);
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(getContext(), TakeoutSellerListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        intent.putExtras(bundle);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}