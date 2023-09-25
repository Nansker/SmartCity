package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/26 17:19
 * @Description 根据菜品搜索店家
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
import com.nansk.smartcity.beans.takeout.TakeoutSellerSearchBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutSellerSearchAdapter extends RecyclerView.Adapter<TakeoutSellerSearchAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutSellerSearchBean.RowsBean> data;

    public TakeoutSellerSearchAdapter(Context context, List<TakeoutSellerSearchBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TakeoutSellerSearchAdapter.BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_seller_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TakeoutSellerSearchAdapter.BodyViewHolder holder, int position) {
        final TakeoutSellerSearchBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getImgUrl()).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.nameTv.setText(bean.getName());
        holder.scoreTv.setText(Double.toString(bean.getScore()) + "分");
        holder.saleQuantityTv.setText("月销 "+Integer.toString(bean.getSaleQuantity()));
        holder.avgCostTv.setText("人均 "+bean.getAvgCost().toString());
        holder.deliveryTimeTv.setText(Integer.toString(bean.getDeliveryTime())+"分钟");
        holder.distanceTv.setText(Double.toString(bean.getDistance()) +"m");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TakeoutSellerDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sellerId",Integer.toString(bean.getProductList().get(0).getSellerId()));
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
        private TextView scoreTv;
        private TextView saleQuantityTv;
        private TextView avgCostTv;
        private TextView deliveryTimeTv;
        private TextView distanceTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            scoreTv = (TextView) itemView.findViewById(R.id.score_tv);
            saleQuantityTv = (TextView) itemView.findViewById(R.id.saleQuantity_tv);
            avgCostTv = (TextView) itemView.findViewById(R.id.avgCost_tv);
            deliveryTimeTv = (TextView) itemView.findViewById(R.id.deliveryTime_tv);
            distanceTv = (TextView) itemView.findViewById(R.id.distance_tv);
        }
    }
}
