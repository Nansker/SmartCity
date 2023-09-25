package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 14:40
 * @Description
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
import com.nansk.smartcity.beans.takeout.TakeoutOrderDetailsBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutOrderDetailsProductAdapter extends RecyclerView.Adapter<TakeoutOrderDetailsProductAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutOrderDetailsBean.DataBean.OrderInfoBean.OrderItemListBean> data;

    public TakeoutOrderDetailsProductAdapter(Context context, List<TakeoutOrderDetailsBean.DataBean.OrderInfoBean.OrderItemListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TakeoutOrderDetailsProductAdapter.BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_product_order, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TakeoutOrderDetailsProductAdapter.BodyViewHolder holder, int position) {
        TakeoutOrderDetailsBean.DataBean.OrderInfoBean.OrderItemListBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getProductImage()).into(holder.imageIv);
        holder.nameTv.setText(bean.getProductName());
        holder.priceTv.setText(bean.getTotalPrice().toString());
        holder.numberTv.setText(bean.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nameTv;
        private TextView numberTv;
        private TextView priceTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView); imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            numberTv = (TextView) itemView.findViewById(R.id.number_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);

            nameTv.setTextSize(14);
        }
    }
}
