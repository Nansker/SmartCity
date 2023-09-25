package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 17:33
 * @Description 商家评论适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.takeout.TakeoutSellerCommentBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutCommentAdapter extends RecyclerView.Adapter<TakeoutCommentAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutSellerCommentBean.RowsBean> data;


    public TakeoutCommentAdapter(Context context, List<TakeoutSellerCommentBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_seller_comment, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        TakeoutSellerCommentBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP+bean.getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.avatarIv);
        holder.userNameTv.setText(bean.userName);
        holder.dateTv.setText(bean.getCommentDate());
        holder.contentTv.setText(bean.getContent());

        if (bean.getReplyContent() == null || bean.getReplyContent() == ""){
            holder.replyContainer.setVisibility(View.GONE);
        }else {
            holder.replyContainer.setVisibility(View.VISIBLE);
            holder.replyContentTv.setText(bean.getReplyContent());
            holder.replyTimeTv.setText(bean.getReplyTime());
        }
        holder.scoreBar.setRating((float) bean.getScore());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarIv;
        private TextView userNameTv;
        private TextView dateTv;
        private RatingBar scoreBar;
        private TextView contentTv;
        private LinearLayout replyContainer;
        private TextView replyContentTv;
        private TextView replyTimeTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarIv = (ImageView) itemView.findViewById(R.id.avatar_iv);
            userNameTv = (TextView) itemView.findViewById(R.id.userName_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            scoreBar = (RatingBar) itemView.findViewById(R.id.score_bar);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
            replyContainer = (LinearLayout) itemView.findViewById(R.id.reply_container);
            replyContentTv = (TextView) itemView.findViewById(R.id.replyContent_tv);
            replyTimeTv = (TextView) itemView.findViewById(R.id.replyTime_tv);
        }
    }
}
