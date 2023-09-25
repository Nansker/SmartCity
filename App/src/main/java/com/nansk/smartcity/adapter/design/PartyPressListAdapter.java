package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/22 09:49
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
import com.nansk.smartcity.beans.party.PartyTextBean;
import com.nansk.smartcity.design.DesignResources;

import java.util.List;


public class PartyPressListAdapter extends RecyclerView.Adapter<PartyPressListAdapter.BodyViewHolder> {
    private Context context;
    private List<PartyTextBean> data;
    private OnItemCallBack onItemCallBack;

    public PartyPressListAdapter(Context context) {
        this.context = context;
    }

    public List<PartyTextBean> getData() {
        return data;
    }

    public void setData(List<PartyTextBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_party_press, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final PartyTextBean bean = data.get(position);
        holder.titleTv.setText(bean.getTitle());
        holder.contentTv.setText(bean.getContent());
        holder.imageIv.setImageResource(DesignResources.getPartyPressImage(bean.getId()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position, bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIv;
        private TextView titleTv;
        private TextView contentTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
