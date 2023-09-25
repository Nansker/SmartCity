package com.nansk.smartcity.activity.lifepay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.WeatherActivity;
import com.nansk.smartcity.adapter.lifepay.LifePayCategoryAdapter;
import com.nansk.smartcity.adapter.lifepay.LivingPressListAdapter;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.lifepay.LifePayCategoryBean;
import com.nansk.smartcity.beans.lifepay.LivingPressListBean;
import com.nansk.smartcity.beans.WeatherBean;
import com.nansk.smartcity.utils.BannerLinkListener;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SystemUtils;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 10:47
 * @Description 生活缴费首页
 */

public class LifePayActivity extends BaseActivity implements View.OnClickListener {
    private Gson gson;

    private Banner banner;
    private RecyclerView livingList;
    private RecyclerView pressList;
    private Button autoPayBtn;
    private Button accountManageBtn;
    private Button moreBtn;

    private ImageView weatherIv;
    private TextView weatherTv;
    private ViewFlipper weatherVf;
    private LinearLayout weatherBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_life_pay);
        setToolBarTitle("生活缴费");

        setToolBarRightButton(true, "南昌市", new MyCallBack() {
            @Override
            public void CallBack() {

            }
        });

        initView();
        initObject();
        initBanner();
        initLiving();
        initPressList();
        initWeather();
    }

    private void initView() {
        moreBtn = (Button) findViewById(R.id.more_btn);
        banner = (Banner) findViewById(R.id.banner);
        livingList = (RecyclerView) findViewById(R.id.living_list);
        pressList = (RecyclerView) findViewById(R.id.press_list);
        autoPayBtn = (Button) findViewById(R.id.autoPay_btn);
        accountManageBtn = (Button) findViewById(R.id.accountManage_btn);

        weatherIv = (ImageView) findViewById(R.id.weather_iv);
        weatherTv = (TextView) findViewById(R.id.weather_tv);
        weatherVf = (ViewFlipper) findViewById(R.id.weather_vf);
        weatherBox = (LinearLayout) findViewById(R.id.weather_box);
    }

    private void initObject() {

        weatherIv.setColorFilter(Color.parseColor("#FFFF9800"));

        autoPayBtn.setOnClickListener(this);
        moreBtn.setOnClickListener(this);
        weatherBox.setOnClickListener(this);

        BannerSetUtils.setBannerStyle(LifePayActivity.this, banner);
        gson = new Gson();

        livingList.setLayoutManager(new GridLayoutManager(LifePayActivity.this, 4));
        pressList.setLayoutManager(new LinearLayoutManager(LifePayActivity.this));
        pressList.addItemDecoration(new DividerItemDecoration(LifePayActivity.this, DividerItemDecoration.VERTICAL));

        weatherVf.setAutoStart(true);
        weatherVf.setFlipInterval(6000);
        weatherVf.setInAnimation(LifePayActivity.this, R.anim.wisdom_ontice_in);
        weatherVf.setOutAnimation(LifePayActivity.this, R.anim.wisdom_notice_out);
    }

    /**
     * @Create 2021/9/30 12:54
     * @Role 获取天气
     * @Param
     */
    private void initWeather() {
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
                            WeatherBean.DataBean.TodayBean today = data.getToday();

                            weatherTv.setText(today.getTempInfo().getWeather());
                            weatherIv.setImageResource(SystemUtils.getWeatherImg(today.getTempInfo().getWeather()));

                            List<TextView> textViews = new ArrayList<>();
                            for (int i = 0; i < 4; i++) {
                                TextView textView = new TextView(LifePayActivity.this);
                                switch (i) {
                                    case 0:
                                        textView.setText("温度 " + today.getTempInfo().getTemperature() + "℃");
                                        break;
                                    case 1:
                                        textView.setText("湿度 " + today.getTempInfo().getHumidity());
                                        break;
                                    case 2:
                                        textView.setText("空气 " + today.getTempInfo().getAir());
                                        break;
                                    case 3:
                                        textView.setText(today.getWind().getWindDirection() + "\u3000" + today.getWind().getWindStrength());
                                        break;
                                }
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textViews.add(textView);
                            }

                            for (int i = 0; i < textViews.size(); i++) {
                                weatherVf.addView(textViews.get(i));
                            }


                        } else {
                            weatherTv.setText("暂无天气数据！");
                        }
                    }
                });

            }
        });
    }

    /**
     * @Create 2021/9/28 11:14
     * @Role 相关资讯
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_BANNER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final BannerBean jsonObj = gson.fromJson(response.body().string(), BannerBean.class);
                if (jsonObj.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<BannerBean.RowsBean> rows = jsonObj.getRows();
                            List<String> pagers = new ArrayList<>();
                            for (int i = 0; i < rows.size(); i++) {
                                pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                            }
                            banner.setImages(pagers);
                            BannerLinkListener.setBannerLink(LifePayActivity.this, banner, rows);
                            banner.start();
                        }
                    });

                }
            }
        });
    }

    /**
     * @Create 2021/9/28 13:42
     * @Role 获取缴费分类入口
     * @Param
     */
    private void initLiving() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_LIVING, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LifePayCategoryBean jsonObj = gson.fromJson(response.body().string(), LifePayCategoryBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<LifePayCategoryBean.DataBean> data = jsonObj.getData();
                            if (data.size() > 0) {
                                LifePayCategoryAdapter adapter = new LifePayCategoryAdapter(LifePayActivity.this, data);

                                //点击监听回调
                                adapter.OnItemCallback(new LifePayCategoryAdapter.OnItemCallback() {
                                    @Override
                                    public void OnItemCallback(View view, int position, LifePayCategoryBean.DataBean obj) {

                                        if (obj.getId() != 1) {
                                            //水、电、燃气费
                                            Intent intent = new Intent(LifePayActivity.this, LifepayQueryActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("categoryId", obj.getId());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        } else {
                                            //电话费
                                            Intent intent = new Intent(LifePayActivity.this, LifepayPhoneActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("categoryId", obj.getId());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    }
                                });

                                livingList.setAdapter(adapter);
                            }

                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/9/28 14:23
     * @Role 获取资讯列表
     * @Param
     */
    private void initPressList() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIVING_PRESS, "press/list?pageNum=1&pageSize=3");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LivingPressListBean jsonObj = gson.fromJson(response.body().string(), LivingPressListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<LivingPressListBean.RowsBean> rows = jsonObj.getRows();
                            if (rows.size() > 0) {
                                LivingPressListAdapter adapter = new LivingPressListAdapter(LifePayActivity.this, rows);
                                pressList.setAdapter(adapter);
                            }
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //返回
            case R.id.back_bar:
                finish();
                break;
            //定位
            case R.id.fun_bar:
                break;
            //最近天气
            case R.id.weather_box:
                intent = new Intent(LifePayActivity.this, WeatherActivity.class);
                startActivity(intent);
                break;
            case R.id.more_btn:
                intent = new Intent(LifePayActivity.this, LivingPressActivity.class);
                startActivity(intent);
                break;
            //自动缴费
            case R.id.autoPay_btn:
                break;
            //账户管理
            case R.id.accountManage_btn:
                intent = new Intent(LifePayActivity.this, LifeAccountManageActivity.class);
                startActivity(intent);
                break;
        }
    }
}