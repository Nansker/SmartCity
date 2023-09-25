package com.nansk.smartcity.adapter.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 14:14
 * @Description 缴费分类入口适配器
 */

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
import com.nansk.smartcity.beans.lifepay.LifePayCategoryBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class LifePayCategoryAdapter extends RecyclerView.Adapter<LifePayCategoryAdapter.BodyViewHolder> {
    private Context context;
    private List<LifePayCategoryBean.DataBean> data;

    private OnItemCallback onItemCallback;

    public LifePayCategoryAdapter(Context context, List<LifePayCategoryBean.DataBean> data) {
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
        void OnItemCallback(View view,int position,LifePayCategoryBean.DataBean obj);
    }

    public void OnItemCallback(OnItemCallback callback){
        this.onItemCallback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull LifePayCategoryAdapter.BodyViewHolder holder, final int position) {
        Glide.with(context).load(MyApplication.IP + data.get(position).getImgUrl()).placeholder(R.drawable.ic_baseline_insert_photo_24).into(holder.iconIv);
        holder.nameTv.setText(data.get(position).getLiveName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallback.OnItemCallback(v,position,data.get(position));
            }
        });
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
        }
    }
}
