package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/07 13:46
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.traffic.TrafficNoticeListBean;

import java.util.List;

public class TrafficNoticeListAdapter extends RecyclerView.Adapter<TrafficNoticeListAdapter.BodyViewHolder> {
    private Context context;
    private List<TrafficNoticeListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;

    public TrafficNoticeListAdapter(Context context, List<TrafficNoticeListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TrafficNoticeListAdapter.BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_illegal_process, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficNoticeListAdapter.BodyViewHolder holder, final int position) {
        final TrafficNoticeListBean.RowsBean bean = data.get(position);
        holder.trafficOffenceTv.setText(bean.getIllegalEven());
        holder.illegalSitesTv.setText(bean.getIllegalAddress());
        holder.badTimeTv.setText(bean.getIllegalDate());
        holder.moneyTv.setText("罚款"+bean.getMoney()+"元");
        holder.deductMarksTv.setText("记"+bean.getDeductMarks()+"分");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    public interface OnItemCallBack{
        void OnItemCallBack(int position,TrafficNoticeListBean.RowsBean obj);
    }
    public void setOnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder{
        private TextView trafficOffenceTv;
        private TextView illegalSitesTv;
        private TextView badTimeTv;
        private TextView moneyTv;
        private TextView deductMarksTv;
        private Button disposeBtn;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            trafficOffenceTv = (TextView) itemView.findViewById(R.id.trafficOffence_tv);
            illegalSitesTv = (TextView) itemView.findViewById(R.id.illegalSites_tv);
            badTimeTv = (TextView) itemView.findViewById(R.id.badTime_tv);
            moneyTv = (TextView) itemView.findViewById(R.id.money_tv);
            deductMarksTv = (TextView) itemView.findViewById(R.id.deductMarks_tv);
            disposeBtn = (Button) itemView.findViewById(R.id.dispose_btn);

           disposeBtn.setVisibility(View.GONE);
        }
}
}
