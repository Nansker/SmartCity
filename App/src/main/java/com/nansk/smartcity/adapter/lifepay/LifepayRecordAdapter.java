package com.nansk.smartcity.adapter.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 18:53
 * @Description 水电，燃气缴费记录适配器器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.lifepay.LifepayRecordBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class LifepayRecordAdapter extends RecyclerView.Adapter<LifepayRecordAdapter.BodyViewHolder> {
    private Context context;
    private List<LifepayRecordBean.RowsBean> data;

    public LifepayRecordAdapter(Context context, List<LifepayRecordBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public LifepayRecordAdapter.BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lifepay_record, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LifepayRecordAdapter.BodyViewHolder holder, int position) {
        LifepayRecordBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getImgUrl()).into(holder.imageIv);
        holder.amountTv.setText(bean.getAmount()+"元");
        holder.rechargeTimeTv.setText(bean.getRechargeTime());
        holder.paymentNoTv.setText(bean.getPaymentNo());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView rechargeTimeTv;
        private TextView paymentNoTv;
        private TextView amountTv;
        private ImageView imageIv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            rechargeTimeTv = (TextView) itemView.findViewById(R.id.rechargeTime_tv);
            paymentNoTv = (TextView) itemView.findViewById(R.id.paymentNo_tv);
            amountTv = (TextView) itemView.findViewById(R.id.amount_tv);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
        }
    }
}
