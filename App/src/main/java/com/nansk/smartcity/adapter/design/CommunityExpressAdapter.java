package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 21:00
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
import com.nansk.smartcity.beans.community.CommunityExpressBean;

import java.util.List;


public class CommunityExpressAdapter extends RecyclerView.Adapter<CommunityExpressAdapter.BodyViewHolder> {
    private Context context;
    private List<CommunityExpressBean> data;


    public CommunityExpressAdapter(Context context) {
        this.context = context;
    }

    public List<CommunityExpressBean> getData() {
        return data;
    }

    public void setData(List<CommunityExpressBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_community_express, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        CommunityExpressBean bean = data.get(position);
        holder.statusTv.setText(bean.getStatus());
        holder.paramsTv.setText(bean.getParams());
        holder.dateTv.setText(bean.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView statusTv;
        private TextView paramsTv;
        private TextView dateTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            statusTv = (TextView) itemView.findViewById(R.id.status_tv);
            paramsTv = (TextView) itemView.findViewById(R.id.params_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
        }
    }
}
