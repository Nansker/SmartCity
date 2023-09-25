package com.nansk.smartcity.adapter;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 17:11
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.support.SupportVillageBean;
import com.nansk.smartcity.design.DesignResources;

import java.util.List;

public class SupportVillageAdapter extends RecyclerView.Adapter<SupportVillageAdapter.BodyViewHolder> {
    private Context context;
    private List<SupportVillageBean> data;
    private OnItemCallBack onItemCallBack;

    public SupportVillageAdapter(Context context) {
        this.context = context;
    }

    public List<SupportVillageBean> getData() {
        return data;
    }

    public void setData(List<SupportVillageBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_support_village, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final SupportVillageBean bean = data.get(position);
        holder.nameTv.setText(bean.getName());
        holder.introductionTv.setText(bean.getIntroduce());
        holder.imageIv.setImageResource(DesignResources.getSupportVillage(bean.getId()));
        holder.dateTv.setText(bean.getDate());
        holder.readNumTv.setText("查看人数："+bean.getReadNum()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setOnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nameTv;
        private TextView introductionTv;
        private TextView dateTv;
        private TextView readNumTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            introductionTv = (TextView) itemView.findViewById(R.id.introduction_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            readNumTv = (TextView) itemView.findViewById(R.id.readNum_tv);
        }
    }
}
