package com.nansk.smartcity.adapter.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 14:09
 * @description 地铁首页站点列表适配器
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroListBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MetroListAdapter extends RecyclerView.Adapter<MetroListAdapter.BodyViewHolder> {
    private Context context;
    private List<MetroListBean.DataBean> data;
    private String[] lineColors;

    public MetroListAdapter(Context context, List<MetroListBean.DataBean> data) {
        this.context = context;
        this.data = data;
        if (context != null){
            lineColors = context.getResources().getStringArray(R.array.metro_line_colors);
        }

    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metro_step_line, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        MetroListBean.DataBean bean = data.get(position);
        holder.lineNameTv.setText(bean.getLineName());
        setReachTime(holder.reachTimeTv, bean.getReachTime());
        holder.preStepTv.setText(bean.getPreStep().getName());
        holder.nextStepTv.setText(bean.getNextStep().getName());

        setStepLines(holder.preStepLines, 0, bean);
        setStepLines(holder.nextStepLines,1,bean);
    }

    /**
     * @Create 2021/10/6 14:44
     * @Role 设置上一站或下一站所属的线路
     * @Param
     */
    private void setStepLines(GridLayout container, int type, MetroListBean.DataBean obj) {
        HashSet<String> setLines = new HashSet<>();
        List<String> lines = new ArrayList<>();

        //上一站
        if (type == 0) {
            MetroListBean.DataBean.PreStepBean preStep = obj.getPreStep();
            List<MetroListBean.DataBean.PreStepBean.LinesBean> preStepLines = preStep.getLines();
            for (int i = 0; i < preStepLines.size(); i++) {
                lines.add(preStepLines.get(i).getLineName());
            }
        } else {
            //下一站
            MetroListBean.DataBean.NextStepBean nextStep = obj.getNextStep();
            List<MetroListBean.DataBean.NextStepBean.LinesBeanX> nextStepLines = nextStep.getLines();
            for (int i = 0; i < nextStepLines.size(); i++) {
                lines.add(nextStepLines.get(i).getLineName());
            }
        }

        //地铁线去重
        for (int i =0;i<lines.size();i++){
            setLines.add(lines.get(i));
        }
        lines.clear();
        lines.addAll(setLines);

        if (lines.size()>0){
            for (int i = 0; i < lines.size(); i++) {
                TextView textView = new TextView(context);
                View view = new View(context);
                String lineName = lines.get(i);
                String substring = lineName.substring(0, 1);
                textView.setText(substring);
                container.addView(view);
                container.addView(textView);

                ViewGroup.LayoutParams layoutParams1 = view.getLayoutParams();
                layoutParams1.width = 10;
                view.setLayoutParams(layoutParams1);

                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(100);
                gradientDrawable.setColor(Color.parseColor(getLinesColor(Integer.parseInt(substring))));

                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setBackground(gradientDrawable);
                textView.setGravity(Gravity.CENTER);

                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                layoutParams.width = 56;
                layoutParams.height = 56;
                textView.setTextSize(12);
                textView.setLayoutParams(layoutParams);
            }
        }
    }

    private String getLinesColor(int line){
        return lineColors[line-1];
    }

    /**
     * @Create 2021/10/6 14:37
     * @Role 设置到到达时间样式
     * @Param
     */
    private void setReachTime(TextView textView, int time) {
        if (time == 0) {
            textView.setText("即将到站");
            textView.setTextColor(Color.parseColor("#FF9800"));
        } else if (time < 4 && time > 0) {
            textView.setText(time + "分钟");
            textView.setTextColor(Color.parseColor("#FF9800"));
        } else {
            textView.setText(time + "分钟");
            textView.setTextColor(Color.parseColor("#333333"));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView lineNameTv;
        private TextView reachTimeTv;
        private TextView preStepTv;
        private GridLayout preStepLines;
        private TextView nextStepTv;
        private GridLayout nextStepLines;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            lineNameTv = (TextView) itemView.findViewById(R.id.lineName_tv);
            reachTimeTv = (TextView) itemView.findViewById(R.id.reachTime_tv);
            preStepTv = (TextView) itemView.findViewById(R.id.preStep_tv);
            preStepLines = (GridLayout) itemView.findViewById(R.id.preStep_lines);
            nextStepTv = (TextView) itemView.findViewById(R.id.nextStep_tv);
            nextStepLines = (GridLayout) itemView.findViewById(R.id.nextStep_lines);
        }
    }
}
