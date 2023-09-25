package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 21:42
 * @Description 推荐好店适配器
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
import com.nansk.smartcity.activity.takeout.TakeoutSellerDetailsActivity;
import com.nansk.smartcity.beans.takeout.TakeoutSellerBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.List;

public class TakeoutRecommSellerAdapter extends RecyclerView.Adapter<TakeoutRecommSellerAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutSellerBean.RowsBean> data;

    public TakeoutRecommSellerAdapter(Context context, List<TakeoutSellerBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_recommend_seller, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final TakeoutSellerBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP +bean.getImgUrl()).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.nameTv.setText(bean.getName());
        holder.saleNumTv.setText("进三小时销量："+ String.valueOf(bean.getSaleNum3hour()));
        holder.scoreTv.setText("评分："+ Double.toString(bean.getScore()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TakeoutSellerDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sellerId",Integer.toString(bean.getId()));
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
        private TextView saleNumTv;
        private TextView scoreTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            saleNumTv = (TextView) itemView.findViewById(R.id.saleNum_tv);
            scoreTv = (TextView) itemView.findViewById(R.id.score_tv);

            ViewGroup.LayoutParams layoutParams = imageIv.getLayoutParams();
            layoutParams.height = WindowMangerUtils.getWindowSize(context,0) /3;
            imageIv.setLayoutParams(layoutParams);
            imageIv.setScaleType(ImageView.ScaleType.FIT_XY);


        }
    }
}
