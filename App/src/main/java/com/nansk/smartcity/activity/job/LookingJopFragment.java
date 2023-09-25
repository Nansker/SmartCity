package com.nansk.smartcity.activity.job;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.adapter.job.JobPostListAdapter;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.job.JobPostBean;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
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
 * @Create 2021/09/30 17:38
 * @Description 找工作
 */

public class LookingJopFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Banner banner;
    private LinearLayout searchContainer;
    private SearchView searchView;
    private Button cancelBtn;
    private RecyclerView jobContainer;

    private TextView tipsTv;

    private Handler handler;
    private GridLayout hotJobContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_job_looking, null);
        initView();
        initObject();
        initBanner();
        fillJobList("");
        return view;
    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        searchContainer = (LinearLayout) view.findViewById(R.id.search_container);
        searchView = (SearchView) view.findViewById(R.id.search_view);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        jobContainer = (RecyclerView) view.findViewById(R.id.job_container);
        tipsTv = (TextView) view.findViewById(R.id.tips_tv);

        hotJobContainer = view.findViewById(R.id.hotJob_container);
    }

    /**
     * @Create 2021/9/30 19:41
     * @Role
     * @Param
     */
    private void initObject() {
        List<GradientDrawable> drawables = new ArrayList<>();

        GradientDrawable searchDrawable = new GradientDrawable();
        searchDrawable.setCornerRadius(100);
        searchDrawable.setColor(Color.parseColor("#F7F7F7"));
        drawables.add(searchDrawable);

        searchView.setBackground(drawables.get(0));

        //热门职位
        String[] hotjobs = getResources().getStringArray(R.array.hot_jobs);
        for (int i = 0; i < hotjobs.length; i++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(5);
            gradientDrawable.setColor(Color.parseColor("#F7F7F7"));
            drawables.add(gradientDrawable);

            final TextView textView = new TextView(getContext());
            textView.setPadding(30, 15, 30, 15);
            textView.setTextSize(16);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setBackground(gradientDrawable);

            textView.setText(hotjobs[i]);

            GridLayout.LayoutParams gridlayoutParams = new GridLayout.LayoutParams();
            gridlayoutParams.leftMargin = 15;
            gridlayoutParams.rightMargin = 15;
            gridlayoutParams.bottomMargin = 15;
            gridlayoutParams.topMargin = 15;

            textView.setLayoutParams(gridlayoutParams);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fillJobList(textView.getText().toString());
                }
            });

            hotJobContainer.addView(textView);
        }

        BannerSetUtils.setBannerStyle(getContext(), banner);

        searchView.setQueryHint("搜索职位相关信息");
        cancelBtn.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                WindowMangerUtils.closeKeyboard(getContext(), searchView);
                searchView.clearFocus();
                fillJobList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //搜索框焦点监听
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


        //搜索框取消按钮监听
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.clearFocus();
                WindowMangerUtils.closeKeyboard(getContext(), searchView);
                searchView.setQuery("", false);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        handler = new Handler();

        jobContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        jobContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
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

    /**
     * @Create 2021/9/30 21:56
     * @Role 设置banner
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
                BannerBean jsonObj = MyApplication.gson.fromJson(response.body().string(), BannerBean.class);
                final List<BannerBean.RowsBean> rows = jsonObj.getRows();
                final List<String> pagers = new ArrayList<>();
                for (int i = 0; i < rows.size(); i++) {
                    //去掉开屏广告页
                    if (!rows.get(i).getAdvTitle().equals("开屏广告")) {
                        pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(pagers);
                        banner.start();
                    }
                });
            }

        });

    }


    /**
     * @Create 2021/9/30 19:22
     * @Role 获取职位信息
     * @Param condition 按条件查找的字段名
     * @Param query 需要查找的内容
     */
    private void fillJobList(String condition) {
        //向判断用户是否登录
        boolean isLogin = (boolean) SharedPreferencesUtils.getRecord(getContext(), "isLogin", false);
        if (isLogin) {
            String url = ConnectInfo.getUrl(ConnectInfo.JOB_POST, "list?name=" + condition);
            OkHttpUtil.doGet(url, MyApplication.getToken(getContext()), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JobPostBean jsonObj = MyApplication.gson.fromJson(response.body().string(), JobPostBean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200) {
                                if (jsonObj.getTotal() > 0) {
                                    tipsTv.setVisibility(View.GONE);
                                    List<JobPostBean.RowsBean> rows = jsonObj.getRows();
                                    JobPostListAdapter adapter = new JobPostListAdapter(getContext(), rows);
                                    jobContainer.setAdapter(adapter);

                                    if ((getContext() instanceof Activity)) {
                                        Toast.makeText(getContext(), "职位列表信息已更新", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    //默认显示全部
                                    fillJobList("");
                                    Toast.makeText(getContext(), "暂无相关职位信息！", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            });
            //用户未登录
        } else {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            tipsTv.setText("用户暂未登录，无法获取到职位相关信息");
            Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}