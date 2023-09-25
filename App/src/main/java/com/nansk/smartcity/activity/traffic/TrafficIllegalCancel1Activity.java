package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.traffic.TrafficUntreatedListAdapter;
import com.nansk.smartcity.beans.UserInfoBean;
import com.nansk.smartcity.beans.traffic.TrafficIllegalListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.UserInfoUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/07 16:51
 * @description 撤销申请
 */

public class TrafficIllegalCancel1Activity extends BaseActivity {

    private TextView idCardTv;
    private TextView userNameTv;
    private TextView deductMarksTv;
    private TextView tipsTv;
    private RecyclerView bodyContainer;
    private TextView disposeTv;
    private TextView cancelAppleBtn;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_illegal_cancel1);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("违法处理撤销申请");
        setToolBarRightButton(true, "进度跟踪", new MyCallBack() {
            @Override
            public void CallBack() {
                Intent intent = new Intent(TrafficIllegalCancel1Activity.this, TrafficCancelProgressActivity.class);
                startActivity(intent);
            }
        });
        initView();
        initObject();
        fillData();
        getUntreatedList();
    }

    /**
     * @Create 2021/10/8 11:39
     * @Role 未处理的违章信息列表
     * @Param
     */
    private void getUntreatedList() {

        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "list/?disposeState=未处理");
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficIllegalListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TrafficIllegalListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<TrafficIllegalListBean.RowsBean> rows = jsonObj.getRows();
                            if (jsonObj.getTotal() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                disposeTv.setText("已选择记录");
                                cancelAppleBtn.setBackgroundResource(R.color.theme_traffic);

                                final TrafficUntreatedListAdapter adapter = new TrafficUntreatedListAdapter(TrafficIllegalCancel1Activity.this, rows);
                                adapter.setOnItemCallback(new TrafficUntreatedListAdapter.OnItemCallBack() {
                                    @Override
                                    public void OnItemCallBack(int position, final TrafficIllegalListBean.RowsBean obj) {
                                        adapter.setPosition(position);
                                        adapter.notifyDataSetChanged();

                                        cancelAppleBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                cancelApple(obj);
                                            }
                                        });
                                    }
                                });

                                bodyContainer.setAdapter(adapter);

                            } else {
                                tipsTv.setText("暂无未处理的违章记录");
                            }
                        } else {
                            tipsTv.setText(jsonObj.getMsg());
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/8 19:24
     * @Role 撤销申请
     * @Param
     */
    private void cancelApple(TrafficIllegalListBean.RowsBean obj) {
        Intent intent = new Intent(TrafficIllegalCancel1Activity.this, TrafficIllegalCancel2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("illegalId",obj.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * @Create 2021/10/8 11:35
     * @Role 获取个人信息
     * @Param
     */
    private void fillData() {
        UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(TrafficIllegalCancel1Activity.this);

        idCardTv.setText(userInfo.getIdCard().substring(0, 2) + "********" + userInfo.getIdCard().substring(userInfo.getIdCard().length() - 4));
        userNameTv.setText(userInfo.getNickName());
    }

    /**
     * @Create 2021/10/7 17:18
     * @Role
     * @Param
     */
    private void initObject() {

        token = MyApplication.getToken(this);

        bodyContainer.setLayoutManager(new LinearLayoutManager(TrafficIllegalCancel1Activity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
            }
        });

    }

    private void initView() {

        idCardTv = (TextView) findViewById(R.id.idCard_tv);
        userNameTv = (TextView) findViewById(R.id.userName_tv);
        deductMarksTv = (TextView) findViewById(R.id.deductMarks_tv);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        disposeTv = (TextView) findViewById(R.id.dispose_tv);
        cancelAppleBtn = (TextView) findViewById(R.id.cancelApple_btn);
    }
}