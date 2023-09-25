package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 15:59
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
import com.nansk.smartcity.beans.protection.ProtectionRecyclingSubmitBean;
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;


public class ProtectionRecyclingRecordAdapter extends RecyclerView.Adapter<ProtectionRecyclingRecordAdapter.BodyViewHolder> {
    private Context context;
    private List<ProtectionRecyclingSubmitBean> data;


    public ProtectionRecyclingRecordAdapter(Context context, List<ProtectionRecyclingSubmitBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_protection_record, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        ProtectionRecyclingSubmitBean bean = data.get(position);
        holder.timeTv.setText(bean.getTime());
        holder.nameTv.setText(bean.getAddressObj().getName());
        holder.addressTv.setText(bean.getAddressObj().getAddress() + bean.getAddressObj().getAddressNo());
        holder.classNameTv.setText(bean.getClassX());
        holder.paramTv.setText(SystemUtils.getValue(bean.getParams(),""));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTv;
        private TextView classNameTv;
        private TextView addressTv;
        private TextView nameTv;
        private TextView paramTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
            classNameTv = (TextView) itemView.findViewById(R.id.className_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            paramTv = (TextView) itemView.findViewById(R.id.param_tv);
        }
    }
}
