package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/22 10:26
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

import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.party.PartyTextBean;
import com.nansk.smartcity.design.DesignResources;

import java.util.List;


public class PartyActivityAdapter extends RecyclerView.Adapter<PartyActivityAdapter.BodyViewHolder> {
    private Context context;
    private List<PartyTextBean> data;
    private OnItemCallBack onItemCallBack;

    public PartyActivityAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_party_activity, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final PartyTextBean bean = data.get(position);
        holder.nameTv.setText(bean.getTitle());
        holder.imageIv.setImageResource(DesignResources.getPartyPressImage(bean.getId()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView dateTv;
        private TextView addressTv;
        private ImageView imageIv;
        private TextView studyBtn;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
            imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
            studyBtn = (TextView) itemView.findViewById(R.id.study_btn);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(100);
            drawable.setStroke(2,Color.parseColor(context.getResources().getString(R.string.theme_party)));
            drawable.setColor(Color.parseColor("#ffffff"));
            studyBtn.setTextColor(Color.parseColor(context.getResources().getString(R.string.theme_party)));
            studyBtn.setBackground(drawable);
        }
    }
}
