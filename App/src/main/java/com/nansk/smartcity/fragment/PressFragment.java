package com.nansk.smartcity.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.press.PressDetailsActivity;
import com.nansk.smartcity.base.BaseAdapter;
import com.nansk.smartcity.base.BaseViewHolder;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.press.PressListBean;
import com.nansk.smartcity.model.impl.OnItemClickListener;
import com.nansk.smartcity.utils.BannerLinkListener;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.BannerSetUtils;
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
 * @Create 2021/09/14 10:23
 * @Description 新闻
 */

public class PressFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private View view;
    private Gson gson;

    //轮播图
    private Banner banner;

    private TabLayout pressTabMenu;
    private RecyclerView pressList;
    private BaseAdapter<PressListBean.RowsBean> pressListAdapter;

    private int[] pressTabMenuId = new int[]{9, 17, 19, 20, 21, 22};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_press, container, false);
        gson = MyApplication.gson;

        initView();
        initBanner();
        setPressCategory();
        return view;
    }

    public void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        pressTabMenu = (TabLayout) view.findViewById(R.id.press_tab_menu);
        pressList = (RecyclerView) view.findViewById(R.id.press_list);

        //设置banner显示样式
        BannerSetUtils.setBannerStyle(getContext(),banner);

        //新闻列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        pressList.setLayoutManager(layoutManager);
        pressTabMenu.addOnTabSelectedListener(this);

        //设置分割线
        pressList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

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

    /**
     * @Create 2021/9/8 15:50
     * @Role 获取Banner轮播图数据
     * @Param
     */
    public void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOME_BANNER, "");
        OkHttpUtil.doGet(url, "",new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Gson解析
                BannerBean jsonObj = gson.fromJson(response.body().string(), BannerBean.class);
                if (jsonObj.getCode() == 200){
                    final List<BannerBean.RowsBean> rows = jsonObj.getRows();
                    final List<String> pagers = new ArrayList<>();
                    for (int i = 0; i < rows.size(); i++) {
                        //去掉开屏广告页
                        if (rows.get(i).getAdvTitle().equals("开屏广告")) {
                            rows.remove(i);
                        }
                        pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.setImages(pagers);
                            BannerLinkListener.setBannerLink(getContext(),banner,rows);
                            banner.start();
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
    public void initPressList(String condition) {

        String url = ConnectInfo.getUrl(ConnectInfo.PRESS_LIST,condition);
        OkHttpUtil.doGet(url,"", new Callback() {
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

    /**
     * @Create 2021/9/9 19:19
     * @Role 新闻分类导航栏点击监听事件
     * @Param
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        initPressList("?type=" + pressTabMenuId[pressTabMenu.getSelectedTabPosition()]);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}