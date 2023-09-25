package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 17:57
 * @description 所有地铁线适配器
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroLinesListBean;

import java.util.List;

public class MetroLineListAdapter extends RecyclerView.Adapter<MetroLineListAdapter.BodyViewHolder> {
    private Context context;
    private List<MetroLinesListBean.DataBean> data;
    private OnItemCallBack onItemCallBack;

    public MetroLineListAdapter(Context context, List<MetroLinesListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metro_line, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {

        if (position == 0){
            holder.name.setText("全部");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                    onItemCallBack.OnItemCallBack(position, null);
                }
            });
        }else {
            int newPosition = position-1;
            final MetroLinesListBean.DataBean bean = data.get(newPosition);
            holder.name.setText(bean.getLineName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                    onItemCallBack.OnItemCallBack(position, bean);
                }
            });
        }

        if (position == getPosition()) {
            holder.indicatorView.setVisibility(View.VISIBLE);
            holder.name.setTextColor(Color.parseColor("#ca062c"));
        } else {
            holder.indicatorView.setVisibility(View.INVISIBLE);
            holder.name.setTextColor(Color.parseColor("#666666"));
        }

    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }


    public interface OnItemCallBack {
        void OnItemCallBack(int position, MetroLinesListBean.DataBean obj);
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private View indicatorView;
        private TextView name;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            indicatorView = (View) itemView.findViewById(R.id.indicator_view);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

}

