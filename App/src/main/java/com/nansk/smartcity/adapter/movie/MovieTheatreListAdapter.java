package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 12:37
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.movie.MovieTheatreListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class MovieTheatreListAdapter extends RecyclerView.Adapter<MovieTheatreListAdapter.BodyViewHolder> {
    private Context context;
    private List<MovieTheatreListBean.RowsBean> data;


    public MovieTheatreListAdapter(Context context, List<MovieTheatreListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_theatre, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        MovieTheatreListBean.RowsBean bean = data.get(position);
        holder.addressTv.setText(bean.getAddress());
        holder.nameTv.setText(bean.getName());
        Glide.with(context).load(MyApplication.IP+ bean.getCover()).placeholder(R.drawable.default_img).into(holder.imageIv);

        holder.scoreBar.setRating(bean.getScore()/20);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nameTv;
        private TextView addressTv;
        private RatingBar scoreBar;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView)  itemView.findViewById(R.id.name_tv);
            addressTv = (TextView)  itemView.findViewById(R.id.address_tv);
            scoreBar = (RatingBar)  itemView.findViewById(R.id.score_bar);
        }
    }
}
