package com.nansk.smartcity.adapter.lifepay;

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
import com.nansk.smartcity.activity.lifepay.LivingPressDetailsActivity;
import com.nansk.smartcity.beans.lifepay.LivingPressListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 13:52
 * @Description 生活资讯列表适配器
 */
public class LivingPressListAdapter extends RecyclerView.Adapter<LivingPressListAdapter.BodyViewHolder> {
    private Context context;
    private List<LivingPressListBean.RowsBean> data;

    public LivingPressListAdapter(Context context, List<LivingPressListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lifepay_press_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final LivingPressListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP + bean.getCover()).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.titleTv.setText(bean.getTitle());
        holder.timeTv.setText("更新时间：" + bean.getPublishDate());
        holder.likeNumTv.setText(Integer.toString(bean.getLikeNum()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LivingPressDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pressId",bean.getId());
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
        private TextView titleTv;
        private TextView timeTv;
        private ImageView imageIv;
        private TextView likeNumTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            likeNumTv = (TextView) itemView.findViewById(R.id.likeNum_tv);
        }
    }
}
