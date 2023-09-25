package com.nansk.smartcity.adapter.lifepay;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 11:57
 * @Description
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;

public class LifepayAmuntChangeAdapter extends RecyclerView.Adapter<LifepayAmuntChangeAdapter.BodyViewHolder> {
    private Context context;
    private int[] amounts;
    private OnItemCallBack onItemCallBack;

    public LifepayAmuntChangeAdapter(Context context, int[] amounts) {
        this.context = context;
        this.amounts = amounts;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phone_amount_change, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BodyViewHolder holder, final int position) {
        holder.amunnt.setText(amounts[position]+"元");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position);
                notifyDataSetChanged();
            }
        });

        if (position == getPosition()){
            holder.tipsIv.setVisibility(View.VISIBLE);
        }else {
            holder.tipsIv.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return amounts.length;
    }

    public interface OnItemCallBack{
        void OnItemCallBack(int position);
    }

    public void OnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView amunnt;
        private ImageView tipsIv;
        private RelativeLayout changeBox;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            amunnt = (TextView) itemView.findViewById(R.id.amunnt);
            tipsIv = (ImageView) itemView.findViewById(R.id.tipsiv);
            changeBox = itemView.findViewById(R.id.change_box);
        }
    }
}

