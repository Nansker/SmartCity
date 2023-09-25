package com.nansk.smartcity.adapter.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 17:32
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
import com.nansk.smartcity.beans.lifepay.OftenUserPhoneBean;

import java.util.List;

public class OftenUserPhoneAdapter extends RecyclerView.Adapter<OftenUserPhoneAdapter.BodyViewHolder> {
    private Context context;
    private List<OftenUserPhoneBean> data;
    private OnItemCallBack onItemCallBack;

    public OftenUserPhoneAdapter(Context context, List<OftenUserPhoneBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_often_user_phone, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final OftenUserPhoneBean bean = data.get(position);
        holder.operatorTv.setText(bean.getOperator());
        holder.phonenumberTvTv.setText(bean.getPhoneNumber());
        holder.timeTv.setText(bean.getCreateTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(bean);
            }
        });
    }

    public interface OnItemCallBack{
        void OnItemCallBack(OftenUserPhoneBean obj);
    }

    public void OnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView phonenumberTvTv;
        private TextView operatorTv;
        private TextView timeTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            phonenumberTvTv = (TextView) itemView.findViewById(R.id.phonenumber_tv_tv);
            operatorTv = (TextView) itemView.findViewById(R.id.operator_tv);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
        }
    }
}
