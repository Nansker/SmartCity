package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 17:07
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
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroFoundListBean;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class MetroFoundList1Adapter extends RecyclerView.Adapter<MetroFoundList1Adapter.BodyViewHolder> {
    private Context context;
    private List<MetroFoundListBean.DataBean.FoundListBean> data;


    public MetroFoundList1Adapter(Context context, List<MetroFoundListBean.DataBean.FoundListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metro_found_list1, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        MetroFoundListBean.DataBean.FoundListBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP +bean.getImgUrl()).into(holder.imageIv);
        holder.claimPlaceTv.setText(SystemUtils.getValue(bean.getClaimPlace(),""));
        holder.foundTimeTv.setText(SystemUtils.getValue(bean.getFoundTime(),""));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView claimPlaceTv;
        private TextView foundTimeTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            claimPlaceTv = (TextView) itemView.findViewById(R.id.claimPlace_tv);
            foundTimeTv = (TextView) itemView.findViewById(R.id.foundTime_tv);
        }
    }
}
