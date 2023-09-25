package com.nansk.smartcity.adapter.bus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.bus.BusDetailsActivity;
import com.nansk.smartcity.beans.bus.BusLinesBean;
import com.nansk.smartcity.beans.bus.BusStopBean;

import java.util.List;


/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/16 11:30
 * @Description
 */
public class BusLinesAdapter extends RecyclerView.Adapter<BusLinesAdapter.BodyViewHolder> {
    private Context context;
    private List<BusLinesBean.RowsBean> data;
    private List<List<BusStopBean.RowsBean>> stops;
    private BusStopAdapter busStopAdapter;

    public BusLinesAdapter(Context context, List<BusLinesBean.RowsBean> data, List<List<BusStopBean.RowsBean>> stops) {
        this.context = context;
        this.data = data;
        this.stops = stops;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bus_lines_info, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BodyViewHolder holder, final int position) {
        final BusLinesBean.RowsBean rowsBean = data.get(position);
        holder.nameTv.setText(rowsBean.getName());
        holder.priceTv.setText("票价：" + rowsBean.getPrice().toString());
        holder.mileageTv.setText("里程：" + rowsBean.getMileage() + "km");
        holder.startTimeTv.setText("首班车：" + rowsBean.getStartTime());
        holder.endTimeTv.setText("末班车：" + rowsBean.getEndTime());
        holder.endTv.setText(rowsBean.getEnd());
        holder.startTv.setText(rowsBean.getFirst());

        if (position < stops.size())
        busStopAdapter = new BusStopAdapter(context, stops.get(position));
        holder.stopContainer.setAdapter(busStopAdapter);

        //跳转定制班车页
        holder.toCustomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BusDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("busObj",rowsBean);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        //显示全部站点
        final int[] flag = {0};
        holder.showAllLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.showAllLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (flag[0] == 0) {
                            holder.stopContainer.setVisibility(View.VISIBLE);
                            holder.inTipsTv.setText("收起");
                            holder.inIconIv.setRotationX(180);
                            flag[0] = 1;
                        } else {
                            holder.stopContainer.setVisibility(View.GONE);
                            holder.inTipsTv.setText("查看全部站点");
                            holder.inIconIv.setRotationX(360);
                            flag[0] = 0;
                        }

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView priceTv;
        private TextView mileageTv;
        private TextView startTimeTv;
        private TextView endTimeTv;
        private TextView startTv;
        private TextView endTv;
        private RecyclerView stopContainer;
        private LinearLayout showAllLayout;
        private ImageView inIconIv;
        private TextView inTipsTv;

        private LinearLayout toCustomLayout;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            mileageTv = (TextView) itemView.findViewById(R.id.mileage_tv);
            startTimeTv = (TextView) itemView.findViewById(R.id.startTime_tv);
            endTimeTv = (TextView) itemView.findViewById(R.id.endTime_tv);
            startTv = (TextView) itemView.findViewById(R.id.start_tv);
            endTv = (TextView) itemView.findViewById(R.id.end_tv);
            stopContainer = (RecyclerView) itemView.findViewById(R.id.stop_container);
            inIconIv = (ImageView) itemView.findViewById(R.id.inIcon_iv);
            inTipsTv = (TextView) itemView.findViewById(R.id.inTips_tv);
            toCustomLayout = (LinearLayout) itemView.findViewById(R.id.toCustom_layout);

            stopContainer.setLayoutManager(new LinearLayoutManager(context));
            stopContainer.setVisibility(View.GONE);

            showAllLayout = (LinearLayout) itemView.findViewById(R.id.showAll_layout);

        }
    }
}
