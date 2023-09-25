package com.nansk.smartcity.adapter.metro;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 09:23
 * @description 站点地铁线数字显示列表适配器
 */

public class MetroLinesNumAdapter extends RecyclerView.Adapter<MetroLinesNumAdapter.BodyViewHolder> {
    private Context context;
    private List<String> data;
    private String[] lineColors;

    public MetroLinesNumAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        lineColors = context.getResources().getStringArray(R.array.metro_line_colors);
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView view = new TextView(context);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        String name = data.get(position);
        holder.textView.setText(name);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(getLinesColor(name)));
        gradientDrawable.setCornerRadius(100);
        holder.textView.setBackground(gradientDrawable);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String getLinesColor(String lineName) {
        if (lineName.equals("1") || lineName.equals("八")) {
            return lineColors[0];
        } else if (lineName.equals("2")) {
            return lineColors[1];
        } else if (lineName.equals("4") || lineName.equals("大")) {
            return lineColors[2];
        } else if (lineName.equals("5")) {
            return lineColors[3];
        } else if (lineName.equals("6")) {
            return lineColors[4];
        } else if (lineName.equals("7")) {
            return lineColors[5];
        } else if (lineName.equals("8")) {
            return lineColors[6];
        } else if (lineName.equals("9")) {
            return lineColors[7];
        } else if (lineName.equals("10")) {
            return lineColors[8];
        } else if (lineName.equals("13")) {
            return lineColors[9];
        } else if (lineName.equals("14")) {
            return lineColors[10];
        } else if (lineName.equals("15")) {
            return lineColors[11];
        } else if (lineName.equals("16")) {
            return lineColors[12];
        } else if (lineName.equals("亦")) {
            return lineColors[13];
        } else if (lineName.equals("昌")) {
            return lineColors[14];
        } else if (lineName.equals("机")) {
            return lineColors[15];
        } else if (lineName.equals("房")) {
            return lineColors[16];
        } else {
            return "#c70541";
        }
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 60);
            layoutParams.rightMargin = 6;
            layoutParams.leftMargin = 6;
            layoutParams.topMargin = 6;
            layoutParams.bottomMargin = 6;
            textView.setLayoutParams(layoutParams);

            textView.setTextColor(Color.parseColor("#ffffff"));
            textView.setTextSize(12);
            textView.setGravity(Gravity.CENTER);
        }
    }
}
