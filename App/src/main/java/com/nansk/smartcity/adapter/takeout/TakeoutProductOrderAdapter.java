package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 11:31
 * @Description 外卖-提交订单-菜品列表
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
import com.nansk.smartcity.beans.takeout.TakeoutOrderObj;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutProductOrderAdapter extends RecyclerView.Adapter<TakeoutProductOrderAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutOrderObj.OrderItemListBean> data;

    public TakeoutProductOrderAdapter(Context context, List<TakeoutOrderObj.OrderItemListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_product_order, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        TakeoutOrderObj.OrderItemListBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getImgUrl()).into(holder.imageIv);
        holder.nameTv.setText(bean.getProductName());
        holder.priceTv.setText(bean.getPrice()+"");
        holder.numberTv.setText(bean.getNumber()+"");
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

        }
    }
}
