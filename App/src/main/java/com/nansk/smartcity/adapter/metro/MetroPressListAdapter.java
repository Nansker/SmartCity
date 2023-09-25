package com.nansk.smartcity.adapter.metro;


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
import com.nansk.smartcity.beans.metro.MetroPressListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;


/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 16:28
 * @description
 */

public class MetroPressListAdapter extends RecyclerView.Adapter<MetroPressListAdapter.BodyViewHolder> {
    private Context context;
    private List<MetroPressListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;

    public MetroPressListAdapter(Context context, List<MetroPressListBean.RowsBean> data) {
        this.context = context;
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
        final MetroPressListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP + bean.getCover()).placeholder(R.drawable.default_img).into(holder.coverIv);
        holder.titleTv.setText(bean.getTitle());
        holder.timeTv.setText(bean.getUpdateTime());
        holder.readNumTv.setText("阅读："+bean.getReadNum());
        holder.likeNumTv.setText("点赞："+bean.getLikeNum() + "");

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
        void OnItemCallBack(int position,MetroPressListBean.RowsBean obj);
    }

    public void OnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView likeNumTv;
        private ImageView coverIv;
        private TextView titleTv;
        private TextView timeTv;
        private TextView readNumTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = (ImageView) itemView.findViewById(R.id.cover_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            likeNumTv = (TextView) itemView.findViewById(R.id.likeNum_tv);
            timeTv = (TextView) itemView.findViewById(R.id.time_tv);
            readNumTv = (TextView) itemView.findViewById(R.id.readNum_tv);
        }
    }
}
