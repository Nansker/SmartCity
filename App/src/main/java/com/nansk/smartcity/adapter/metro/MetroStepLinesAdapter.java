package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 13:49
 * @description 站点详情页，列车时间列表适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroStepInfoBean;

public class MetroStepLinesAdapter extends RecyclerView.Adapter<MetroStepLinesAdapter.BodyViewHolder> {
    private Context context;
    private MetroStepInfoBean.DataBean data;


    public MetroStepLinesAdapter(Context context, MetroStepInfoBean.DataBean data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metro_step_lines, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        MetroStepInfoBean.DataBean.LineListBean bean = data.getLineList().get(position);

        holder.lineNameTv.setText(bean.getName() + "-" + data.getStepInfo().getName());
        holder.starTv.setText(bean.getFirst());
        holder.endTv.setText(bean.getEnd());
        holder.starTimeTv.setText(bean.getStartTime());
        holder.endTimeTv.setText(bean.getEndTime());
    }

    @Override
    public int getItemCount() {
        return data.getLineList().size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView lineNameTv;
        private TextView starTv;
        private TextView endTv;
        private TextView starTimeTv;
        private TextView endTimeTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            lineNameTv = (TextView) itemView.findViewById(R.id.lineName_tv);
            starTv = (TextView) itemView.findViewById(R.id.star_tv);
            endTv = (TextView) itemView.findViewById(R.id.end_tv);
            starTimeTv = (TextView) itemView.findViewById(R.id.starTime_tv);
            endTimeTv = (TextView) itemView.findViewById(R.id.endTime_tv);
        }
    }
}
