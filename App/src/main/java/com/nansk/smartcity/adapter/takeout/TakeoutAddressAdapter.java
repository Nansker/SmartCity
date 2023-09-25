package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 14:54
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

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.takeout.TakeoutNewAddressActivity;
import com.nansk.smartcity.beans.takeout.TakeoutAddressListBean;

import java.util.List;

public class TakeoutAddressAdapter extends RecyclerView.Adapter<TakeoutAddressAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutAddressListBean.DataBean> data;

    private OnItemCallBack onItemCallBack;

    public TakeoutAddressAdapter(Context context, List<TakeoutAddressListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_address, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final TakeoutAddressListBean.DataBean bean = data.get(position);
        holder.nameTv.setText(bean.getName());
        holder.phoneTv.setText(bean.getPhone());
        holder.labelTv.setText(bean.getLabel());
        holder.addressTv.setText(bean.getAddressDetail());

        //跳转地址信息编辑页面
        holder.editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TakeoutNewAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("addressObj",bean);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemCallBack{
        void OnItemCallBack(int position,TakeoutAddressListBean.DataBean obj);
    }

    public void OnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView phoneTv;
        private TextView labelTv;
        private TextView addressTv;
        private ImageView editIv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            phoneTv = (TextView) itemView.findViewById(R.id.phone_tv);
            labelTv = (TextView) itemView.findViewById(R.id.label_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            editIv = (ImageView) itemView.findViewById(R.id.edit_iv);
        }
    }
}
