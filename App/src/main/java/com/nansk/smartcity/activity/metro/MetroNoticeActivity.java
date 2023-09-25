package com.nansk.smartcity.activity.metro;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.metro.MetroFoundListAdapter;
import com.nansk.smartcity.adapter.metro.MetroNoticeListAdapter;
import com.nansk.smartcity.beans.metro.MetroFoundListBean;
import com.nansk.smartcity.beans.metro.MetroNoticeBean;
import com.nansk.smartcity.beans.metro.MetroStatementBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 14:55
 * @description 乘车须知、运营公告
 */

public class MetroNoticeActivity extends BaseActivity {

    private WebView webView;
    private RecyclerView bodyContainer;

    private int dataType; //2=乘车须知 3=运营公告
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro_notice);
        initView();
        fillData();
    }

    /**
     * @Create 2021/10/11 15:02
     * @Role 获取数据
     * @Param
     */
    private void fillData() {
        if (dataType == 2) {
            getStatement(1);
        } else if (dataType == 3) {
            setToolBarTitle("运营公告");
            getNoticeList();
        } else if (dataType == 4) {
            setToolBarTitle("失物招领");
            getFoundList();
        } else if (dataType == 5) {
           getStatement(3);
            setToolBarTitle("免责声明");
        }
    }

    /**
     * @Create 2021/10/11 16:55
     * @Role 获取失物招领列表
     * @Param
     */
    private void getFoundList() {
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
                outRect.bottom = 15;
            }
        });
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_FOUND, "list");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroFoundListBean json = MyApplication.gson.fromJson(response.body().string(), MetroFoundListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            tipsTv.setVisibility(View.GONE);
                            List<MetroFoundListBean.DataBean> data = json.getData();
                            MetroFoundListAdapter adapter = new MetroFoundListAdapter(MetroNoticeActivity.this, data);
                            bodyContainer.setAdapter(adapter);
                        } else {
                            Toast.makeText(MetroNoticeActivity.this, json.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/11 15:27
     * @Role 获取公告列表
     * @Param
     */
    private void getNoticeList() {
        bodyContainer.addItemDecoration(new DividerItemDecoration(MetroNoticeActivity.this,DividerItemDecoration.VERTICAL));
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_NOTICE, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroNoticeBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroNoticeBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            tipsTv.setVisibility(View.GONE);
                            List<MetroNoticeBean.RowsBean> rows = jsonObj.getRows();
                            MetroNoticeListAdapter adapter = new MetroNoticeListAdapter(MetroNoticeActivity.this, rows);
                            bodyContainer.setAdapter(adapter);
                        } else {
                            Toast.makeText(MetroNoticeActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    /**
     * @Create 2021/10/11 15:10
     * @Role 获取乘车公告
     * @Param
     */
    private void getStatement(int type) {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_STATEMENT, "?type="+type);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroStatementBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroStatementBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            tipsTv.setVisibility(View.GONE);
                            MetroStatementBean.DataBean data = jsonObj.getData();
                            setToolBarTitle(SystemUtils.getValue(data.getTitle(), ""));
                            try {
                                webView.loadData(URLEncoder.encode(data.getContent(), "utf-8"), "text/html", "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(MetroNoticeActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        tipsTv = (TextView) findViewById(R.id.tips_tv);

        webView = (WebView) findViewById(R.id.webView);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        bodyContainer.setLayoutManager(new LinearLayoutManager(MetroNoticeActivity.this));

        dataType = getIntent().getIntExtra("dataType", 0);

    }
}