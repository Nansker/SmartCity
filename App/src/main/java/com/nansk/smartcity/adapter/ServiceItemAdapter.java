package com.nansk.smartcity.adapter;


import android.content.Context;
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

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/10 21:22
 * @Description 服务item适配器
 */

public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.BodyViewHolder>{
    private Context context;
    private List<ServiceJsonRows> data;

    private OnItemCallback onItemCallback;

    public ServiceItemAdapter(Context context, List<ServiceJsonRows> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ServiceItemAdapter.BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            Glide.with(context).load(MyApplication.IP + data.get(position).getImgUrl()).placeholder(R.drawable.ic_baseline_insert_photo_24).into(holder.iconIv);
            holder.nameTv.setText(data.get(position).getServiceName());
    }

    @Override
    public int getItemCount() {
        return data.size();
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
                        onItemCallback.OnItemCallback(v,getLayoutPosition(),data.get(getLayoutPosition()));
                }
            });
        }
    }
}
