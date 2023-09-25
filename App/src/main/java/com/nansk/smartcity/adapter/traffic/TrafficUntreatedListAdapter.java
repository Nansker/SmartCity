package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/08 11:52
 * @description 撤销申请列表适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.traffic.TrafficIllegalListBean;

import java.util.List;

public class TrafficUntreatedListAdapter extends RecyclerView.Adapter<TrafficUntreatedListAdapter.BodyViewHolder> {
    private Context context;
    private List<TrafficIllegalListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;

    public TrafficUntreatedListAdapter(Context context, List<TrafficIllegalListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_untreated, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BodyViewHolder holder, final int position) {
        final TrafficIllegalListBean.RowsBean bean = data.get(position);

        holder.illegalSitesTv.setText(getValue(bean.getIllegalSites()));
        holder.trafficOffenceTv.setText(getValue(bean.getTrafficOffence()));
        holder.moneyTv.setText(getValue(bean.getMoney())+"元");
        holder.deductMarksTv.setText(getValue(bean.getDeductMarks()+"分"));
        holder.disposeStateTv.setText(getValue(bean.getDisposeState()));

        if (getPosition() == position){
            holder.selectIv.setImageResource(R.drawable.group345);
        }else {
            holder.selectIv.setImageResource(R.drawable.group344);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position, bean);
            }
        });

    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String getValue(String value) {
       if (value != null && value != ""){
           return value;
       }
       return "暂无数据";
    }

    public interface OnItemCallBack {
        void OnItemCallBack(int position, TrafficIllegalListBean.RowsBean obj);
    }

    public void setOnItemCallback(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView selectIv;
        private TextView illegalSitesTv;
        private TextView trafficOffenceTv;
        private TextView moneyTv;
        private TextView deductMarksTv;
        private TextView disposeStateTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            selectIv = (ImageView) itemView.findViewById(R.id.select_iv);
            illegalSitesTv = (TextView) itemView.findViewById(R.id.illegalSites_tv);
            trafficOffenceTv = (TextView) itemView.findViewById(R.id.trafficOffence_tv);
            moneyTv = (TextView) itemView.findViewById(R.id.money_tv);
            deductMarksTv = (TextView) itemView.findViewById(R.id.deductMarks_tv);
            disposeStateTv = (TextView) itemView.findViewById(R.id.disposeState_tv);
        }
    }
}
