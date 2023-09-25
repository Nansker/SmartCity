package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 11:35
 * @description
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.movie.MovieFilmListBean;
import com.nansk.smartcity.beans.movie.MoviePreviewFilmBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.List;

public class MoviePreviewFilmAdapter extends RecyclerView.Adapter<MoviePreviewFilmAdapter.BodyViewHolder> {
    private Context context;
    private List<MoviePreviewFilmBean.RowsBean> data;


    public MoviePreviewFilmAdapter(Context context, List<MoviePreviewFilmBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_hot_film, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        MoviePreviewFilmBean.RowsBean bean = data.get(position);
        holder.nameTv.setText(bean.getName());
        Glide.with(context).load(MyApplication.IP+bean.getCover()).placeholder(R.drawable.default_img).into(holder.imageIv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nameTv;
        private TextView payBtn;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            payBtn = (TextView) itemView.findViewById(R.id.pay_btn);

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor("#FF9800"));
            gradientDrawable.setCornerRadius(100);
            payBtn.setBackground(gradientDrawable);
            payBtn.setText("想看");

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = WindowMangerUtils.getWindowSize(context,0)/4;
            itemView.setLayoutParams(layoutParams);
        }
    }
}
