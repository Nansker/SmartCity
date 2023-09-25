package com.nansk.smartcity.activity.bus;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.bus.BusLinesAdapter;
import com.nansk.smartcity.base.BaseAdapter;
import com.nansk.smartcity.beans.bus.BusLinesBean;
import com.nansk.smartcity.beans.bus.BusStopBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 11:15
 * @Description 智慧巴士首页
 */

public class BusActivity extends BaseActivity {
    private Gson gson;

    private RecyclerView bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_bus);
        setToolBarTitle("智慧巴士");
        initView();
        getAllLines();
    }

    private void initView() {
        bodyContainer = findViewById(R.id.body_container);

        bodyContainer.setLayoutManager(new LinearLayoutManager(BusActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 40;
                outRect.bottom = 40;

            }
        });
        gson = MyApplication.gson;
    }

    /**
     * @Create 2021/9/16 11:20
     * @Role 获取全部线路
     * @Param
     */
    private void getAllLines() {
        final String url = ConnectInfo.getUrl(ConnectInfo.BUS_LINE_LIST, "");

        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                BusLinesBean busLinesBean = gson.fromJson(response.body().string(), BusLinesBean.class);
                final List<BusLinesBean.RowsBean> rows = busLinesBean.rows;

                final List<List<BusStopBean.RowsBean>> allStops = new ArrayList<>();

                /**
                 * @Create 2021/9/16 11:21
                 * @Role 获取线路详情
                 * @Param
                 */

                for (int i = 0; i < rows.size(); i++) {
                    String stopUrl = ConnectInfo.getUrl(ConnectInfo.BUS_STOP_LIST, "?linesId=" + rows.get(i).getId());
                    OkHttpUtil.doGet(stopUrl, "", new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            BusStopBean busStopBean = gson.fromJson(response.body().string(), BusStopBean.class);
                            final List<BusStopBean.RowsBean> stopBeanRows = busStopBean.getRows();
                            allStops.add(stopBeanRows);

                            //设置适配器
                            final BusLinesAdapter adapter = new BusLinesAdapter(BusActivity.this, rows, allStops);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (rows.size() > 0) {
                                        bodyContainer.setAdapter(adapter);
                                    }
                                }
                            });
                        }
                    });
                }

            }
        });
    }


}