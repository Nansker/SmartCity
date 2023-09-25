package com.nansk.smartcity.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.BalanceRecordListAdapter;
import com.nansk.smartcity.beans.BalanceRecordBean;
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
 * @create 2021/10/12 20:26
 * @description 交易记录
 */

public class BalanceRecordActivity extends BaseActivity {

    private RecyclerView bodyContainer;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_balance_record);
        setToolBarTitle("交易明细");
        initView();
        fillData();
    }

    /**
     * @Create 2021/10/12 20:30
     * @Role 获取记录
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.BALANCE, "list");
        OkHttpUtil.doGet(url, MyApplication.getToken(BalanceRecordActivity.this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final BalanceRecordBean jsonObj = MyApplication.gson.fromJson(response.body().string(), BalanceRecordBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            if (jsonObj.getTotal()>0){
                                tipsTv.setVisibility(View.GONE);
                                List<BalanceRecordBean.RowsBean> rows = jsonObj.getRows();
                                BalanceRecordListAdapter adapter = new BalanceRecordListAdapter(BalanceRecordActivity.this, rows);
                                bodyContainer.setAdapter(adapter);
                            }else {
                                tipsTv.setText("暂无交易记录");
                            }

                        }else {
                            Log.e("city",jsonObj.getMsg());
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        tipsTv = (TextView) findViewById(R.id.tips_tv);


        bodyContainer.setLayoutManager(new LinearLayoutManager(BalanceRecordActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 15;
                outRect.top = 15;
                outRect.left = 30;
                outRect.right = 30;
            }
        });

    }
}