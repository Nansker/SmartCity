package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 17:17
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.protection.ProtectionNearListBean;

import java.util.List;


public class ProtectionNearListAdapter extends RecyclerView.Adapter<ProtectionNearListAdapter.BodyViewHolder> {
    private Context context;
    private List<ProtectionNearListBean> data;
    private OnItemCallBack onItemCallBack;


    public ProtectionNearListAdapter(Context context, List<ProtectionNearListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_protection_near, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final ProtectionNearListBean bean = data.get(position);
        holder.addressTv.setText(bean.getAddress());
        holder.fromTv.setText(bean.getFrom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position, bean);
            }
        });
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView addressTv;
        private TextView fromTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            fromTv = (TextView) itemView.findViewById(R.id.from_tv);
        }
    }
}
