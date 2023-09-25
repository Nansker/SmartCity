package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.press.PressDetailsActivity;
import com.nansk.smartcity.adapter.traffic.TrafficPressListAdapter;
import com.nansk.smartcity.beans.press.PressListBean;
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
 * @create 2021/10/09 13:04
 * @description 资讯中心
 */

public class TrafficServicePressFragment extends Fragment {
    private View view;
    private RecyclerView bodyContainer;
    private TextView tipsTv;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_traffic_service_press, container, false);
        initView();
        fillData();
        return view;
    }

    /**
     * @Create 2021/10/9 14:18
     * @Role 获取新闻
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.PRESS_LIST, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PressListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), PressListBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            tipsTv.setVisibility(View.GONE);
                            List<PressListBean.RowsBean> rows = jsonObj.getRows();
                            TrafficPressListAdapter adapter = new TrafficPressListAdapter(getContext(), rows);
                            bodyContainer.setAdapter(adapter);
                            adapter.setOnItemCallBack(new TrafficPressListAdapter.OnItemCallBack() {
                                @Override
                                public void OnItemCallBack(int position, PressListBean.RowsBean obj) {
                                    Intent intent = new Intent(getContext(), PressDetailsActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("pressId",obj.getId());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        } else{
                            tipsTv.setText(jsonObj.getMsg());
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        bodyContainer = (RecyclerView) view.findViewById(R.id.body_container);
        tipsTv = (TextView) view.findViewById(R.id.tips_tv);

        bodyContainer.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
                outRect.bottom = 15;
                outRect.left = 15;
                outRect.right = 15;
            }
        });

        handler = new Handler();
    }
}