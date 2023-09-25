package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 14:39
 * @description
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.movie.MovieGetType;
import com.nansk.smartcity.beans.movie.MovieFilmListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class MovieFilmListAdapter extends RecyclerView.Adapter<MovieFilmListAdapter.BodyViewHolder> {
    private Context context;
    private List<MovieFilmListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;

    public MovieFilmListAdapter(Context context, List<MovieFilmListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_film_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final MovieFilmListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.IP + bean.getCover()).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.nameTv.setText(bean.getName());

        holder.scoreTv.setText(Integer.toString(bean.getScore())+".0");
        holder.playTypeTv.setText(MovieGetType.getPlayType(bean.getPlayType()));
        holder.categoryTv.setText(MovieGetType.getMovieCategory(bean.getCategory()));

        if (bean.getLikeNum() / 10000 > 1) {
            double v = (bean.getLikeNum() + 0.5) / 10000;
            holder.likeNumTv.setText(String.format("%.2f", v) + "万");
        } else {
            holder.likeNumTv.setText(Integer.toString(bean.getLikeNum()));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    public interface OnItemCallBack {
        void OnItemCallBack(int position, MovieFilmListBean.RowsBean obj);
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nameTv;
        private TextView scoreTv;
        private TextView likeNumTv;
        private TextView categoryTv;
        private TextView playTypeTv;
        private TextView payBtn;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            scoreTv = (TextView) itemView.findViewById(R.id.score_tv);
            likeNumTv = (TextView) itemView.findViewById(R.id.likeNum_tv);
            categoryTv = (TextView) itemView.findViewById(R.id.category_tv);
            playTypeTv = (TextView) itemView.findViewById(R.id.playType_tv);
            payBtn = (TextView) itemView.findViewById(R.id.pay_btn);

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(100);
            gradientDrawable.setColor(Color.parseColor("#ff3e5d"));
            payBtn.setBackground(gradientDrawable);
        }
    }
}
