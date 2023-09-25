package com.nansk.smartcity.adapter.bus;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 15:33
 * @Description 线路详情站点信息适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class BusStopsAdapter extends RecyclerView.Adapter<BusStopsAdapter.BodyViewHolder> {
    private Context context;
    private List<BusStopBean.RowsBean> data;

    public BusStopsAdapter(Context context, List<BusStopBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bus_lines_stops, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        BusStopBean.RowsBean rowsBean = data.get(position);
        if (position == 0) {
            holder.leftLine.setVisibility(View.INVISIBLE);
        }else if (position == data.size() - 1) {
            holder.rightLine.setVisibility(View.INVISIBLE);
        }else {
            holder.leftLine.setVisibility(View.VISIBLE);
            holder.rightLine.setVisibility(View.VISIBLE);
        }

        holder.nameTv.setText(rowsBean.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView busIv;
        private LinearLayout leftLine;
        private ImageView currentIv;
        private LinearLayout rightLine;
        private TextView nameTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            busIv = (ImageView) itemView.findViewById(R.id.bus_iv);
            leftLine = (LinearLayout) itemView.findViewById(R.id.left_line);
            currentIv = (ImageView) itemView.findViewById(R.id.current_iv);
            rightLine = (LinearLayout) itemView.findViewById(R.id.right_line);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            busIv.setVisibility(View.GONE);
        }
    }

}
