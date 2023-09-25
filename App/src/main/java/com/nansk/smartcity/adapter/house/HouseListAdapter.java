package com.nansk.smartcity.adapter.house;

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
import com.nansk.smartcity.activity.house.HouseDetailsActivity;
import com.nansk.smartcity.beans.house.HouseListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/26 21:46
 * @Description 找房子-房源列表
 */

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.BodyViewHolder> {
    private Context context;
    private List<HouseListBean.RowsBean> data;

    public HouseListAdapter(Context context, List<HouseListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_house_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final HouseListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getPic()).into(holder.imageIv);
        holder.sourceNameTv.setText(bean.getSourceName());
        holder.addressTv.setText(bean.getAddress());
        holder.descriptionTv.setText(bean.getDescription());
        holder.priceTv.setText(bean.getPrice());
        holder.areaSizeTv.setText(bean.getAreaSize()+"平方米");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HouseDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("houseId",Integer.toString(bean.getId()));
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
        private TextView sourceNameTv;
        private TextView addressTv;
        private TextView descriptionTv;
        private TextView priceTv;
        private TextView areaSizeTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            sourceNameTv = (TextView) itemView.findViewById(R.id.sourceName_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            descriptionTv = (TextView) itemView.findViewById(R.id.description_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            areaSizeTv = (TextView) itemView.findViewById(R.id.areaSize_tv);
        }
    }

}
