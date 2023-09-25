package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 10:36
 * @description 违章撤销进度列表适配器
 */

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.traffic.TrafficAppleListBean;
import com.nansk.smartcity.beans.traffic.TrafficIllegalDetailsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TrafficCancelProgressListAdapter extends RecyclerView.Adapter<TrafficCancelProgressListAdapter.BodyViewHolder> {
    private Context context;
    private List<TrafficAppleListBean.RowsBean> data;
    String defaultValue = "暂无数据";

    private Handler handler;

    public TrafficCancelProgressListAdapter(Context context, List<TrafficAppleListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
        handler = new Handler();
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_cancel_progress, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        TrafficAppleListBean.RowsBean bean = data.get(position);

        holder.noTv.setText(SystemUtils.getValue(bean.getNo(),defaultValue));
        holder.applyDateTv.setText(SystemUtils.getValue(bean.getApplyDate(),defaultValue));

        holder.stateTv.setText(SystemUtils.getValue(bean.getStatus(),defaultValue));

        //根据违章id查询处罚详情
        getIllegalDetails(bean.getIllegalId(),holder);
    }

    private void getIllegalDetails(int id, final BodyViewHolder holder){
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, id);
        OkHttpUtil.doGet(url, MyApplication.getToken(context), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficIllegalDetailsBean detailsBean = MyApplication.gson.fromJson(response.body().string(), TrafficIllegalDetailsBean.class);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (detailsBean.getCode() == 200) {
                            TrafficIllegalDetailsBean.DataBean data = detailsBean.getData();
                            holder.illegalAddressTv.setText(SystemUtils.getValue(data.getIllegalSites(),defaultValue));
                            holder.codeTv.setText(SystemUtils.getValue(data.getNoticeNo(),defaultValue));
                            holder.performDateTv.setText(SystemUtils.getValue(data.getPerformDate(),defaultValue));
                            holder.illegalEvenTv.setText(SystemUtils.getValue(data.getTrafficOffence(),defaultValue));
                        } else {
                            Toast.makeText(context, detailsBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView noTv;
        private TextView applyDateTv;
        private TextView codeTv;
        private TextView illegalEvenTv;
        private TextView illegalAddressTv;
        private TextView performDateTv;
        private TextView stateTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            noTv = (TextView) itemView.findViewById(R.id.no_tv);
            applyDateTv = (TextView) itemView.findViewById(R.id.applyDate_tv);
            codeTv = (TextView) itemView.findViewById(R.id.code_tv);
            illegalEvenTv = (TextView) itemView.findViewById(R.id.illegalEven_tv);
            illegalAddressTv = (TextView) itemView.findViewById(R.id.illegalAddress_tv);
            performDateTv = (TextView) itemView.findViewById(R.id.performDate_tv);
            stateTv = (TextView) itemView.findViewById(R.id.state_tv);
        }
    }
}
