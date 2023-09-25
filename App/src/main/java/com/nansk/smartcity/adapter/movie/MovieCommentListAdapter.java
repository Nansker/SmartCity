package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 19:17
 * @description
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.movie.MovieFilmCommentListBean;

import java.util.List;

public class MovieCommentListAdapter extends RecyclerView.Adapter<MovieCommentListAdapter.BodyViewHolder> {
    private Context context;
    private List<MovieFilmCommentListBean.RowsBean> data;
    private LikeCommentCallBack likeCommentCallBack;

    public MovieCommentListAdapter(Context context, List<MovieFilmCommentListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_film_comment, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final MovieFilmCommentListBean.RowsBean bean = data.get(position);
        holder.nickNameTv.setText(bean.getNickName());
        holder.scoreTv.setText(Integer.toString(bean.getScore()) + ".0");
        holder.scoreBar.setRating(bean.getScore());

        holder.contentTv.setText(bean.getContent());
        holder.commentDateTv.setText(bean.getCommentDate());
        holder.likeNumTv.setText(Integer.toString(bean.getLikeNum()));

        if (position == data.size() - 1) {
            holder.dividerView.setVisibility(View.INVISIBLE);
        } else {
            holder.dividerView.setVisibility(View.VISIBLE);
        }

        holder.likeCommentBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeCommentCallBack.LikeCommentCallBack(position,bean.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface LikeCommentCallBack{
        void LikeCommentCallBack( int position, int id);
    }

    public void setLikeCommentCallBack(LikeCommentCallBack callBack){
        this.likeCommentCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nickNameTv;
        private RatingBar scoreBar;
        private TextView scoreTv;
        private TextView contentTv;
        private TextView commentDateTv;
        private TextView likeNumTv;
        private View dividerView;
        private LinearLayout likeCommentBox;
        ImageView likeIv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nickNameTv = (TextView) itemView.findViewById(R.id.nickName_tv);
            scoreBar = (RatingBar) itemView.findViewById(R.id.score_bar);
            scoreTv = (TextView) itemView.findViewById(R.id.score_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
            commentDateTv = (TextView) itemView.findViewById(R.id.commentDate_tv);
            likeNumTv = (TextView) itemView.findViewById(R.id.likeNum_tv);
            dividerView = (View) itemView.findViewById(R.id.divider_view);
            likeCommentBox = (LinearLayout) itemView.findViewById(R.id.like_comment_box);

            likeIv = (ImageView) likeCommentBox.getChildAt(0);
            likeIv.setColorFilter(Color.parseColor("#e6e6e6"));
        }
    }
}
