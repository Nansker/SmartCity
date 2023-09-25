package com.nansk.smartcity.adapter.hospital;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 21:11
 * @Description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.hospital.HospitalOrdersBean;

import java.util.List;

public class HospitalHistoryAdapter extends RecyclerView.Adapter<HospitalHistoryAdapter.BodyViewHolder> {
    private Context context;
    private List<HospitalOrdersBean.RowsBean> data;

    public HospitalHistoryAdapter(Context context, List<HospitalOrdersBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hospital_history, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        HospitalOrdersBean.RowsBean rowsBean = data.get(position);
        holder.orderNoTv.setText(rowsBean.getOrderNo());
        holder.nameTv.setText(rowsBean.getPatientName());

        if (rowsBean.getType() == null){
            holder.typeTv.setText("");
        }else {
            if (rowsBean.getType().equals("1")) {
                holder.typeTv.setText("专家号");
            } else if (rowsBean.getType().equals("2")) {
                holder.typeTv.setText("普通号");
            }
        }

        holder.categoryNameTv.setText(rowsBean.getCategoryName());
        if (rowsBean.getMoney() == null){
            holder.moneyTv.setText("");
        }else {
            holder.moneyTv.setText(rowsBean.getMoney().toString()+"");
        }

        holder.reserveTimeTv.setText(rowsBean.getReserveTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView orderNoTv;
        private TextView nameTv;
        private TextView categoryNameTv;
        private TextView typeTv;
        private TextView moneyTv;
        private TextView reserveTimeTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNoTv = (TextView) itemView.findViewById(R.id.orderNo_tv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            categoryNameTv = (TextView) itemView.findViewById(R.id.categoryName_tv);
            typeTv = (TextView) itemView.findViewById(R.id.type_tv);
            moneyTv = (TextView) itemView.findViewById(R.id.money_tv);
            reserveTimeTv = (TextView) itemView.findViewById(R.id.reserveTime_tv);
        }
    }
}
