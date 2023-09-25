package com.nansk.smartcity.adapter.job;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/10/01 00:22
 * @Description 历史投递列表适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.job.JobDeliveryRecordListBean;

import java.util.List;

public class JobDeliverListAdapter extends RecyclerView.Adapter<JobDeliverListAdapter.BodyViewHolder> {
    private Context context;
private List<JobDeliveryRecordListBean.RowsBean> data;

    public JobDeliverListAdapter(Context context, List<JobDeliveryRecordListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job_deliver_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        JobDeliveryRecordListBean.RowsBean bean = data.get(position);
        holder.companyNameTv.setText(getValue(bean.getCompanyName()));
        if (bean.getMoney() != null && bean.getMoney()!=""){
            holder.salaryTv.setText(bean.getMoney() + "k");
        }else {
            holder.salaryTv.setText("暂无数据");
        }

        holder.starTimeTv.setText("投递时间："+getValue(bean.getSatrTime()));
        holder.nameTv.setText(getValue(bean.getPostName()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getValue(String value){
        if (value != null && value != ""){
            return value;
        }
        return "暂无数据";
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView salaryTv;
        private TextView companyNameTv;
        private TextView starTimeTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            salaryTv = (TextView) itemView.findViewById(R.id.salary_tv);
            companyNameTv = (TextView) itemView.findViewById(R.id.companyName_tv);
            starTimeTv = (TextView) itemView.findViewById(R.id.starTime_tv);
        }
    }
}
