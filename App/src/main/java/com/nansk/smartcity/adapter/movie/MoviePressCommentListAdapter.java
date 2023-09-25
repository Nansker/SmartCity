package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 20:12
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
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;

public class MoviePressCommentListAdapter extends RecyclerView.Adapter<MoviePressCommentListAdapter.BodyViewHolder> {
    private Context context;
    private List<MovieCommentListBean.RowsBean> data;


    public MoviePressCommentListAdapter(Context context, List<MovieCommentListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
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
        MovieCommentListBean.RowsBean bean = data.get(position);
        holder.contentTv.setText(SystemUtils.getValue(bean.getContent(),"没有内容"));
        holder.dateTv.setText(bean.getCommentDate());
        holder.userNameTv.setText(bean.getUserName());
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
