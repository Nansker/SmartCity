package com.nansk.smartcity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.activity.press.PressDetailsActivity;
import com.nansk.smartcity.model.impl.OnItemClickListener;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.MainActivity;
import com.nansk.smartcity.activity.press.PressSearchActivity;
import com.nansk.smartcity.base.BaseAdapter;
import com.nansk.smartcity.base.BaseViewHolder;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.press.PressListBean;
import com.nansk.smartcity.beans.service.ServiceJsonObj;
import com.nansk.smartcity.beans.service.ServiceJsonRows;
import com.nansk.smartcity.utils.BannerLinkListener;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.ServiceLinkListener;
import com.nansk.smartcity.utils.WindowMangerUtils;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 13:05
 * @Description主页
 */

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener, SearchView.OnQueryTextListener {
    private View view;
    private Gson gson;

    //轮播图
    private Banner banner;

    private RecyclerView recommendsList;
    private RecyclerView hotTopicList;
    private TabLayout pressTabMenu;
    private RecyclerView pressList;

    private int[] pressTabMenuId = new int[]{9, 17, 19, 20, 21, 22};
    private SearchView searchView;
    private Button cancelBtn;

    private Handler handler;

    private BaseAdapter<ServiceJsonRows> recommendAdapter;
    private BaseAdapter<PressListBean.RowsBean> hotTopicAdapter;
    private BaseAdapter<PressListBean.RowsBean> pressListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gson = MyApplication.gson;
        initView();
        initObject();
        initAdapter();
        initData();
        setPressCategory();
        return view;
    }


    /**
     * @Create 2021/10/24 22:01
     * @Role
     * @Param
     */
    private void initAdapter() {
//        推荐服务
        recommendAdapter = new BaseAdapter<ServiceJsonRows>(getContext(), R.layout.item_service_item) {
            @Override
            protected void onConvert(BaseViewHolder holder, ServiceJsonRows item, int position) {
                if (position != 9) {
                    Glide.with(getContext()).load(MyApplication.IP + item.getImgUrl()).placeholder(R.drawable.ic_baseline_insert_photo_24).into(holder.getImageView(R.id.icon_iv));
                } else {
                    Glide.with(getContext()).load(R.mipmap.services).into(holder.getImageView(R.id.icon_iv));
                }

                holder.getTextView(R.id.name_tv).setText(item.getServiceName());
            }
        };

        recommendAdapter.setOnItemCallBack(new OnItemClickListener<ServiceJsonRows>() {
            @Override
            public void OnItemCallBack(int position, ServiceJsonRows obj) {
                super.OnItemCallBack(position, obj);
                if (position != 9) {
                    ServiceLinkListener.setLinkListener(getContext(), obj);
                } else {
                    MainActivity.bottomTabLayout.getTabAt(1).select();
                }
            }
        });

//        热门主题
        hotTopicAdapter = new BaseAdapter<PressListBean.RowsBean>(getContext(),R.layout.item_hot_topic) {
            @Override
            protected void onConvert(BaseViewHolder holder, PressListBean.RowsBean item, int position) {
                holder.getTextView(R.id.title_tv).setText("\u3000" + item.getTitle());
                Glide.with(getContext()).load(MyApplication.IP + item.getCover()).placeholder(R.drawable.default_img).into(holder.getImageView(R.id.image_iv));
            }
        };
        hotTopicAdapter.setOnItemCallBack(new OnItemClickListener<PressListBean.RowsBean>() {
            @Override
            public void OnItemCallBack(int position, PressListBean.RowsBean obj) {
                super.OnItemCallBack(position, obj);
                Intent intent = new Intent(getContext(), PressDetailsActivity.class);
                intent.putExtra("pressId", obj.getId());
                startActivity(intent);
            }
        });

//        新闻列表
        pressListAdapter = new BaseAdapter<PressListBean.RowsBean>(getContext(), R.layout.item_press_list) {
            @Override
            protected void onConvert(BaseViewHolder holder, PressListBean.RowsBean item, int position) {
                holder.getTextView(R.id.title_tv).setText(item.getTitle());
                Glide.with(getContext()).load(MyApplication.getIP(getContext()) + item.getCover()).into(holder.getImageView(R.id.cover_iv));
                holder.getTextView(R.id.time_tv).setText(item.getPublishDate());
                holder.getTextView(R.id.readNum_tv).setText("阅读人数：" + item.getReadNum());
                holder.getTextView(R.id.likeNum_tv).setText("点赞人数：" + item.getLikeNum());
            }
        };

        pressListAdapter.setOnItemCallBack(new OnItemClickListener<PressListBean.RowsBean>() {
            @Override
            public void OnItemCallBack(int position, PressListBean.RowsBean obj) {
                super.OnItemCallBack(position, obj);
                Intent intent = new Intent(getContext(), PressDetailsActivity.class);
                intent.putExtra("pressId", obj.getId());
                startActivity(intent);
            }
        });
    }


    public void initData() {
        setBanner();
        setRecommendService();
        setHotTopicData();
    }

    /**
     * @Create 2021/9/8 15:50
     * @Role 获取Banner轮播图数据
     * @Param
     */
    public void setBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOME_BANNER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Gson解析
                final BannerBean jsonObj = gson.fromJson(response.body().string(), BannerBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        final List<BannerBean.RowsBean> rows = jsonObj.getRows();
                        final List<String> pagers = new ArrayList<>();
                        for (int i = 0; i < rows.size(); i++) {
                            //去掉开屏广告页
                            if (rows.get(i).getAdvTitle().equals("开屏广告")) {
                                rows.remove(rows.get(i));
                            }
                            pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                        }

                        banner.setImages(pagers);
                        BannerLinkListener.setBannerLink(getContext(), banner, rows);
                        banner.start();
                    }
                });
            }

        });
    }

    /**
     * @Create 2021/9/8 20:49
     * @Role 获取推荐服务
     * @Param
     */
    public void setRecommendService() {
        String url = ConnectInfo.getUrl(ConnectInfo.SERVICE_LIST, "?pageNum=1&pageSize=9");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ServiceJsonObj serviceObj = gson.fromJson(response.body().string(), new TypeToken<ServiceJsonObj>() {
                }.getType());
                final List<ServiceJsonRows> serviceRows = serviceObj.getRows();

                //对数据源根据权重排序,升序
                Collections.sort(serviceRows, new Comparator<ServiceJsonRows>() {
                    @Override
                    public int compare(ServiceJsonRows o1, ServiceJsonRows o2) {
                        int i = o1.getSort() - o2.getSort();
                        return i;
                    }
                });

                //将排序好的集合顺序翻转，改为降序
                Collections.reverse(serviceRows);
                //最后一个添加全部服务
                ServiceJsonRows obj = new ServiceJsonRows();
                obj.setServiceName("全部服务");
                serviceRows.add(obj);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recommendAdapter.setData(serviceRows);
                        recommendsList.setAdapter(recommendAdapter);
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/9/9 10:59
     * @Role 设置热门主题数据
     * @Param
     */
    public void setHotTopicData() {
        String url = ConnectInfo.getUrl(ConnectInfo.PRESS_LIST, "?hot=Y");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PressListBean jsonObj = gson.fromJson(response.body().string(), PressListBean.class);

                if (jsonObj.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hotTopicAdapter.setData(jsonObj.getRows());
                            hotTopicList.setAdapter(hotTopicAdapter);
                        }
                    });
                }
            }
        });


    }

    /**
     * @Create 2021/9/9 15:44
     * @Role 新闻分类
     * @Param
     */
    public void setPressCategory() {
        String[] categoryNames = getContext().getResources().getStringArray(R.array.press_category_names);
        for (int i = 0; i < categoryNames.length; i++) {
            pressTabMenu.addTab(pressTabMenu.newTab().setText(categoryNames[i]));
        }
    }


    /**
     * @Create 2021/9/9 16:21
     * @Role 获取新闻列表
     * @Param
     */
    public void getPressList(String condition) {

        String url = ConnectInfo.getUrl(ConnectInfo.PRESS_LIST, condition);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PressListBean pressJsonObj = gson.fromJson(response.body().string(), PressListBean.class);
                final List<PressListBean.RowsBean> rows = pressJsonObj.getRows();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pressListAdapter.setData(rows);
                        pressList.setAdapter(pressListAdapter);
                    }
                });
            }
        });


    }

    private void initObject() {
        handler = new Handler();

        //设置banner显示样式
        BannerSetUtils.setBannerStyle(getContext(), banner);

        //推荐服务
        recommendsList.setLayoutManager(new GridLayoutManager(getContext(), 5));

        //热门主题
        hotTopicList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        //新闻列表
        pressList.setLayoutManager(new LinearLayoutManager(getContext()));
        pressTabMenu.addOnTabSelectedListener(this);

        //设置分割线
        pressList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

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
        cancelBtn.setVisibility(View.GONE);
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
    }

    public void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        recommendsList = (RecyclerView) view.findViewById(R.id.recommends_list);
        hotTopicList = (RecyclerView) view.findViewById(R.id.hotNews_list);
        pressTabMenu = (TabLayout) view.findViewById(R.id.press_tab_menu);
        pressList = (RecyclerView) view.findViewById(R.id.press_list);
        searchView = (SearchView) view.findViewById(R.id.search_view);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
    }

    /**
     * @Create 2021/9/9 19:19
     * @Role 新闻分类导航栏点击监听事件
     * @Param
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        getPressList("?type=" + pressTabMenuId[pressTabMenu.getSelectedTabPosition()]);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        WindowMangerUtils.closeKeyboard(getContext(), searchView);
        searchView.clearFocus();
        Intent intent = new Intent(getContext(), PressSearchActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        intent.putExtras(bundle);
        startActivity(intent);
        searchView.setQuery("", false);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}