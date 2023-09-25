package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 15:30
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
import com.nansk.smartcity.beans.metro.MetroNoticeBean;
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;

public class MetroNoticeListAdapter extends RecyclerView.Adapter<MetroNoticeListAdapter.BodyViewHolder> {
    private Context context;
    private List<MetroNoticeBean.RowsBean> data;

    public MetroNoticeListAdapter(Context context, List<MetroNoticeBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metro_notice, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        MetroNoticeBean.RowsBean bean = data.get(position);
        holder.titleTv.setText(SystemUtils.getValue(bean.getTitle(), ""));
        holder.timeTv.setText(SystemUtils.getValue(bean.getCreateTime(), ""));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView timeTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
        }
    }
}

