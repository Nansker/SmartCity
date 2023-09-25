package com.nansk.smartcity.adapter;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/15 20:00
 * @Description 新闻评论适配器
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
import com.nansk.smartcity.beans.press.PressCommentListObj;
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;

public class PressCommentsAdapter extends RecyclerView.Adapter<PressCommentsAdapter.BodyViewHolder> {
    private Context context;
    private List<PressCommentListObj.RowsBean> rows;

    public PressCommentsAdapter(Context context, List<PressCommentListObj.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_press_comment, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        Glide.with(context).load(R.drawable.user_icon).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.avatarIv);
        holder.userNameTv.setText(rows.get(position).getUserName());
        holder.dateTv.setText(rows.get(position).getCommentDate());
        holder.contentTv.setText(SystemUtils.getValue(rows.get(position).getContent(),"暂无内容"));
    }

    @Override
    public int getItemCount() {
        return rows.size();
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
