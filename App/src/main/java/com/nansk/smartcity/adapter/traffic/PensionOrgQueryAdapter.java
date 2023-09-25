package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 11:46
 * @description 推荐养老机构适配器
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.pension.PensionOrgBean;
import com.nansk.smartcity.design.DesignResources;
import com.nansk.smartcity.design.pension.PensionOrgDetailsActivity;

import java.util.List;

public class PensionOrgQueryAdapter extends RecyclerView.Adapter<PensionOrgQueryAdapter.BodyViewHolder> {
    private Context context;
    private List<PensionOrgBean> data;
    private OnItemCallBack onItemCallBack;

    public PensionOrgQueryAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_pension_org_query, parent, false);
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
                Intent intent = new Intent(context, PensionOrgDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj",bean);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.makeBtn.setOnClickListener(new View.OnClickListener() {
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

    public void setMakeCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView introductionTv;
        private TextView name;
        private Button makeBtn;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            introductionTv = (TextView) itemView.findViewById(R.id.introduction_tv);
            name = (TextView) itemView.findViewById(R.id.name);
            makeBtn = (Button) itemView.findViewById(R.id.make_btn);
        }
    }
}
