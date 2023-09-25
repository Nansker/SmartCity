package com.nansk.smartcity.activity.metro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.metro.MetroLineListAdapter;
import com.nansk.smartcity.adapter.metro.MetroStepListAdapter;
import com.nansk.smartcity.beans.metro.MetroLinesListBean;
import com.nansk.smartcity.beans.metro.MetroStepLisBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 17:13
 * @description 地铁站点信息页
 */

public class MetroStepInfoActivity extends BaseActivity {

    private RecyclerView lineContainer;
    private RecyclerView stepContainer;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro_step_info);
        setToolBarTitle("站点列表");
        initView();
        getLines();
        getSteps(0);
    }

    /**
     * @Create 2021/10/9 17:48
     * @Role 查询城市所有线路
     * @Param
     */
    private void getLines() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_ALL_LINE, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroLinesListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroLinesListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<MetroLinesListBean.DataBean> data = jsonObj.getData();
                            //线路名去重
                            Set<MetroLinesListBean.DataBean> set = new TreeSet<>(new Comparator<MetroLinesListBean.DataBean>() {
                                @Override
                                public int compare(MetroLinesListBean.DataBean o1, MetroLinesListBean.DataBean o2) {
                                    return o1.getLineName().compareTo(o2.getLineName());
                                }
                            });
                            set.addAll(data);
                            ArrayList<MetroLinesListBean.DataBean> newList = new ArrayList<>(set);

                            final MetroLineListAdapter adapter = new MetroLineListAdapter(MetroStepInfoActivity.this, newList);
                            adapter.setOnItemCallBack(new MetroLineListAdapter.OnItemCallBack() {
                                @Override
                                public void OnItemCallBack(int position, MetroLinesListBean.DataBean obj) {
                                    adapter.setPosition(position);
                                    adapter.notifyDataSetChanged();
                                    if (position == 0) {
                                        getSteps(0);
                                    } else {
                                        getSteps(obj.getLineId());
                                    }

                                }
                            });

                            lineContainer.setAdapter(adapter);
                        } else {
                            Toast.makeText(MetroStepInfoActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //根据线路获取站点
    private void getSteps(final int lineId) {
        String url;
        if (lineId != 0) {
            url = ConnectInfo.getUrl(ConnectInfo.METRO_STEP, "?lineId=" + lineId);
        } else {
            url = ConnectInfo.getUrl(ConnectInfo.METRO_STEP, "");
            stepContainer.setVisibility(View.INVISIBLE);
            tipsTv.setVisibility(View.VISIBLE);
        }

        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroStepLisBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroStepLisBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<MetroStepLisBean.DataBean> data = jsonObj.getData();
                            if (data.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                stepContainer.setVisibility(View.VISIBLE);
                                MetroStepListAdapter adapter = new MetroStepListAdapter(MetroStepInfoActivity.this, data);
                                //点击跳转到站点详情页
                                adapter.setOnItemCallBack(new MetroStepListAdapter.OnItemCallBack() {
                                    @Override
                                    public void OnItemCallBack(int position, MetroStepLisBean.DataBean obj) {
                                        Intent intent = new Intent(MetroStepInfoActivity.this, MetroStepDetailsActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("stepObj",obj);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                                stepContainer.setAdapter(adapter);
                            }

                        } else {
                            Toast.makeText(MetroStepInfoActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    private void initView() {

        lineContainer = (RecyclerView) findViewById(R.id.line_container);
        stepContainer = (RecyclerView) findViewById(R.id.step_container);

        lineContainer.setLayoutManager(new LinearLayoutManager(MetroStepInfoActivity.this));
        stepContainer.setLayoutManager(new LinearLayoutManager(MetroStepInfoActivity.this));
        tipsTv = (TextView) findViewById(R.id.tips_tv);
    }
}