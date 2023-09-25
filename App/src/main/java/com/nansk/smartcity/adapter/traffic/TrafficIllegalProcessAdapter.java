package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 21:35
 * @description 违章处理
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.traffic.TrafficIllegalListBean;

import java.util.List;

public class TrafficIllegalProcessAdapter extends RecyclerView.Adapter<TrafficIllegalProcessAdapter.BodyViewHolder> {
    private Context context;
    private List<TrafficIllegalListBean.RowsBean> data;
    private OnItemCallBack setOnItemCallBack;

    public TrafficIllegalProcessAdapter(Context context, List<TrafficIllegalListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_illegal_process, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final TrafficIllegalListBean.RowsBean bean = data.get(position);
        holder.trafficOffenceTv.setText(bean.getTrafficOffence());
        holder.illegalSitesTv.setText(bean.getIllegalSites());
        holder.badTimeTv.setText(bean.getBadTime());
        holder.moneyTv.setText("罚款"+bean.getMoney()+"元");
        holder.deductMarksTv.setText("记"+bean.getDeductMarks()+"分");
        if (bean.getDisposeState().equals("未处理")){
            holder.disposeBtn.setVisibility(View.VISIBLE);
        }else {
            holder.disposeBtn.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemCallBack.OnItemCallBack(0,position,bean);
            }
        });

        holder.disposeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemCallBack.OnItemCallBack(1,position,bean);
            }
        });

    }

    /**
     * @Create 2021/10/7 13:13
     * @Role 0=查看详情 1=接收处理
     * @Param
     */
    public interface OnItemCallBack{
        void OnItemCallBack(int type, int position, TrafficIllegalListBean.RowsBean obj);
    }

    public void setOnItemCallBack(OnItemCallBack callBack){
        this.setOnItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
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

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(10);
            gradientDrawable.setColor(Color.parseColor("#2c65a8"));
            disposeBtn.setBackground(gradientDrawable);
        }
    }
}
