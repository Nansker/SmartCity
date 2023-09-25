package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 21:16
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

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.takeout.TakeoutSellerListActivity;
import com.nansk.smartcity.beans.takeout.TakeOutThemeBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutThemeAdapter extends RecyclerView.Adapter<TakeoutThemeAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeOutThemeBean.DataBean> data;

    public TakeoutThemeAdapter(Context context, List<TakeOutThemeBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_item, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        TakeOutThemeBean.DataBean bean = data.get(position);
        holder.nameTv.setText(bean.getThemeName());
        Glide.with(context).load(MyApplication.IP+bean.getImgUrl()).placeholder(R.drawable.ic_baseline_insert_photo_24).into(holder.iconIv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconIv;
        private TextView nameTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.icon_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TakeOutThemeBean.DataBean bean = data.get(getLayoutPosition());
                    Intent intent = new Intent(context, TakeoutSellerListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("themeObj",bean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
