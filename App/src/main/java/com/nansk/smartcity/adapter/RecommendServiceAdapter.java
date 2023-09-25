package com.nansk.smartcity.adapter;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 21:02
 * @Description 推荐服务适配器
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.service.ServiceJsonRows;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class RecommendServiceAdapter extends RecyclerView.Adapter<RecommendServiceAdapter.BodyViewHolder> {
    private Context context;
    private List<ServiceJsonRows> data;

    private OnItemCallback onItemCallback;

    public RecommendServiceAdapter(Context context, List<ServiceJsonRows> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_item, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    public interface OnItemCallback{
        void OnItemCallback(View view,int position,ServiceJsonRows rows);
    }

    public void setOnItemCallback(OnItemCallback callback){
        this.onItemCallback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        if (position < data.size()) {
            Glide.with(context).load(MyApplication.IP + data.get(position).getImgUrl()).placeholder(R.drawable.ic_baseline_insert_photo_24).into(holder.iconIv);
            holder.nameTv.setText(data.get(position).getServiceName());
        } else {
        //添加全部服务Item
            holder.iconIv.setColorFilter(Color.parseColor("#007aff"));
            Glide.with(context).load(R.mipmap.services).into(holder.iconIv);
            holder.nameTv.setText("全部服务");
        }

    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconIv;
        private TextView nameTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.icon_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getLayoutPosition() < 9){
                        onItemCallback.OnItemCallback(v,getLayoutPosition(),data.get(getLayoutPosition()));
                    }else {
                        onItemCallback.OnItemCallback(v,getLayoutPosition(),null);
                    }
                }
            });
        }
    }
}
