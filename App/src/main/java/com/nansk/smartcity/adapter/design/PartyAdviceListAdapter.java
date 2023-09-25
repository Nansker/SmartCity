package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 10:41
 * @description
 */

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.party.PartyAdviceBean;

import java.util.List;

public class PartyAdviceListAdapter extends RecyclerView.Adapter<PartyAdviceListAdapter.BodyViewHolder> {
    private Context context;
    private List<PartyAdviceBean> data;

    public PartyAdviceListAdapter(Context context) {
        this.context = context;
    }

    public List<PartyAdviceBean> getData() {
        return data;
    }

    public void setData(List<PartyAdviceBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_party_advice, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        PartyAdviceBean bean = data.get(position);
        holder.nameTv.setText(bean.getName());
        holder.titleTv.setText(bean.getTitle());
        holder.contentTv.setText(bean.getContent());
        holder.dateTv.setText(bean.getDate());
        Glide.with(context).load(R.drawable.user_icon).into(holder.iconIv);
}

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private ImageView iconIv;
        private TextView nameTv;
        private TextView dateTv;
        private TextView contentTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            iconIv = (ImageView) itemView.findViewById(R.id.icon_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
