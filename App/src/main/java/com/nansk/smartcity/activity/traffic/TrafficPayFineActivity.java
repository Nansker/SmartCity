package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.traffic.TrafficCarListAdapter;
import com.nansk.smartcity.adapter.traffic.TrafficLicenseListAdapter;
import com.nansk.smartcity.beans.traffic.TrafficCarListBean;
import com.nansk.smartcity.beans.traffic.TrafficLicenseUserBean;
import com.nansk.smartcity.beans.traffic.TrafficNoticeListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/05 19:47
 * @description 缴纳罚款
 */

public class TrafficPayFineActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private LinearLayout searchContainer;
    private SearchView searchView;
    private Button cancelBtn;
    private RecyclerView licenseContainer;
    private TextView tips1Tv;
    private RecyclerView carContainer;
    private TextView tips2Tv;

    private String token;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_pay_fine);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("罚款缴纳");
        setToolBarRightButton(true, "缴纳历史", new MyCallBack() {
            @Override
            public void CallBack() {

            }
        });

        initView();
        initObject();

        initLicenseList();
        initCarList();
    }

    /**
     * @Create 2021/10/5 20:27
     * @Role 获取已绑定驾驶证列表
     * @Param
     */
    private void initLicenseList() {
        //获取当前登录人绑定的驾驶证信息
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_LICENSE, "user");
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                final TrafficLicenseUserBean jsonObj = gson.fromJson(string, TrafficLicenseUserBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getData().size() > 0) {
                            TrafficLicenseListAdapter adapter = new TrafficLicenseListAdapter(TrafficPayFineActivity.this, jsonObj.getData());
                            licenseContainer.setAdapter(adapter);
                            adapter.OnItemCallBack(new TrafficLicenseListAdapter.OnItemCallBack() {
                                @Override
                                public void OnItemCallBack(int position, TrafficLicenseUserBean.DataBean obj) {
                                    LinkNoticeList(0,obj);
                                }
                            });
                        } else {
                            tips1Tv.setVisibility(View.VISIBLE);
                            tips1Tv.setText("暂无绑定的驾驶证信息");
                        }
                    }
            });
        }
        });
    }

    /**
     * @Create 2021/10/5 20:28
     * @Role 获取已绑定机动车列表
     * @Param
     */
    private void initCarList() {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_CAR, "list");
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficCarListBean jsonObj = gson.fromJson(response.body().string(), TrafficCarListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            if (jsonObj.getTotal()>0){
                                tips2Tv.setVisibility(View.GONE);
                                List<TrafficCarListBean.RowsBean> rows = jsonObj.getRows();
                                TrafficCarListAdapter adapter = new TrafficCarListAdapter(TrafficPayFineActivity.this, rows);
                                carContainer.setAdapter(adapter);
                                adapter.OnItemCallBack(new TrafficCarListAdapter.OnItemCallBack() {
                                    @Override
                                    public void OnItemCallBack(int position, TrafficCarListBean.RowsBean obj) {
                                        LinkNoticeList(1,obj);
                                    }
                                });
                            }else {
                                tips2Tv.setText("暂无绑定的车辆");
                            }
                        }else {
                            Toast.makeText(TrafficPayFineActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/5 22:03
     * @Role 跳转到处罚通知书列表页
     * @Param noType=对象类型（0=驾驶证，1=机动车）
     */
    private void LinkNoticeList(Integer noType,Object obj){
        List<Map> maps = new ArrayList<>();
        HashMap<String, Integer> type = new HashMap<>();
        HashMap<String, Object> object = new HashMap<>();
        type.put("type",noType);
        object.put("obj",obj);
        maps.add(type);
        maps.add(object);

        Intent intent = new Intent(TrafficPayFineActivity.this, TrafficNoticeListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objects", (Serializable) maps);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * @Create 2021/10/7 15:36
     * @Role 搜索处罚书并进入详情页
     * @Param
     */
    private void searchNotice(String noticeNo) {
        WindowMangerUtils.closeKeyboard(TrafficPayFineActivity.this, searchView);
        searchView.clearFocus();
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_NOTICE, "list?code=" + noticeNo);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficNoticeListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TrafficNoticeListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            if (jsonObj.getTotal() > 0) {
                                Intent intent = new Intent(TrafficPayFineActivity.this, TrafficNoticeDetailsActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("noticeId", jsonObj.getRows().get(0).getId());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                Toast.makeText(TrafficPayFineActivity.this, "没有搜索到相关结果！", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(TrafficPayFineActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    private void initObject() {

        tips1Tv.setVisibility(View.GONE);

        token = MyApplication.getToken(this);
        gson = MyApplication.gson;

        GradientDrawable searchDrawable = new GradientDrawable();
        searchDrawable.setColor(Color.WHITE);
        searchDrawable.setCornerRadius(10);
        searchView.setBackground(searchDrawable);
        searchView.setQueryHint("请输入处罚决定书编号");

        searchView.setOnQueryTextListener(this);
        cancelBtn.setVisibility(View.GONE);
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    cancelBtn.setVisibility(View.VISIBLE);
                }else {
                    cancelBtn.setVisibility(View.GONE);
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("",false);
                WindowMangerUtils.closeKeyboard(TrafficPayFineActivity.this,searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        licenseContainer.setLayoutManager(new LinearLayoutManager(TrafficPayFineActivity.this));
        carContainer.setLayoutManager(new LinearLayoutManager(TrafficPayFineActivity.this));

        carContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top =15;
                outRect.bottom = 15;
            }
        });
    }

    private void initView() {

        searchContainer = (LinearLayout) findViewById(R.id.search_container);
        searchView = (SearchView) findViewById(R.id.search_view);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        licenseContainer = (RecyclerView) findViewById(R.id.license_container);
        tips1Tv = (TextView) findViewById(R.id.tips1_tv);
        carContainer = (RecyclerView) findViewById(R.id.car_container);
        tips2Tv = (TextView) findViewById(R.id.tips2_tv);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchNotice(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}