package com.nansk.smartcity.adapter;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/10 20:11
 * @Description 服务一级分类菜单
 */

public class PrimaryServiceAdapter extends RecyclerView.Adapter<PrimaryServiceAdapter.BodyViewHolder> {
    private Context context;
    private String[] classNames;

    public PrimaryServiceAdapter(Context context, String[] classNames) {
        this.context = context;
        this.classNames = classNames;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_primary_class, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        holder.classname.setText(classNames[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(position);
                notifyDataSetChanged();
            }
        });

        //根据回调的position设置当前Item的背景色
        if (position == getPosition()){
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }else {
            holder.itemView.setBackgroundColor(Color.parseColor("#f3f1f2"));
        }

    }

    @Override
    public int getItemCount() {
        return classNames.length;
    }

    public interface OnItemListener{
        void onClick(int position);
    }

    public void setOnItemListener(OnItemListener listener){
        this.onItemListener = listener;
    }

    public OnItemListener onItemListener;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView classname;

        public BodyViewHolder(@NonNull final View itemView) {
            super(itemView);
            classname = (TextView) itemView.findViewById(R.id.classname);
        }
    }
}
