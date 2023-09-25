package com.nansk.smartcity.adapter.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 10:05
 * @description 电影场次列表适配器
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.movie.MovieGetType;

import java.util.List;

public class MovieTheatreTimeListAdapter extends RecyclerView.Adapter<MovieTheatreTimeListAdapter.BodyViewHolder> {
    private Context context;
    private List<MovieTheatreTimeListBean.RowsBean> data;

    private OnItemCallBack onItemCallBack;
    private PayCallBack payCallBack;

    public MovieTheatreTimeListAdapter(Context context, List<MovieTheatreTimeListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_theatre_time, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final MovieTheatreTimeListBean.RowsBean bean = data.get(position);
        holder.nameTv.setText(bean.getTheatreName());
        holder.priceTv.setText("￥"+bean.getPrice());
//        holder.categoryTv.setText(MovieGetType.getMovieCategory(b));
        holder.playTypeTv.setText(MovieGetType.getPlayType(bean.getPlayType()));

        holder.starTimeTv.setText(bean.getStartTime());
        holder.endTimeTv.setText(bean.getEndTime());

        holder.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payCallBack.PayCallBack(position,bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemCallBack {
        void OnItemCallBack(int position, MovieTheatreTimeListBean.RowsBean obj);
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }

    /**
     * @Create 2021/10/14 11:16
     * @Role 购买按钮接口
     * @Param
     */
    public interface PayCallBack{
        void PayCallBack(int position,MovieTheatreTimeListBean.RowsBean obj);
    }
    public void setPayCallBack(PayCallBack payCallBack){
        this.payCallBack = payCallBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView priceTv;
        private TextView categoryTv;
        private TextView playTypeTv;
        private TextView starTimeTv;
        private TextView endTimeTv;
        private TextView payBtn;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            categoryTv = (TextView) itemView.findViewById(R.id.category_tv);
            playTypeTv = (TextView) itemView.findViewById(R.id.playType_tv);
            starTimeTv = (TextView) itemView.findViewById(R.id.starTime_tv);
            endTimeTv = (TextView) itemView.findViewById(R.id.endTime_tv);
            payBtn = (TextView) itemView.findViewById(R.id.pay_btn);

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(100);
            gradientDrawable.setColor(Color.parseColor("#ff3e5d"));
            payBtn.setBackground(gradientDrawable);
        }
    }
}
