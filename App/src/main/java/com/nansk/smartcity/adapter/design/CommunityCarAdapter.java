package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 16:50
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

import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.community.CommunityCarBean;

import java.util.List;

public class CommunityCarAdapter extends RecyclerView.Adapter<CommunityCarAdapter.BodyViewHolder> {
    private Context context;
    private List<CommunityCarBean> data;
    private OnItemCallBack onItemCallBack;

    public CommunityCarAdapter(Context context) {
        this.context = context;
    }

    public List<CommunityCarBean> getData() {
        return data;
    }

    public void setData(List<CommunityCarBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_community_car, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final CommunityCarBean bean = data.get(position);
        holder.carNoTv.setText(bean.getCarNo());
        holder.nameTv.setText(bean.getName());
        holder.editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });

    }

    public void setOnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
       return 0;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView carNoTv;
        private TextView nameTv;
        private ImageView editIv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            carNoTv = (TextView) itemView.findViewById(R.id.carNo_tv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            editIv = (ImageView) itemView.findViewById(R.id.edit_iv);
        }
    }
}
