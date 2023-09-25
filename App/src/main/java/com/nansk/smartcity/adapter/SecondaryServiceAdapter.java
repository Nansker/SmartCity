package com.nansk.smartcity.adapter;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/10 20:52
 * @Description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.service.ServiceJsonRows;
import com.nansk.smartcity.utils.ServiceLinkListener;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/14 10:21
 * @Description 服务二级分类菜单
 */

public class SecondaryServiceAdapter extends RecyclerView.Adapter<SecondaryServiceAdapter.BodyViewHolder> {
    private Context context;
    private String[] classNames;
    private List<List<ServiceJsonRows>> dataGroup;

    public SecondaryServiceAdapter(Context context, String[] classNames, List<List<ServiceJsonRows>> dataGroup) {
        this.context = context;
        this.classNames = classNames;
        this.dataGroup = dataGroup;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_secondary_class, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        holder.className.setText(classNames[position]);
        ServiceItemAdapter adapter = new ServiceItemAdapter(context, dataGroup.get(position));
        holder.servicesView.setAdapter(adapter);
        adapter.setOnItemCallback(new ServiceItemAdapter.OnItemCallback() {
            @Override
            public void OnItemCallback(View view, int position, ServiceJsonRows rows) {
                ServiceLinkListener.setLinkListener(context,rows);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataGroup.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView className;
        private RecyclerView servicesView;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            className = (TextView) itemView.findViewById(R.id.className);
            servicesView = (RecyclerView) itemView.findViewById(R.id.services_view);
            servicesView.setLayoutManager(new GridLayoutManager(context, 3));

        }
    }
}
