package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 20:49
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
import com.nansk.smartcity.beans.support.SupportMyHelpBean;

import java.util.List;

public class SupportMyHelpAdapter extends RecyclerView.Adapter<SupportMyHelpAdapter.BodyViewHolder> {
    private Context context;
    private List<SupportMyHelpBean> data;

    public SupportMyHelpAdapter(Context context) {
        this.context = context;
    }

    public List<SupportMyHelpBean> getData() {
        return data;
    }

    public void setData(List<SupportMyHelpBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_support_help, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        SupportMyHelpBean bean = data.get(position);
        holder.nameTv.setText(bean.getName());
        holder.dateTv.setText(bean.getDate());
        holder.addressTv.setText(bean.getAddress());
        holder.introductionTv.setText(bean.getIntroduction());
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView addressTv;
        private TextView dateTv;
        private TextView introductionTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            introductionTv = (TextView) itemView.findViewById(R.id.introduction_tv);
        }
    }
}
