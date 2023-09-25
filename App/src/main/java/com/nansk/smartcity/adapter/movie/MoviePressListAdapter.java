package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 18:51
 * @description
 */

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.movie.MoviePressListBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;

public class MoviePressListAdapter extends RecyclerView.Adapter<MoviePressListAdapter.BodyViewHolder> {
    private Context context;
    private List<MoviePressListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;

    public MoviePressListAdapter(Context context, List<MoviePressListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_press, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final MoviePressListBean.RowsBean bean = data.get(position);
        holder.titleTv.setText(SystemUtils.getValue(bean.getTitle(), ""));
        String content = Html.fromHtml(bean.getContent(), Html.FROM_HTML_MODE_LEGACY).toString();
        holder.contentTv.setText(SystemUtils.getValue(content, ""));
        holder.publishDateTv.setText(SystemUtils.getValue(bean.getPublishDate(), ""));
        holder.likeNumTv.setText(SystemUtils.getValue(Integer.toString(bean.getLikeNum()), ""));

        Glide.with(context).load(MyApplication.IP + bean.getCover()).placeholder(R.drawable.default_img).into(holder.imageIv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    public interface OnItemCallBack {
        void OnItemCallBack(int position, MoviePressListBean.RowsBean obj);
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
        private TextView titleTv;
        private TextView contentTv;
        private TextView publishDateTv;
        private TextView likeNumTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
            publishDateTv = (TextView) itemView.findViewById(R.id.publishDate_tv);
            likeNumTv = (TextView) itemView.findViewById(R.id.likeNum_tv);
        }
    }
}
