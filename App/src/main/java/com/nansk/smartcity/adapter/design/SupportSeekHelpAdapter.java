package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 19:56
 * @description
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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.support.SupportSeekHelpBean;

import java.util.ArrayList;
import java.util.List;

public class SupportSeekHelpAdapter extends RecyclerView.Adapter<SupportSeekHelpAdapter.BodyViewHolder> {
    private Context context;
    private List<SupportSeekHelpBean> data;

    private List<Integer> icons;

    public SupportSeekHelpAdapter(Context context) {
        this.context = context;
        icons = new ArrayList<>();
        icons.add(R.drawable.support_icon1);
        icons.add(R.drawable.support_icon2);
        icons.add(R.drawable.support_icon3);
    }

    public List<SupportSeekHelpBean> getData() {
        return data;
    }

    public void setData(List<SupportSeekHelpBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_support_seek_help, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        SupportSeekHelpBean bean = data.get(position);
        holder.titleTv.setText(bean.getTitle());
        holder.nameTv.setText(bean.getName());
        holder.telTv.setText(bean.getTel());
        holder.dateTv.setText(bean.getDate());
        holder.contentTv.setText(bean.getContent());
        Glide.with(context).load(icons.get((position+1)%3)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.iconIv);
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconIv;
        private TextView nameTv;
        private TextView telTv;
        private TextView dateTv;
        private TextView contentTv;
        private TextView titleTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            iconIv = (ImageView) itemView.findViewById(R.id.icon_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            telTv = (TextView) itemView.findViewById(R.id.tel_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
