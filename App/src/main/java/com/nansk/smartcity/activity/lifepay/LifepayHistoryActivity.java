package com.nansk.smartcity.activity.lifepay;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.lifepay.LifepayPhoneRecordAdapter;
import com.nansk.smartcity.adapter.lifepay.LifepayRecordAdapter;
import com.nansk.smartcity.beans.lifepay.LifepayPhoneRecordBean;
import com.nansk.smartcity.beans.lifepay.LifepayRecordBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 18:24
 * @Description
 */

public class LifepayHistoryActivity extends BaseActivity {
    private int categoryId;

    private RecyclerView bodyContainer;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_lifepay_history);
        setToolBarTitle("缴费记录");
        initView();

        if (categoryId != 1) {
            fillData();
            //手机缴费记录
        } else {
            fillPhoneData();
        }


    }


    private void initView() {

        tipsTv = (TextView) findViewById(R.id.tips_tv);

        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", 0);

        if (categoryId == 0) {
            finish();
            Toast.makeText(this, "获取数据失败！", Toast.LENGTH_SHORT).show();
        }

        bodyContainer.setLayoutManager(new LinearLayoutManager(LifepayHistoryActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 40;
                outRect.right = 40;
                outRect.top = 20;
                outRect.bottom = 20;

            }
        });

    }

    /**
     * @Create 2021/9/28 18:43
     * @Role 获取数据，水、电，燃气
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_RECHARGE_LIST, "?categoryId=" + categoryId);
        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LifepayRecordBean jsonObj = MyApplication.gson.fromJson(response.body().string(), LifepayRecordBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<LifepayRecordBean.RowsBean> rows = jsonObj.getRows();
                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                LifepayRecordAdapter adapter = new LifepayRecordAdapter(LifepayHistoryActivity.this, rows);
                                bodyContainer.setAdapter(adapter);
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/9/29 10:27
     * @Role 获取手机缴费记录
     * @Param
     */
    private void fillPhoneData() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_PHONE_RECORD, "");
        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LifepayPhoneRecordBean jsonObj = MyApplication.gson.fromJson(response.body().string(), LifepayPhoneRecordBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<LifepayPhoneRecordBean.RowsBean> rows = jsonObj.getRows();
                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                LifepayPhoneRecordAdapter adapter = new LifepayPhoneRecordAdapter(LifepayHistoryActivity.this, rows);
                                bodyContainer.setAdapter(adapter);
                            }
                        }
                    }
                });
            }
        });
    }

}