package com.nansk.smartcity.adapter.bus;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 15:33
 * @Description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.bus.BusStopBean;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 19:56
 * @Description
 */

public class BusStopAdapter extends RecyclerView.Adapter<BusStopAdapter.BodyViewHolder> {
    private Context context;
    private List<BusStopBean.RowsBean> data;

    public BusStopAdapter(Context context, List<BusStopBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bus_lines_stop, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        BusStopBean.RowsBean rowsBean = data.get(position);
        holder.nameTv.setText(rowsBean.getName());

        if (position == 0){
            holder.tipsTv.setText("起点");
        }else if (position == data.size()-1){
            holder.tipsTv.setText("终点");
        }else {
         holder.tipsTv.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView tipsTv;
        private TextView nameTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            tipsTv = (TextView) itemView.findViewById(R.id.tips_tv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
        }
    }

}
