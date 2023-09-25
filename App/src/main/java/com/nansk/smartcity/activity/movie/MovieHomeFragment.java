package com.nansk.smartcity.activity.movie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.WeatherActivity;
import com.nansk.smartcity.adapter.movie.MovieHotFilmAdapter;
import com.nansk.smartcity.adapter.movie.MoviePreviewFilmAdapter;
import com.nansk.smartcity.adapter.movie.MovieTheatreListAdapter;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.PositionCurrent;
import com.nansk.smartcity.beans.WeatherBean;
import com.nansk.smartcity.beans.movie.MovieFilmListBean;
import com.nansk.smartcity.beans.movie.MoviePreviewFilmBean;
import com.nansk.smartcity.beans.movie.MovieTheatreListBean;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 08:34
 * @description 电影首页
 */

public class MovieHomeFragment extends Fragment {
    private View view;

    private TextView placeTv;
    private LinearLayout searchBox;
    private SearchView searchView;
    private Button cancelBtn;
    private Banner banner;
    private LinearLayout weatherBox;
    private ImageView weatherIv;
    private TextView weatherTv;
    private ViewFlipper weatherVf;
    private LinearLayout appContainer;
    private RecyclerView hotContainer;
    private RecyclerView previewContainer;
    private RecyclerView theatreContainer;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_home, container, false);
        initView();
        initObject();
        getCurrentPosition();
        initBanner();
        getWeather();
        getHotFilmList();
        getPreviewFilmList();
        getTheatreList();
        return view;
    }

    /**
     * @Create 2021/10/14 21:38
     * @Role 获取当前地理位置
     * @Param
     */
    private void getCurrentPosition() {
        String url = ConnectInfo.getUrl(ConnectInfo.POSITION_CURRENT, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PositionCurrent json = MyApplication.gson.fromJson(response.body().string(), PositionCurrent.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200){
                            PositionCurrent.DataBean data = json.getData();
                            placeTv.setText(data.getCity());
                        }else{
                            placeTv.setText("获取失败");
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 12:34
     * @Role 获取影院列表
     * @Param
     */
    private void getTheatreList() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_THEATRE, "list");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieTheatreListBean json = MyApplication.gson.fromJson(response.body().string(), MovieTheatreListBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            if (json.getTotal() > 0) {
                                List<MovieTheatreListBean.RowsBean> rows = json.getRows();
                                MovieTheatreListAdapter adapter = new MovieTheatreListAdapter(getContext(), rows);
                                theatreContainer.setAdapter(adapter);
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 12:20
     * @Role 预告电影
     * @Param
     */
    private void getPreviewFilmList() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_FILM, "preview/list");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MoviePreviewFilmBean json = MyApplication.gson.fromJson(response.body().string(), MoviePreviewFilmBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            if (json.getTotal() > 0) {
                                List<MoviePreviewFilmBean.RowsBean> rows = json.getRows();
                                MoviePreviewFilmAdapter adapter = new MoviePreviewFilmAdapter(getContext(), rows);
                                previewContainer.setAdapter(adapter);
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 11:28
     * @Role 获取热播影片列表
     * @Param
     */
    private void getHotFilmList() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_FILM, "list?recommend=Y");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieFilmListBean json = MyApplication.gson.fromJson(response.body().string(), MovieFilmListBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            if (json.getTotal() > 0) {
                                List<MovieFilmListBean.RowsBean> rows = json.getRows();
                                MovieHotFilmAdapter adapter = new MovieHotFilmAdapter(getContext(), rows);
                                hotContainer.setAdapter(adapter);
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 11:11
     * @Role 获取天气
     * @Param
     */
    private void getWeather() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIVING_WEATHER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final WeatherBean jsonObj = MyApplication.gson.fromJson(response.body().string(), WeatherBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            WeatherBean.DataBean data = jsonObj.getData();
                            WeatherBean.DataBean.TodayBean today = data.getToday();

                            weatherTv.setText(today.getTempInfo().getWeather());
                            weatherIv.setImageResource(SystemUtils.getWeatherImg(today.getTempInfo().getWeather()));
                            List<TextView> textViews = new ArrayList<>();
                            for (int i = 0; i < 4; i++) {
                                TextView textView = new TextView(getContext());
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
     * @Create 2021/10/13 10:54
     * @Role 公告轮播
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_BANNER, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final BannerBean json = MyApplication.gson.fromJson(response.body().string(), BannerBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            List<BannerBean.RowsBean> rows = json.getRows();
                            List<String> pagers = new ArrayList<>();
                            for (int i = 0; i < rows.size(); i++) {
                                pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                            }
                            banner.setImages(pagers);
                            banner.start();
                        }
                    }
                });
            }
        });

    }

    private void initObject() {
        handler = new Handler();

        GradientDrawable searchBck = new GradientDrawable();
        searchBck.setCornerRadius(100);
        searchBck.setColor(Color.parseColor("#f2f2f2"));
        searchBox.setBackground(searchBck);
        cancelBtn.setVisibility(View.GONE);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cancelBtn.setVisibility(View.VISIBLE);
                } else {
                    cancelBtn.setVisibility(View.GONE);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowMangerUtils.closeKeyboard(getContext(), searchView);
                searchView.setQuery("", false);
                searchView.clearFocus();
            }
        });


        LinearLayoutManager hotManager = new LinearLayoutManager(getContext());
        hotManager.setOrientation(RecyclerView.HORIZONTAL);
        hotContainer.setLayoutManager(hotManager);
        hotContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 20;
                outRect.right = 20;
            }
        });

        LinearLayoutManager previewManager = new LinearLayoutManager(getContext());
        previewManager.setOrientation(RecyclerView.HORIZONTAL);
        previewContainer.setLayoutManager(previewManager);
        previewContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 20;
                outRect.right = 20;
            }
        });

        theatreContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        theatreContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 30;
                outRect.right = 30;
                outRect.top = 10;
                outRect.bottom = 10;
            }
        });


        BannerSetUtils.setBannerStyle(getContext(), banner);

        weatherVf.setAutoStart(true);
        weatherVf.setFlipInterval(6000);
        weatherVf.setInAnimation(getContext(), R.anim.wisdom_ontice_in);
        weatherVf.setOutAnimation(getContext(), R.anim.wisdom_notice_out);

        for (int i = 0; i < appContainer.getChildCount(); i++) {
            final int finalI = i;
            appContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent;
                    switch (finalI) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            intent = new Intent(getContext(), MoviePressActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }

        weatherBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WeatherActivity.class));
            }
        });

    }

    private void initView() {
        placeTv = (TextView) view.findViewById(R.id.place_tv);
        searchBox = (LinearLayout) view.findViewById(R.id.search_box);
        searchView = (SearchView) view.findViewById(R.id.search_view);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        banner = (Banner) view.findViewById(R.id.banner);
        weatherBox = (LinearLayout) view.findViewById(R.id.weather_box);
        weatherIv = (ImageView) view.findViewById(R.id.weather_iv);
        weatherTv = (TextView) view.findViewById(R.id.weather_tv);
        weatherVf = (ViewFlipper) view.findViewById(R.id.weather_vf);
        appContainer = (LinearLayout) view.findViewById(R.id.app_container);
        hotContainer = (RecyclerView) view.findViewById(R.id.hot_container);
        previewContainer = (RecyclerView) view.findViewById(R.id.preview_container);
        theatreContainer = (RecyclerView) view.findViewById(R.id.theatre_container);
    }
}