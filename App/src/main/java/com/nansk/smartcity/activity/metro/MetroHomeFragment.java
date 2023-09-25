package com.nansk.smartcity.activity.metro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.metro.MetroListAdapter;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.metro.MetroListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.BannerLinkListener;
import com.nansk.smartcity.utils.BannerLoader;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 11:33
 * @description 城市地铁-首页
 */


public class MetroHomeFragment extends Fragment {
    private View view;
    private Banner banner;
    private TextView currentNameTv;
    private RecyclerView stepContainer;

    private LinearLayout tabContainer;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_metro_home, container, false);
        initView();
        initObject();
        initBanner();
        setTabAction();
        initStepList("建国门");

        return view;
    }

    /**
     * @Create 2021/10/6 13:42
     * @Role 获取当前站点信息
     * @Param
     */
    private void initStepList(String stepName) {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_LIST, "?currentName=" + stepName);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroListBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<MetroListBean.DataBean> data = jsonObj.getData();
                            MetroListAdapter adapter = new MetroListAdapter(getContext(), data);
                            stepContainer.setAdapter(adapter);
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
                            final List<BannerBean.RowsBean> rows = jsonObj.getRows();
                            List<String> pagers = new ArrayList<>();
                            List<String> titles = new ArrayList<>();
                            for (int i = 0; i < rows.size(); i++) {
                                pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                                titles.add(rows.get(i).getAdvTitle());
                            }
                            banner.setImages(pagers);
                            banner.setBannerTitles(titles);
                            banner.start();
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int i) {
                                    BannerLinkListener.setBannerLink(getContext(), banner, rows);
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/9 17:24
     * @Role 功能栏点击事件
     * @Param
     */

    private void setTabAction() {
        for (int i = 0; i < tabContainer.getChildCount(); i++) {
            final int finalI = i;
            tabContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            Intent intent = new Intent(getContext(), MetroStepInfoActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            MetroActivity.bottomTabMenu.getTabAt(3).select();
                            break;
                        case 2:
                            Intent intent1 = new Intent(getContext(), MetroNoticeActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dataType", 2);
                            intent1.putExtras(bundle);
                            startActivity(intent1);
                            break;
                        case 3:
                            Intent intent2 = new Intent(getContext(), MetroNoticeActivity.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putInt("dataType", 3);
                            intent2.putExtras(bundle1);
                            startActivity(intent2);
                            break;
                    }
                }
            });
        }
    }


    private void initObject() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new BannerLoader());
        banner.setDelayTime(4000);
        banner.isAutoPlay(true);

        stepContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        stepContainer.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
               initStepList("建国门");
               handler.postDelayed(this,8000);
            }
        };

        handler.postDelayed(runnable, 8000);

    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        currentNameTv = (TextView) view.findViewById(R.id.currentName_tv);
        stepContainer = (RecyclerView) view.findViewById(R.id.step_container);
        tabContainer = (LinearLayout) view.findViewById(R.id.tab_container);
    }

}