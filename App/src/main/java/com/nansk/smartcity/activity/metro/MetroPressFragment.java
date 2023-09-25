package com.nansk.smartcity.activity.metro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.metro.MetroPressListAdapter;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.metro.MetroPressCategoryBean;
import com.nansk.smartcity.beans.metro.MetroPressListBean;
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
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 11:34
 * @description 城市地铁-生活
 */


public class MetroPressFragment extends Fragment {
    private View view;
    private Banner banner;
    private TabLayout tabMenu;
    private RecyclerView bodyContainer;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_metro_press, container, false);
        initView();
        initBanner();
        initTabMenu();
        fillData("4");
        return view;
    }

    /**
     * @Create 2021/10/6 16:39
     * @Role 获取数据
     * @Param
     * @param categoryId
     */
    private void fillData(String categoryId) {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_PRESS, "press/list?type="+categoryId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroPressListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroPressListBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            List<MetroPressListBean.RowsBean> data = jsonObj.getRows();
                            MetroPressListAdapter adapter = new MetroPressListAdapter(getContext(), data);
                            bodyContainer.setAdapter(adapter);
                            adapter.OnItemCallBack(new MetroPressListAdapter.OnItemCallBack() {
                                @Override
                                public void OnItemCallBack(int position, MetroPressListBean.RowsBean obj) {
                                    Intent intent = new Intent(getContext(), MetroPressDetailsActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("pressId",obj.getId());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });

                        }else {
                            Toast.makeText(getContext(), jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private void initTabMenu() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_PRESS, "category/list");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroPressCategoryBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroPressCategoryBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            List<MetroPressCategoryBean.DataBean> data = jsonObj.getData();
                            final List<String> categoryId = new ArrayList<>();
                            for (int i =0;i<data.size();i++){
                                categoryId.add(Integer.toString(data.get(i).getId()));
                                tabMenu.addTab(tabMenu.newTab().setText(data.get(i).getName()));
                            }

                            tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    fillData(categoryId.get(tab.getPosition()));
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });

                        }else {
                            Toast.makeText(getContext(), jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    /**
     * @Create 2021/10/6 13:09
     * @Role 设置轮播图
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_ROTATION, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final BannerBean jsonObj = MyApplication.gson.fromJson(response.body().string(), BannerBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<BannerBean.RowsBean> rows = jsonObj.getRows();
                            List<String> pagers = new ArrayList<>();
                            List<String> titles = new ArrayList<>();
                            for (int i = 0; i < rows.size(); i++) {
                                pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                                titles.add(rows.get(i).getAdvTitle());
                            }
                            banner.setImages(pagers);
                            banner.setBannerTitles(titles);
                            banner.start();
                        } else {
                            Toast.makeText(getContext(), jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        tabMenu = (TabLayout) view.findViewById(R.id.tab_menu);
        bodyContainer = (RecyclerView) view.findViewById(R.id.body_container);

        BannerSetUtils.setBannerStyle(getContext(),banner);

        handler = new Handler();
        bodyContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        bodyContainer.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }
}