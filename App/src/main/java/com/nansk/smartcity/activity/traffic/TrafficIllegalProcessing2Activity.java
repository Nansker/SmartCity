package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.traffic.TrafficIllegalProcessAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.traffic.TrafficIllegalListBean;
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
 * @create 2021/10/04 20:40
 * @description 违法处理
 */

public class TrafficIllegalProcessing2Activity extends BaseActivity {

    private TextView plateNoTv;
    private TabLayout tabMenu;
    private TextView tipsTv;
    private RecyclerView bodyContainer;

    private String[] tabNames;

    private String token;
    private Gson gson;
    private String plateNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_illegal_processing2);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("违章处理");
        initView();
        initObject();
        initTabMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fillData();
    }

    private void initObject() {


        token = MyApplication.getToken(this);
        gson = MyApplication.gson;

        Intent intent = getIntent();
        plateNo = intent.getStringExtra("plateNo");

        plateNoTv.setText(plateNo);

        bodyContainer.setLayoutManager(new LinearLayoutManager(TrafficIllegalProcessing2Activity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
                outRect.bottom = 15;
            }
        });
    }

    private void initView() {

        plateNoTv = (TextView) findViewById(R.id.plateNo_tv);
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        tabNames = new String[]{"未处理", "已处理未缴款", "已缴款"};

    }

    /**
     * @Create 2021/10/4 21:00
     * @Role 初始化状态栏
     * @Param
     */
    private void initTabMenu() {


        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fillData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < tabNames.length; i++) {
            tabMenu.addTab(tabMenu.newTab().setText(tabNames[i]));
        }
    }

    /**
     * @Create 2021/10/4 19:57
     * @Role 获取数据源
     * @Param
     */
    private void fillData() {
        String disposeState = tabNames[tabMenu.getSelectedTabPosition()];
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "list/?licencePlate=" + plateNo + "&disposeState=" + disposeState);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficIllegalListBean jsonObj = gson.fromJson(response.body().string(), TrafficIllegalListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<TrafficIllegalListBean.RowsBean> rows = jsonObj.getRows();
                            TrafficIllegalProcessAdapter adapter = new TrafficIllegalProcessAdapter(TrafficIllegalProcessing2Activity.this, rows);
                            bodyContainer.setAdapter(adapter);

                            adapter.setOnItemCallBack(new TrafficIllegalProcessAdapter.OnItemCallBack() {
                                @Override
                                public void OnItemCallBack(int type, int position, TrafficIllegalListBean.RowsBean obj) {
                                    //查看详情
                                    if (type == 0) {
                                        Intent intent = new Intent(TrafficIllegalProcessing2Activity.this, TrafficIllegalDetailsActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("id",obj.getId());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        //接受处理
                                    } else if (type == 1) {
                                        acceptIllegal(obj.getId());
                                    }
                                }
                            });

                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                            } else {
                                tipsTv.setText("暂无数据！");
                            }
                        } else {
                            Toast.makeText(TrafficIllegalProcessing2Activity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }

    public void acceptIllegal(int id) {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "accept/" + id);
        OkHttpUtil.doPost(url, token, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() ==200){
                            fillData();
                            Toast.makeText(TrafficIllegalProcessing2Activity.this, "已接受处理", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(TrafficIllegalProcessing2Activity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}