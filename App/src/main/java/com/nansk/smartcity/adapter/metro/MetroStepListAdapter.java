package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/09 19:19
 * @description
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroStepLisBean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MetroStepListAdapter extends RecyclerView.Adapter<MetroStepListAdapter.BodyViewHolder> {
    private Context context;
    private List<MetroStepLisBean.DataBean> data;
    private OnItemCallBack onItemCallBack;

    public MetroStepListAdapter(Context context, List<MetroStepLisBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metro_step, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final MetroStepLisBean.DataBean bean = data.get(position);

        holder.stepNameTv.setText(bean.getName());

        List<String> lineNames = getLineNames(bean.getLines());
        holder.lineContainer.setAdapter(new MetroLinesNumAdapter(context,lineNames));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position, bean);
            }
        });

    }

    /**
     * @Create 2021/10/9 19:33
     * @Role 设置站点的地铁线
     * @Param
     */
    private List<String> getLineNames(List<MetroStepLisBean.DataBean.LinesBean> lines) {
        //根据线路名去重
        List<String> names = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String lineName = lines.get(i).getLineName();
            String oldName = lines.get(i).getLineName();

            //只保留线路名中的数字字母，或者开头字符
            lineName = lineName.replaceAll("[^a-z0-9A-Z]", "");
            if (!lineName.equals("")) {
                names.add(lineName);
            } else {
                names.add(oldName.substring(0, 1));
            }
        }

        HashSet<String> strings = new HashSet<>();
        for (int i =0;i<names.size();i++){
            strings.add(names.get(i));
        }
        names.clear();
        names.addAll(strings);

        return names;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemCallBack {
        void OnItemCallBack(int position, MetroStepLisBean.DataBean obj);
    }

    public void setOnItemCallBack(OnItemCallBack callBack) {
        this.onItemCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView stepNameTv;
        private RecyclerView lineContainer;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNameTv = (TextView) itemView.findViewById(R.id.stepName_tv);
            lineContainer = (RecyclerView) itemView.findViewById(R.id.line_container);
            lineContainer.setLayoutManager(new GridLayoutManager(context,3));
        }
    }
}
