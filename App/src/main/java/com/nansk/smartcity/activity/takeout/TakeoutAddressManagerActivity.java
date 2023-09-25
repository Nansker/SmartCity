package com.nansk.smartcity.activity.takeout;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.takeout.TakeoutAddressAdapter;
import com.nansk.smartcity.beans.takeout.TakeoutAddressListBean;
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
 * @Create 2021/09/23 14:25
 * @Description 外卖-地址管理
 */

public class TakeoutAddressManagerActivity extends BaseActivity {

    private RecyclerView bodyContainer;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_address_manager);
        setToolBarBackground("#FFC107",0);
        setToolBarTitle("地址管理");
        setToolBarRightButton(true, "新增地址", new MyCallBack() {
            @Override
            public void CallBack() {
                Intent intent = new Intent(TakeoutAddressManagerActivity.this, TakeoutNewAddressActivity.class);
                startActivity(intent);
            }
        });
        initView();
        fillData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        fillData();
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

        bodyContainer.setLayoutManager(new LinearLayoutManager(TakeoutAddressManagerActivity.this));
        bodyContainer.addItemDecoration(new DividerItemDecoration(TakeoutAddressManagerActivity.this,DividerItemDecoration.VERTICAL));

        gson = new Gson();

    }

    /**
     * @Create 2021/9/23 14:36
     * @Role 获取收货地址
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ADDRESS_LIST, "");
        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeoutAddressListBean jsonObj = gson.fromJson(response.body().string(), TakeoutAddressListBean.class);
                if (jsonObj.code == 200){
                    List<TakeoutAddressListBean.DataBean> data = jsonObj.getData();
                    if (data.size() > 0){
                        final TakeoutAddressAdapter adapter = new TakeoutAddressAdapter(TakeoutAddressManagerActivity.this, data);
                        adapter.OnItemCallBack(new TakeoutAddressAdapter.OnItemCallBack() {
                            @Override
                            public void OnItemCallBack(int position, TakeoutAddressListBean.DataBean obj) {

                            }
                        });

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bodyContainer.setAdapter(adapter);
                            }
                        });
                    }
                }
            }
        });

    }

}