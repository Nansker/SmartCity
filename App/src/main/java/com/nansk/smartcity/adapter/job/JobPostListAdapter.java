package com.nansk.smartcity.adapter.job;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 20:32
 * @Description 找工作-职位列表适配器
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.job.PositionDetailsActivity;
import com.nansk.smartcity.beans.job.JobPostBean;

import java.util.ArrayList;
import java.util.List;

public class JobPostListAdapter extends RecyclerView.Adapter<JobPostListAdapter.BodyViewHolder> {
    private Context context;
    private List<JobPostBean.RowsBean> data;

    public JobPostListAdapter(Context context, List<JobPostBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job_post, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final JobPostBean.RowsBean bean = data.get(position);
        holder.nameTv.setText(bean.getName());
        holder.salaryTv.setText(bean.getSalary() + "k");
        holder.obligationTv.setText(bean.getObligation());
        holder.companyNameTv.setText(bean.getCompanyName());
        holder.needTv.setText(bean.getNeed());
        holder.contactsTv.setText(bean.getContacts());
        holder.addressTv.setText(bean.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PositionDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("postId",bean.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView salaryTv;
        private TextView obligationTv;
        private TextView companyNameTv;
        private TextView needTv;
        private TextView contactsTv;
        private TextView addressTv;

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @SuppressLint("ResourceAsColor")
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv  = (TextView) itemView.findViewById(R.id.name_tv);
            salaryTv = (TextView) itemView.findViewById(R.id.salary_tv);
            obligationTv = (TextView) itemView.findViewById(R.id.obligation_tv);
            companyNameTv = (TextView) itemView.findViewById(R.id.companyName_tv);
            needTv = (TextView) itemView.findViewById(R.id.need_tv);
            contactsTv = (TextView) itemView.findViewById(R.id.contacts_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);

            List<GradientDrawable> drawables = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(5);
                gradientDrawable.setColor(Color.parseColor("#F7F7F7"));
                drawables.add(gradientDrawable);
            }
            needTv.setBackground(drawables.get(0));
            companyNameTv.setBackground(drawables.get(1));
        }
    }
}
