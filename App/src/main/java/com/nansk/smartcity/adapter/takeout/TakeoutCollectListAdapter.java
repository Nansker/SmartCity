package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 21:16
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
import com.nansk.smartcity.activity.takeout.TakeoutSellerDetailsActivity;
import com.nansk.smartcity.beans.takeout.TakeoutCollectListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutCollectListAdapter extends RecyclerView.Adapter<TakeoutCollectListAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutCollectListBean.RowsBean> data;

    public TakeoutCollectListAdapter(Context context, List<TakeoutCollectListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_cellect_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final TakeoutCollectListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getImgUrl()).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.nameTv.setText(bean.getSellerName());
        holder.scoreTv.setText(Double.toString(bean.getScore()) + "分");
        holder.saleQuantityTv.setText("月销 "+Integer.toString(bean.getSaleQuantity()));
        holder.addressTv.setText(bean.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TakeoutSellerDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sellerId",Integer.toString(bean.getSellerId()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageIv;
        private TextView nameTv;
        private TextView scoreTv;
        private TextView saleQuantityTv;
        private TextView addressTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            scoreTv = (TextView) itemView.findViewById(R.id.score_tv);
            saleQuantityTv = (TextView) itemView.findViewById(R.id.saleQuantity_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
        }
    }
}
