package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 14:29
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

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.press.PressListBean;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TrafficPressListAdapter extends RecyclerView.Adapter<TrafficPressListAdapter.BodyViewHolder> {
    private Context context;
    private List<PressListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;

    public TrafficPressListAdapter(Context context, List<PressListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_press, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final PressListBean.RowsBean bean = data.get(position);
        Glide.with(context).load(MyApplication.getIP(context)+bean.getCover()).placeholder(R.drawable.default_img).into(holder.imageIv);
        holder.titleTv.setText(SystemUtils.getValue(bean.getTitle(), ""));
        holder.publishDateTv.setText(SystemUtils.getValue(bean.getPublishDate(), ""));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemCallBack {
        void OnItemCallBack(int position, PressListBean.RowsBean obj);
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView titleTv;
        private TextView publishDateTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            publishDateTv = (TextView) itemView.findViewById(R.id.publishDate_tv);
        }
    }
}
