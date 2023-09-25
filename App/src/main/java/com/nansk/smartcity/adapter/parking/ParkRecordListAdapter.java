package com.nansk.smartcity.adapter.parking;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 14:59
 * @Description 停车记录列表适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.park.ParkRecordListBean;

import java.util.List;

public class ParkRecordListAdapter extends RecyclerView.Adapter<ParkRecordListAdapter.BodyViewHolder> {
    private Context context;
    private List<ParkRecordListBean.RowsBean> data;

    public ParkRecordListAdapter(Context context, List<ParkRecordListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_park_record_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        ParkRecordListBean.RowsBean bean = data.get(position);
        holder.plateNumTv.setText(bean.getPlateNumber());
        holder.monetaryTv.setText("消费金额："+bean.getMonetary()+"元");
        holder.parkNoTv.setText(bean.getParkNo());
        holder.entryTimeTv.setText(bean.getEntryTime());
        holder.outTimeTv.setText(bean.getOutTime());
        holder.parkNameTv.setText(bean.getParkName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView plateNumTv;
        private TextView monetaryTv;
        private TextView parkNoTv;
        private TextView entryTimeTv;
        private TextView outTimeTv;
        private TextView parkNameTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            plateNumTv = (TextView) itemView.findViewById(R.id.plateNum_tv);
            monetaryTv = (TextView) itemView.findViewById(R.id.monetary_tv);
            parkNoTv = (TextView) itemView.findViewById(R.id.parkNo_tv);
            entryTimeTv = (TextView) itemView.findViewById(R.id.entryTime_tv);
            outTimeTv = (TextView) itemView.findViewById(R.id.outTime_tv);
            parkNameTv = (TextView) itemView.findViewById(R.id.parkName_tv);
        }
    }
}
