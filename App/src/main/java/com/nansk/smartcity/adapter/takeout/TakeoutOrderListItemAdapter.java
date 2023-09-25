package com.nansk.smartcity.adapter.takeout;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 19:16
 * @description
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
import com.nansk.smartcity.activity.takeout.TakeoutOrderDetailsActivity;
import com.nansk.smartcity.beans.takeout.TakeoutOrderListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutOrderListItemAdapter extends RecyclerView.Adapter<TakeoutOrderListItemAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutOrderListBean.RowsBean.OrderInfoBean.OrderItemListBean> data;

    public TakeoutOrderListItemAdapter(Context context, List<TakeoutOrderListBean.RowsBean.OrderInfoBean.OrderItemListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_order_list_item, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final TakeoutOrderListBean.RowsBean.OrderInfoBean.OrderItemListBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP + bean.getProductImage()).into(holder.imageIv);
        holder.nameTv.setText(bean.getProductName());

        //点击菜品信息跳转到订单详情页
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TakeoutOrderDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("orderNo", bean.getOrderNo());
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
        private TextView nameTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
        }
    }
}
