package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 17:56
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

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.lifepay.LivingPressCommentListBean;
import com.nansk.smartcity.beans.metro.MetroPressCommentsBean;

import java.util.List;

public class MetroPressCommentsAdapter extends RecyclerView.Adapter<MetroPressCommentsAdapter.BodyViewHolder> {
    private Context context;
    private List<MetroPressCommentsBean.RowsBean> data;

    public MetroPressCommentsAdapter(Context context, List<MetroPressCommentsBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MetroPressCommentsAdapter.BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_press_comment, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MetroPressCommentsAdapter.BodyViewHolder holder, int position) {
        MetroPressCommentsBean.RowsBean bean = data.get(position);
        holder.userNameTv.setText(bean.getUserName());
        holder.dateTv.setText(bean.getCommentDate());
        holder.contentTv.setText(bean.getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarIv;
        private TextView userNameTv;
        private TextView dateTv;
        private TextView contentTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarIv = (ImageView) itemView.findViewById(R.id.avatar_iv);
            userNameTv = (TextView) itemView.findViewById(R.id.userName_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
