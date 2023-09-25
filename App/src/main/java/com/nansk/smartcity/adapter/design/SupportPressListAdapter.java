package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 15:13
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
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.support.SupportPressBean;
import com.nansk.smartcity.design.DesignResources;

import java.util.List;



public class SupportPressListAdapter extends RecyclerView.Adapter<SupportPressListAdapter.BodyViewHolder> {
    private Context context;
    private List<SupportPressBean> data;
    private OnItemCallBack onItemCallBack;


    public SupportPressListAdapter(Context context) {
        this.context = context;
    }

    public List<SupportPressBean> getData() {
        return data;
    }

    public void setData(List<SupportPressBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_press_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final SupportPressBean bean = data.get(position);
        Glide.with(context).load(DesignResources.getSupportPressImage(bean.getId())).placeholder(R.drawable.default_img).into(holder.coverIv);
        holder.titleTv.setText(bean.getTitle());
        holder.timeTv.setText(bean.getData());
        holder.readNumTv.setText("阅读人数：" + bean.getRedNum());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });

    }

    public void setOnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverIv;
        private TextView titleTv;
        private TextView timeTv;
        private TextView readNumTv;
        private TextView likeNumTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = (ImageView) itemView.findViewById(R.id.cover_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
            readNumTv = (TextView) itemView.findViewById(R.id.readNum_tv);
            likeNumTv = (TextView) itemView.findViewById(R.id.likeNum_tv);
        }
    }
}
