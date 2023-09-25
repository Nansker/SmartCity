package com.nansk.smartcity.adapter.takeout;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.takeout.TakeoutCategoryBean;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 14:42
 * @Description 店家菜品分类适配器
 */
public class TakeoutCategoryAdapter extends RecyclerView.Adapter<TakeoutCategoryAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutCategoryBean.DataBean> data;

    private OnItemClick onItemClick;

    public TakeoutCategoryAdapter(Context context, List<TakeoutCategoryBean.DataBean> data) {
        this.context = context;
        this.data = data;
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
        holder.classname.setText(data.get(position).getName());

        if (position == getPosition()){
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }else {
            holder.itemView.setBackgroundResource(R.color.theme_bck);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClick{
        void OnItemClick(int position,TakeoutCategoryBean.DataBean obj);
    }

    public void OnItemClick(OnItemClick click){
        this.onItemClick = click;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView classname;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            classname = (TextView) itemView.findViewById(R.id.classname);

            classname.setTextSize(13);

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = WindowMangerUtils.getWindowSize(context,0) /4;
            itemView.setLayoutParams(layoutParams);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TakeoutCategoryBean.DataBean bean = data.get(getLayoutPosition());
                    onItemClick.OnItemClick(getLayoutPosition(),bean);
                }
            });
        }
    }
}
