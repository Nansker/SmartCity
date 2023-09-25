package com.nansk.smartcity.activity.job;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.job.JobDeliverListAdapter;
import com.nansk.smartcity.beans.job.JobDeliveryRecordListBean;
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
 * @Create 2021/09/30 17:40
 * @Description 投递记录
 */

public class DeliveryRecordFragment extends Fragment {
    private View view;

    private TextView tipsTv;
    private RecyclerView bodyContainer;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_job_delivery_record, null);
        initView();
        fillData();
        return view;
    }


    private void initView() {
        tipsTv = (TextView) view.findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) view.findViewById(R.id.body_container);

        bodyContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 30;
                outRect.right = 30;
                outRect.top = 15;
                outRect.bottom = 15;
            }
        });

        handler = new Handler();

    }

    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.JOB_DELIVER, "");
        OkHttpUtil.doGet(url, MyApplication.getToken(getContext()), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) {
                JobDeliveryRecordListBean jsonObj = null;
                try {
                    jsonObj = MyApplication.gson.fromJson(response.body().string(), JobDeliveryRecordListBean.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final JobDeliveryRecordListBean finalJsonObj = jsonObj;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (finalJsonObj.getCode() == 200) {
                            if (finalJsonObj.getTotal() > 0) {
                                final List<JobDeliveryRecordListBean.RowsBean> rows = finalJsonObj.getRows();
                                tipsTv.setVisibility(View.GONE);
                                JobDeliverListAdapter adapter = new JobDeliverListAdapter(getContext(), rows);
                                bodyContainer.setAdapter(adapter);

                            } else {
                                tipsTv.setText("尊敬的牛人~\\n您尚未投递过简历");
                            }
                        }
                    }
                });
            }
        });
    }
}