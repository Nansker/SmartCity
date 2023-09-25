package com.nansk.smartcity.adapter.hospital;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 10:36
 * @Description
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
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.hospital.HospitalListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 19:56
 * @Description
 */

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.BodyViewHolder> {
    private Context context;
    List<HospitalListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;
    public HospitalListAdapter(Context context, List<HospitalListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override

    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hospital_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final HospitalListBean.RowsBean rowsBean = data.get(position);
        Glide.with(context).load(MyApplication.IP + rowsBean.getImgUrl()).into(holder.imageIv);
        holder.nameTv.setText(rowsBean.getHospitalName());
        holder.levelBar.setRating(rowsBean.getLevel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,rowsBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView nameTv;
        private RatingBar levelBar;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            levelBar = (RatingBar) itemView.findViewById(R.id.level_bar);
        }

    }
}
