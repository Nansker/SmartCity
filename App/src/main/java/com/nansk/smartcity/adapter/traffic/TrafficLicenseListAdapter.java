package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/05 20:45
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.traffic.TrafficLicenseUserBean;

import java.util.List;

public class TrafficLicenseListAdapter extends RecyclerView.Adapter<TrafficLicenseListAdapter.BodyViewHolder> {
    private Context context;
    private List<TrafficLicenseUserBean.DataBean> data;
    private OnItemCallBack onItemCallBack;
    public TrafficLicenseListAdapter(Context context, List<TrafficLicenseUserBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_license, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final TrafficLicenseUserBean.DataBean bean = data.get(position);
        holder.idCardTv.setText(getValue(bean.getIdCard()));
        holder.userNameTv.setText(getValue(bean.getUserName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String getValue(String value){
        if (value != "" && value != null){
            return value;
        }
        return "暂无数据";
    }

    public interface OnItemCallBack{
        void OnItemCallBack(int position,TrafficLicenseUserBean.DataBean obj);
    }

    public void OnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView idCardTv;
        private TextView userNameTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            idCardTv = (TextView) itemView.findViewById(R.id.idCard_tv);
            userNameTv = (TextView) itemView.findViewById(R.id.userName_tv);
        }
    }
}
