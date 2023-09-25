package com.nansk.smartcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.AllOrdersBean;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 10:33
 * @Description 全部订单适配器
 */

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.BodyViewHolder> {
    private Context context;
    private List<AllOrdersBean.RowsBean> data;

    public AllOrderAdapter(Context context, List<AllOrdersBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_allorders, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        AllOrdersBean.RowsBean rowsBean = data.get(position);
        holder.orderNoTv.setText(rowsBean.getOrderNo());
        holder.merchantsTv.setText(rowsBean.getName());
        holder.orderTypeTv.setText(rowsBean.getOrderTypeName());
        holder.orderStatusTv.setText(rowsBean.getOrderStatus());
        holder.priceTv.setText(rowsBean.getAmount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView merchantsTv;
        private TextView orderNoTv;
        private TextView orderTypeTv;
        private TextView orderStatusTv;
        private TextView priceTv;
        private Button payBtn;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            merchantsTv = (TextView) itemView.findViewById(R.id.merchants_tv);
            orderNoTv = (TextView) itemView.findViewById(R.id.orderNo_tv);
            orderTypeTv = (TextView) itemView.findViewById(R.id.orderType_tv);
            orderStatusTv = (TextView) itemView.findViewById(R.id.orderStatus_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            payBtn = (Button) itemView.findViewById(R.id.pay_btn);
        }
    }
}
