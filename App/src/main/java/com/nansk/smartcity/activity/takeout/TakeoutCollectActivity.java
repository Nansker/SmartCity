package com.nansk.smartcity.activity.takeout;

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
import com.nansk.smartcity.adapter.takeout.TakeoutCollectListAdapter;
import com.nansk.smartcity.beans.takeout.TakeoutCollectListBean;
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
 * @Create 2021/09/24 20:36
 * @Description  外卖-收藏列表
 */

public class TakeoutCollectActivity extends BaseActivity {

    private TextView tipsTv;
    private RecyclerView bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_collect);
        setToolBarBackground("#FFC107", 0);
        setToolBarTitle("我的收藏");
        initView();
        fillData();
    }

    private void initView() {

        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        bodyContainer.setLayoutManager(new LinearLayoutManager(TakeoutCollectActivity.this));

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 20;
                outRect.left = 20;
                outRect.top = 10;
                outRect.bottom = 10;
            }
        });
    }

    /**
     * @Create 2021/9/24 20:50
     * @Role 获取收藏列表
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_COLLECT_LIST, "");
        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TakeoutCollectListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TakeoutCollectListBean.class);
                if (jsonObj.getCode() == 200) {
                    final List<TakeoutCollectListBean.RowsBean> rows = jsonObj.getRows();
                    final TakeoutCollectListAdapter adapter = new TakeoutCollectListAdapter(TakeoutCollectActivity.this, rows);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                bodyContainer.setAdapter(adapter);
                            } else {
                                Toast.makeText(TakeoutCollectActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}