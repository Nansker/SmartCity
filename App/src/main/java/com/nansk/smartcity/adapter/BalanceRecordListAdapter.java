package com.nansk.smartcity.adapter;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 20:43
 * @description 账号交易记录表列表适配器
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.BalanceRecordBean;
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;

public class BalanceRecordListAdapter extends RecyclerView.Adapter<BalanceRecordListAdapter.BodyViewHolder> {
    private Context context;
    private List<BalanceRecordBean.RowsBean> data;


    public BalanceRecordListAdapter(Context context, List<BalanceRecordBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_balance_record, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        BalanceRecordBean.RowsBean bean = data.get(position);
        holder.eventTv.setText(SystemUtils.getValue(bean.getEvent(), "暂无数据"));
        holder.changeTypeTv.setText(bean.getChangeType());
        holder.changeAmountTv.setText(bean.getChangeAmount().toString());
        holder.changeTimeTv.setText(bean.getChangeTime());

        if (bean.getChangeType().equals("收入")){
            holder.changeTypeTv.setTextColor(Color.parseColor("#FF9800"));
        }else {
            holder.changeTypeTv.setTextColor(Color.parseColor("#999999"));
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView eventTv;
        private TextView changeTypeTv;
        private TextView changeAmountTv;
        private TextView changeTimeTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTv = (TextView) itemView.findViewById(R.id.event_tv);
            changeTypeTv = (TextView) itemView.findViewById(R.id.changeType_tv);
            changeAmountTv = (TextView) itemView.findViewById(R.id.changeAmount_tv);
            changeTimeTv = (TextView) itemView.findViewById(R.id.changeTime_tv);
        }
    }
}
