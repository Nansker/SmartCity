package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 16:42
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroFoundListBean;
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;

public class MetroFoundListAdapter extends RecyclerView.Adapter<MetroFoundListAdapter.BodyViewHolder> {
    private Context context;
    private List<MetroFoundListBean.DataBean> data;

    public MetroFoundListAdapter(Context context, List<MetroFoundListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metro_found, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        MetroFoundListBean.DataBean bean = data.get(position);
        List<MetroFoundListBean.DataBean.FoundListBean> metroFoundList = bean.getMetroFoundList();
        holder.publishDateTv.setText(SystemUtils.getValue(bean.getPublishDate(),""));
        holder.bodyContainer.setAdapter(new MetroFoundList1Adapter(context,metroFoundList));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView publishDateTv;
        private RecyclerView bodyContainer;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            publishDateTv = (TextView) itemView.findViewById(R.id.publishDate_tv);
            bodyContainer = (RecyclerView) itemView.findViewById(R.id.body_container);
            bodyContainer.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
