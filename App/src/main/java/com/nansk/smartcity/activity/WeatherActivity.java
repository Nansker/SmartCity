package com.nansk.smartcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.WeatherAdapter;
import com.nansk.smartcity.beans.WeatherBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 14:03
 * @Description 最近天气
 */

public class WeatherActivity extends BaseActivity {

    private TextView temperatureTv;
    private TextView maxTemperatureTv;
    private TextView minTemperatureTv;
    private TextView weatherTv;
    private TextView airTv;
    private TextView updateTimeTv;
    private RecyclerView hoursContainer;
    private RecyclerView weatherListContainer;

    private Gson gson;
    private WeatherBean.DataBean weatherObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_weather);
        setToolBarBackground("#639fd5");
        initView();
        initObject();
        fillData();

    }


    private void initView() {

        temperatureTv = (TextView) findViewById(R.id.temperature_tv);
        maxTemperatureTv = (TextView) findViewById(R.id.maxTemperature_tv);
        minTemperatureTv = (TextView) findViewById(R.id.minTemperature_tv);

        weatherTv = (TextView) findViewById(R.id.weather_tv);
        airTv = (TextView) findViewById(R.id.air_tv);

        updateTimeTv = (TextView) findViewById(R.id.updateTime_tv);

        hoursContainer = (RecyclerView) findViewById(R.id.hours_container);
        weatherListContainer = (RecyclerView) findViewById(R.id.weatherList_container);

    }

    private void initObject() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeatherActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        hoursContainer.setLayoutManager(linearLayoutManager);
        weatherListContainer.setLayoutManager(new LinearLayoutManager(WeatherActivity.this));

        gson = new Gson();
        Intent intent = getIntent();
        weatherObj = (WeatherBean.DataBean) intent.getSerializableExtra("weatherObj");

    }

    /**
     * @Create 2021/9/30 15:23
     * @Role 更新UI
     * @Param
     */
    private void updateUI(WeatherBean.DataBean data) {
        WeatherBean.DataBean.TodayBean today = data.getToday();
        //今日天气
        WeatherBean.DataBean.TodayBean.TempInfoBean tempInfo = today.getTempInfo();
        temperatureTv.setText(tempInfo.getTemperature());
        maxTemperatureTv.setText(tempInfo.getMinTemperature() + "℃");
        minTemperatureTv.setText("/" + tempInfo.getMaxTemperature() + "℃");
        weatherTv.setText(tempInfo.getWeather());
        airTv.setText(tempInfo.getAir());


        updateTimeTv.setText("上次更新时间：" + today.getUpdateTime());

        hoursContainer.setAdapter(new WeatherAdapter(WeatherActivity.this, 0, data));
        weatherListContainer.setAdapter(new WeatherAdapter(WeatherActivity.this, 1, data));
    }

    //获取数据
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIVING_WEATHER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final WeatherBean jsonObj = gson.fromJson(response.body().string(), WeatherBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            WeatherBean.DataBean data = jsonObj.getData();
                            updateUI(data);

                        } else {
                            showToast("更新数据失败");
                        }
                    }
                });
            }
        });
    }
}