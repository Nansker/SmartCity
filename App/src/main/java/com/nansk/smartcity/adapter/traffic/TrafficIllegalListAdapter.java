package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 15:12
 * @description 违章信息列表适配器
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.traffic.TrafficIllegalDetailsActivity;
import com.nansk.smartcity.beans.traffic.TrafficIllegalListBean;

import java.util.List;

public class TrafficIllegalListAdapter extends RecyclerView.Adapter<TrafficIllegalListAdapter.BodyViewHolder> {
    private Context context;
    private List<TrafficIllegalListBean.RowsBean> data;

    public TrafficIllegalListAdapter(Context context, List<TrafficIllegalListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_illegal, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final TrafficIllegalListBean.RowsBean bean = data.get(position);
        holder.badTimeTv.setText(getValue(bean.getBadTime()));
        holder.licencePlateTv.setText(getValue(bean.getLicencePlate()));
        holder.illegalSitesTv.setText(getValue(bean.getIllegalSites()));
        holder.trafficOffenceTv.setText(getValue(bean.getTrafficOffence()));
        holder.moneyTv.setText(getValue(bean.getMoney()+"元"));
        holder.deductMarksTv.setText(getValue(bean.getDeductMarks()+"分"));
        holder.disposeStateTv.setText(getValue(bean.getDisposeState()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrafficIllegalDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",bean.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String getValue(String value){
        if (value != null && value != ""){
            return value;
        }
        return "暂无数据";
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView badTimeTv;
        private TextView licencePlateTv;
        private TextView illegalSitesTv;
        private TextView trafficOffenceTv;
        private TextView moneyTv;
        private TextView deductMarksTv;
        private TextView disposeStateTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            badTimeTv = (TextView) itemView.findViewById(R.id.badTime_tv);
            licencePlateTv = (TextView) itemView.findViewById(R.id.licencePlate_tv);
            illegalSitesTv = (TextView) itemView.findViewById(R.id.illegalSites_tv);
            trafficOffenceTv = (TextView) itemView.findViewById(R.id.trafficOffence_tv);
            moneyTv = (TextView) itemView.findViewById(R.id.money_tv);
            deductMarksTv = (TextView) itemView.findViewById(R.id.deductMarks_tv);
            disposeStateTv = (TextView) itemView.findViewById(R.id.disposeState_tv);
        }
    }
}
