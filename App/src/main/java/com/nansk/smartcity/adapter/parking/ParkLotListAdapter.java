package com.nansk.smartcity.adapter.parking;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 10:57
 * @Description
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.parking.ParkLotDetailsActivity;
import com.nansk.smartcity.beans.park.ParkLotListBean;

import java.util.List;

public class ParkLotListAdapter extends RecyclerView.Adapter<ParkLotListAdapter.BodyViewHolder> {
    private Context context;
    private List<ParkLotListBean.RowsBean> data;

    public ParkLotListAdapter(Context context, List<ParkLotListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_park_lot_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        ParkLotListBean.RowsBean bean = data.get(position);
        holder.parkName.setText(bean.getParkName());
        holder.distanceTv.setText(bean.getDistance()+"km");
        holder.vacancyTv.setText("空位" + bean.getVacancy() + "个");
        holder.priceCapsTv.setText("停车费:"+bean.getRates()+"元/小时");
        holder.addressTv.setText(bean.getAddress());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView parkName;
        private TextView distanceTv;
        private TextView vacancyTv;
        private TextView priceCapsTv;
        private TextView addressTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            parkName = (TextView) itemView.findViewById(R.id.parkName);
            distanceTv = (TextView) itemView.findViewById(R.id.distance_tv);
            vacancyTv = (TextView) itemView.findViewById(R.id.vacancy_tv);
            priceCapsTv = (TextView) itemView.findViewById(R.id.priceCaps_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParkLotListBean.RowsBean rowsBean = data.get(getLayoutPosition());
                    Intent intent = new Intent(context, ParkLotDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("parkLotObj",rowsBean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
