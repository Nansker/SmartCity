package com.nansk.smartcity.adapter.activity;



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
import com.nansk.smartcity.beans.activity.ActivityCommentListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 19:46
 * @Description 活动评论列表适配器
 */

public class ActivityCommentListAdapter extends RecyclerView.Adapter<ActivityCommentListAdapter.BodyViewHolder> {
    private Context context;
    private List<ActivityCommentListBean.RowsBean> data;


    public ActivityCommentListAdapter(Context context, List<ActivityCommentListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity_comment, parent,false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        ActivityCommentListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP + bean.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).placeholder(R.drawable.user_icon).into(holder.avatarIv);
        holder.userNameTv.setText(bean.getNickName());
        holder.dateTv.setText(bean.getCommentTime());
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
