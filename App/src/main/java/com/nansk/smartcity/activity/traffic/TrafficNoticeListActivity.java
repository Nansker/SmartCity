package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.traffic.TrafficNoticeListAdapter;
import com.nansk.smartcity.beans.traffic.TrafficCarListBean;
import com.nansk.smartcity.beans.traffic.TrafficLicenseUserBean;
import com.nansk.smartcity.beans.traffic.TrafficNoticeListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/05 21:56
 * @description 处罚书列表
 */

public class TrafficNoticeListActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private LinearLayout searchContainer;
    private SearchView searchView;
    private Button cancelBtn;
    private TextView numberTv;
    private TextView userNameTv;
    private TextView noticeNumTv;
    private TextView tipsTv;
    private RecyclerView bodyContainer;

    private String token;
    private Gson gson;

    private List<Map> objects;
    private Integer noType;
    private TrafficLicenseUserBean.DataBean licenseObj;
    private TrafficCarListBean.RowsBean carObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_notice_list);
        setToolBarTitle("缴纳罚款");
        setToolBarBackground("#2c65a8");

        initView();
        initObject();

        fillData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        fillData();
    }

    /**
     * @Create 2021/10/5 22:36
     * @Role 获取处罚书列表
     * @Param
     */
    private void getNoticeList(String no) {
        String url;
        if (noType == 0) {
            //驾驶证
            url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_NOTICE, "list?licenseNo=" + no);
        } else {
            //车牌号
            url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_NOTICE, "list?plateNo=" + no);
        }

        if (url != null) {
            OkHttpUtil.doGet(url, token, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final TrafficNoticeListBean jsonObj = gson.fromJson(response.body().string(), TrafficNoticeListBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200) {
                                noticeNumTv.setText(jsonObj.getTotal() + "");
                                if (jsonObj.getTotal() > 0) {
                                    tipsTv.setVisibility(View.GONE);
                                    List<TrafficNoticeListBean.RowsBean> rows = jsonObj.getRows();
                                    TrafficNoticeListAdapter adapter = new TrafficNoticeListAdapter(TrafficNoticeListActivity.this, rows);
                                    adapter.setOnItemCallBack(new TrafficNoticeListAdapter.OnItemCallBack() {
                                        @Override
                                        public void OnItemCallBack(int position, TrafficNoticeListBean.RowsBean obj) {
                                            Intent intent = new Intent(TrafficNoticeListActivity.this, TrafficNoticeDetailsActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("noticeId", obj.getId());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    });
                                    bodyContainer.setAdapter(adapter);
                                } else {
                                    tipsTv.setText("暂无违法记录");
                                }

                            } else {
                                tipsTv.setText(jsonObj.getMsg());
                            }
                        }
                    });
                }
            });
        } else {
            tipsTv.setText("连接服务器出现异常！");
            Toast.makeText(this, "连接服务器出现异常！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @Create 2021/10/5 22:27
     * @Role 根据驾驶证查询处罚通知书
     * @Param
     */
    private void getLicenseNotice() {
        numberTv.setText(getValue(licenseObj.getIdCard()));
        userNameTv.setText(getValue(licenseObj.getUserName()));

        getNoticeList(licenseObj.getLicenseNo());
    }

    /**
     * @Create 2021/10/5 22:28
     * @Role 根据车辆查询处罚通知书
     * @Param
     */
    private void getCarNotice() {
        numberTv.setText(getValue(carObj.getPlateNo()));
        userNameTv.setText(getValue(carObj.getUserName()));
        getNoticeList(carObj.getPlateNo());
    }


    private String getValue(String value) {
        if (value != "" && value != null) {
            return value;
        }
        return "暂无数据";
    }

    private void fillData() {
        if (noType == 0) {
            getLicenseNotice();
        } else {
            getCarNotice();
        }
    }


    private void initObject() {

        GradientDrawable searchDrawable = new GradientDrawable();
        searchDrawable.setColor(Color.parseColor("#F7F7F7"));
        searchDrawable.setCornerRadius(10);
        searchView.setBackground(searchDrawable);
        searchView.setQueryHint("请输入触发决定书编号");
        cancelBtn.setVisibility(View.GONE);
        searchView.setOnQueryTextListener(this);
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cancelBtn.setVisibility(View.VISIBLE);
                } else {
                    cancelBtn.setVisibility(View.GONE);
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("", false);
                WindowMangerUtils.closeKeyboard(TrafficNoticeListActivity.this, searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        token = MyApplication.getToken(this);
        gson = MyApplication.gson;

        bodyContainer.setLayoutManager(new LinearLayoutManager(TrafficNoticeListActivity.this));
        bodyContainer.addItemDecoration(new DividerItemDecoration(TrafficNoticeListActivity.this,DividerItemDecoration.VERTICAL));

        Intent intent = getIntent();
        objects = (List<Map>) intent.getSerializableExtra("objects");
        noType = (Integer) objects.get(0).get("type");

        if (noType == 0) {
            licenseObj = (TrafficLicenseUserBean.DataBean) objects.get(1).get("obj");
        } else {
            carObj = (TrafficCarListBean.RowsBean) objects.get(1).get("obj");
        }

    }
    /**
     * @Create 2021/10/7 15:36
     * @Role 搜索处罚书并进入详情页
     * @Param
     */
    private void searchNotice(String noticeNo) {
        WindowMangerUtils.closeKeyboard(TrafficNoticeListActivity.this, searchView);
        searchView.clearFocus();

        String url;
        if (noType == 0) {
            //驾驶证
            url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_NOTICE, "list?licenseNo="+licenseObj.getId()+"&code=" + noticeNo);
        } else {
            //车牌号
            url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_NOTICE, "list?plateNo="+carObj.getPlateNo()+"&code=" + noticeNo);
        }

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
                                Intent intent = new Intent(TrafficNoticeListActivity.this, TrafficNoticeDetailsActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("noticeId", jsonObj.getRows().get(0).getId());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                Toast.makeText(TrafficNoticeListActivity.this, "没有搜索到相关结果！", Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(TrafficNoticeListActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
    private void initView() {
        searchContainer = (LinearLayout) findViewById(R.id.search_container);
        searchView = (SearchView) findViewById(R.id.search_view);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        numberTv = (TextView) findViewById(R.id.number_tv);
        userNameTv = (TextView) findViewById(R.id.userName_tv);
        noticeNumTv = (TextView) findViewById(R.id.noticeNum_tv);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchNotice(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}