package com.nansk.smartcity.adapter.activity;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 16:40
 * @Description
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.activity.ActivityDetailsActivity;
import com.nansk.smartcity.beans.activity.ActivityListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class AcitvityListAdapter extends RecyclerView.Adapter<AcitvityListAdapter.BodyViewHolder> {
    private Context context;
    private List<ActivityListBean.RowsBean> data;

    public AcitvityListAdapter(Context context, List<ActivityListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final ActivityListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getImgUrl()).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.nameTv.setText(bean.getName());
        holder.signupNumTv.setText("报名人数:"+bean.getSignupNum() + "");
        holder.likeNumTv.setText(bean.getLikeNum() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("activityId",bean.getId());
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
        private ImageView imageIv;
        private TextView nameTv;
        private TextView signupNumTv;
        private TextView likeNumTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            signupNumTv = (TextView) itemView.findViewById(R.id.signupNum_tv);
            likeNumTv = (TextView) itemView.findViewById(R.id.likeNum_tv);
        }
    }
}
