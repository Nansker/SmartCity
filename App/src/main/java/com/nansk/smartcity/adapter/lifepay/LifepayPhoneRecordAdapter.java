package com.nansk.smartcity.adapter.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 10:37
 * @Description 手机缴费历史适配器
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
import com.nansk.smartcity.beans.lifepay.LifepayPhoneRecordBean;

import java.util.List;

public class LifepayPhoneRecordAdapter extends RecyclerView.Adapter<LifepayPhoneRecordAdapter.BodyViewHolder> {
    private Context context;
    private List<LifepayPhoneRecordBean.RowsBean> data;

    public LifepayPhoneRecordAdapter(Context context, List<LifepayPhoneRecordBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lifepay_phone_record, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        LifepayPhoneRecordBean.RowsBean bean = data.get(position);
        holder.phonenumberTv.setText(bean.getPhonenumber());
        holder.paymentTypeTv.setText(bean.getPaymentType());
        holder.rechargeTimeTv.setText(bean.getRechargeTime());
        holder.amountTv.setText(bean.getRechargeAmount().toString()+"元");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView phonenumberTv;
        private TextView rechargeTimeTv;
        private TextView paymentTypeTv;
        private TextView amountTv;
        private ImageView imageIv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            phonenumberTv = (TextView) itemView.findViewById(R.id.phonenumber_tv);
            rechargeTimeTv = (TextView) itemView.findViewById(R.id.rechargeTime_tv);
            paymentTypeTv = (TextView) itemView.findViewById(R.id.paymentType_tv);
            amountTv = (TextView) itemView.findViewById(R.id.amount_tv);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
        }
    }
}
