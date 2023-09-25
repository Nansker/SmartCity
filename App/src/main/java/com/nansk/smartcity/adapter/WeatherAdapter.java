package com.nansk.smartcity.adapter;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 14:47
 * @Description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.WeatherBean;
import com.nansk.smartcity.utils.SystemUtils;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.BodyViewHolder> {
    private Context context;
    //布局参数 0=最近时间列表 1=七天天气列表布局
    private int viewStyle;
    private WeatherBean.DataBean data;

    public WeatherAdapter(Context context, int viewStyle, WeatherBean.DataBean data) {
        this.context = context;
        this.viewStyle = viewStyle;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        BodyViewHolder holder = null;

        if (viewStyle == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_living_weather_house, parent, false);
        } else if (viewStyle == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.item_living_weather_day, parent, false);
        }

        if (view != null) {
            holder = new BodyViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {

        if (viewStyle == 0){
            List<WeatherBean.DataBean.TodayBean.HoursBean> hours = data.getToday().getHours();
            WeatherBean.DataBean.TodayBean.HoursBean bean = hours.get(position);
            holder.hourTv.setText(bean.getHour());
            holder.weatherTv.setText(bean.getWeather());
            holder.temperatureTv.setText(bean.getTemperature()+"℃");
            holder.imageIv.setImageResource(SystemUtils.getWeatherImg(bean.getWeather()));
        }else if (viewStyle == 1){
            List<WeatherBean.DataBean.WeatherListBean> weatherList = data.getWeatherList();
            WeatherBean.DataBean.WeatherListBean bean = weatherList.get(position);
            holder.dayTv.setText(bean.getDay());
            holder.weatherTv.setText(bean.getWeather());
            holder.maxTemperatureTv.setText(bean.getMinTemperature()+"℃");
            holder.minTemperatureTv.setText("/"+bean.getMaxTemperature()+"℃");
            holder.imageIv.setImageResource(SystemUtils.getWeatherImg(bean.getWeather()));
        }

    }

    @Override
    public int getItemCount() {
        if (viewStyle == 0) {
            return data.getToday().getHours().size();
        } else if (viewStyle == 1) {
            return data.getWeatherList().size();
        }
        return 0;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView hourTv;
        private ImageView imageIv;
        private TextView weatherTv;
        private TextView temperatureTv;

        private TextView dayTv;
        private TextView maxTemperatureTv;
        private TextView minTemperatureTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            if (viewStyle == 0) {
                hourTv = (TextView) itemView.findViewById(R.id.hour_tv);
                imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
                weatherTv = (TextView) itemView.findViewById(R.id.weather_tv);
                temperatureTv = (TextView) itemView.findViewById(R.id.temperature_tv);

            } else if (viewStyle == 1) {
                dayTv = (TextView) itemView.findViewById(R.id.day_tv);
                imageIv = (ImageView) itemView.findViewById(R.id.image_iv);
                weatherTv = (TextView) itemView.findViewById(R.id.weather_tv);
                maxTemperatureTv = (TextView) itemView.findViewById(R.id.maxTemperature_tv);
                minTemperatureTv = (TextView) itemView.findViewById(R.id.minTemperature_tv);
            }
        }
    }
}
