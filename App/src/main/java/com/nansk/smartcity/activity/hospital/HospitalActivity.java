package com.nansk.smartcity.activity.hospital;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.hospital.HospitalListAdapter;
import com.nansk.smartcity.beans.hospital.HospitalBannerBean;
import com.nansk.smartcity.beans.hospital.HospitalListBean;
import com.nansk.smartcity.utils.BannerLoader;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;
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
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 10:09
 * @Description
 */

public class HospitalActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    private Gson gson;

    private SearchView searchView;
    private Button cancelBtn;
    private RecyclerView bodyContainer;
    private Banner banner;
    private ImageButton backBtn;
    private Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_hospital);
        setToolbarIsShow(false);

        initView();
        initObject();
        setBanner();
        getHospitalList("");
    }

    /**
     * @Create 2021/10/21 15:14
     * @Role
     * @Param
     */
    private void setBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL_BANNER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final HospitalBannerBean bannerBean = gson.fromJson(response.body().string(), HospitalBannerBean.class);
                final List<HospitalBannerBean.DataBean> data = bannerBean.getData();
                final List<String> imgUrls = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    imgUrls.add(MyApplication.IP + data.get(i).getImgUrl());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(imgUrls);
                        banner.start();
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int i) {
                                Intent intent = new Intent(HospitalActivity.this, HospitalDetailsActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("hospitalId",data.get(i).getId());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                });

            }
        });
    }

    /**
     * @Create 2021/10/21 14:53
     * @Role
     * @Param
     */
    private void initObject() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateBtn.setBackground(getDrawable("#f2f2f2",100,4,"#e6e6e6"));
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHospitalList("");
            }
        });

        cancelBtn.setVisibility(View.GONE);
        searchView.setQueryHint("请输入医院名称");

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
                closeKeyboard(searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        searchView.setBackground(getDrawable("#f2f2f2", 100));

        //设置banner显示样式
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new BannerLoader());
        banner.setDelayTime(4000);
        banner.isAutoPlay(true);

        bodyContainer.setLayoutManager(new LinearLayoutManager(HospitalActivity.this));

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 10;
                outRect.right = 10;
                outRect.top = 20;
            }
        });
    }


    private void initView() {
        gson = MyApplication.gson;
        searchView = (SearchView) findViewById(R.id.search_view);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        banner = (Banner) findViewById(R.id.banner);
        backBtn = (ImageButton) findViewById(R.id.back_btn);
        updateBtn = (Button) findViewById(R.id.update_btn);
    }

    /**
     * @Create 2021/9/17 10:47
     * @Role 获取医院列表
     * @Param
     */
    private void getHospitalList(String condition) {
        String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL, "list?hospitalName=" + condition);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HospitalListBean hospitalListBean = gson.fromJson(response.body().string(), HospitalListBean.class);
                List<HospitalListBean.RowsBean> rows = hospitalListBean.getRows();
                final HospitalListAdapter adapter = new HospitalListAdapter(HospitalActivity.this, rows);

                adapter.setOnItemCallBack(new OnItemCallBack() {
                    @Override
                    public void OnItemCallBack(int position, Object obj) {
                        HospitalListBean.RowsBean object = (HospitalListBean.RowsBean) obj;
                        Intent intent = new Intent(HospitalActivity.this, HospitalDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("hospitalId",object.getId());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                if (hospitalListBean.getTotal() > 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bodyContainer.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getHospitalList(query);
        WindowMangerUtils.closeKeyboard(HospitalActivity.this, searchView);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}