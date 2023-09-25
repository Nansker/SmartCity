package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 16:00
 * @Description 菜品列表适配器
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
import com.nansk.smartcity.beans.takeout.TakeoutProductBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutProductAdapter extends RecyclerView.Adapter<TakeoutProductAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutProductBean.DataBean> data;

    public NumberCallback numberCallback;

    public TakeoutProductAdapter(Context context, List<TakeoutProductBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setNumberCallback(NumberCallback numberCallback) {
        this.numberCallback = numberCallback;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_product, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BodyViewHolder holder, int position) {
        TakeoutProductBean.DataBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP + bean.imgUrl).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.nameTv.setText(bean.getName());
        holder.detailTv.setText(bean.getDetail());
        holder.saleQuantityTv.setText("销量 " + bean.getSaleQuantity());
        holder.favorRateTv.setText("好评率 " + bean.getFavorRate() + "%");
        holder.priceTv.setText(Double.toString(bean.getPrice()));

        holder.removeIv.setVisibility(View.INVISIBLE);
        holder.numberTv.setVisibility(View.INVISIBLE);

        //加减按钮监听事件
        holder.addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddition(holder);
            }
        });

        holder.removeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubtraction(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface NumberCallback {
        void numberAddLoad(int number, TakeoutProductBean.DataBean obj);
        void numberSubLoad(int number,TakeoutProductBean.DataBean obj);
    }

    //添加数量
    private synchronized void onAddition(BodyViewHolder holder) {
        int i = Integer.parseInt(holder.numberTv.getText().toString());
            holder.removeIv.setVisibility(View.VISIBLE);
            holder.numberTv.setVisibility(View.VISIBLE);
            holder.numberTv.setText((Integer.toString(i + 1)));
        int position = holder.getLayoutPosition();
        if (numberCallback != null) {
                numberCallback.numberAddLoad(toInt(holder.numberTv.getText().toString()) - i, data.get(position));
            }
    }

    //减数量
    private synchronized void onSubtraction(BodyViewHolder holder) {
        int i = Integer.parseInt(holder.numberTv.getText().toString());
        if (i > 0) {
            holder.removeIv.setVisibility(View.VISIBLE);
            holder.numberTv.setVisibility(View.VISIBLE);
            holder.numberTv.setText("" + (i - 1));
            int position = holder.getLayoutPosition();
            if (numberCallback != null) {
                numberCallback.numberSubLoad(i - toInt(holder.numberTv.getText().toString()), data.get(position));
            }
        } else{
            holder.removeIv.setVisibility(View.INVISIBLE);
            holder.numberTv.setVisibility(View.INVISIBLE);
        }
    }

    private int toInt(String number) {
        return Integer.parseInt(number);
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nameTv;
        private TextView detailTv;
        private TextView saleQuantityTv;
        private TextView favorRateTv;
        private TextView priceTv;
        private ImageView removeIv;
        private TextView numberTv;
        private ImageView addIv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            detailTv = (TextView) itemView.findViewById(R.id.detail_tv);
            saleQuantityTv = (TextView) itemView.findViewById(R.id.saleQuantity_tv);
            favorRateTv = (TextView) itemView.findViewById(R.id.favorRate_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            removeIv = (ImageView) itemView.findViewById(R.id.remove_iv);
            numberTv = (TextView) itemView.findViewById(R.id.number_tv);
            addIv = (ImageView) itemView.findViewById(R.id.add_iv);
        }
    }
}
