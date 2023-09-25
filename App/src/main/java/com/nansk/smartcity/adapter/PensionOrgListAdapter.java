package com.nansk.smartcity.adapter;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 11:46
 * @description 推荐养老机构适配器
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
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.pension.PensionOrgBean;
import com.nansk.smartcity.design.DesignResources;

import java.util.List;

public class PensionOrgListAdapter extends RecyclerView.Adapter<PensionOrgListAdapter.BodyViewHolder> {
    private Context context;
    private List<PensionOrgBean> data;
    private OnItemCallBack onItemCallBack;

    public PensionOrgListAdapter(Context context) {
        this.context = context;
    }

    public List<PensionOrgBean> getData() {
        return data;
    }

    public void setData(List<PensionOrgBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pension_org, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final PensionOrgBean bean = data.get(position);
        holder.introductionTv.setText(bean.getIntroduce());
        holder.name.setText(bean.getName());
        Glide.with(context).load(DesignResources.getPensionOrgImage(bean.getId())).into(holder.imageIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    public void setOnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView introductionTv;
        private TextView name;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            introductionTv = (TextView) itemView.findViewById(R.id.introduction_tv);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
