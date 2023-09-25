package com.nansk.smartcity.activity.movie;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.movie.MovieTheatreTimeListAdapter;
import com.nansk.smartcity.adapter.movie.MovieTheatreTimeListBean;
import com.nansk.smartcity.beans.DayBean;
import com.nansk.smartcity.beans.movie.MovieFilmListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.view.MyViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 21:43
 * @description 选择影院
 */

public class MovieChangeTheatreActivity extends BaseActivity {

    private TabLayout tabMenu;
    private RecyclerView bodyContainer;
    private TextView tipsTv;
    private MyViewPager headerContainer;

    private int movieId;
    //最近一周日期
    private List<DayBean> lastWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_movie_change_theatre);
        setToolBarTitle("场次选择");

        initView();
        initObject();
        initTimeTab();
        getHeaderFilms();
        getTheatreTimes(movieId);
    }

    /**
     * @Create 2021/10/14 9:46
     * @Role 获取场次列表
     * @Param
     */
    private void getTheatreTimes(int movieId) {
        String playDate = lastWeek.get(tabMenu.getSelectedTabPosition()).getTime();

        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_THEATRE, "times/list?movieId=" + movieId + "&playDate=" + playDate);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieTheatreTimeListBean json = MyApplication.gson.fromJson(response.body().string(), MovieTheatreTimeListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            List<MovieTheatreTimeListBean.RowsBean> rows = json.getRows();
                            MovieTheatreTimeListAdapter adapter = new MovieTheatreTimeListAdapter(MovieChangeTheatreActivity.this, rows);
                            bodyContainer.setAdapter(adapter);
                            //购买按钮回调监听
                            adapter.setPayCallBack(new MovieTheatreTimeListAdapter.PayCallBack() {
                                @Override
                                public void PayCallBack(int position, MovieTheatreTimeListBean.RowsBean obj) {
                                    Intent intent = new Intent(MovieChangeTheatreActivity.this, MovieChangeSeatActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("timeObj",obj);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });

                            if (json.getTotal() > 0) {
                                tipsTv.setText("今天有场次");
                            } else {
                                tipsTv.setText("今天没有场次");
                            }
                        } else {

                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 22:31
     * @Role 获取头部要显示的影片列表
     * @Param
     */
    private void getHeaderFilms() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_FILM, "list");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieFilmListBean json = MyApplication.gson.fromJson(response.body().string(), MovieFilmListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            if (json.getTotal() > 0) {
                                List<MovieFilmListBean.RowsBean> rows = json.getRows();
                                setHeaderView(rows);
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 22:27
     * @Role 设置头部影片
     * @Param
     */
    private void setHeaderView(final List<MovieFilmListBean.RowsBean> rows) {
        List<View> views = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            View customerView = getHeaderCustomerView(rows.get(i));
            views.add(customerView);
        }

        if (views.size() > 0) {
            headerContainer.setAdapter(new HeaderViewAdapter(views));
        }

        headerContainer.post(new Runnable() {
            @Override
            public void run() {
                //移动到购票影片页
                for (int i = 0; i < rows.size(); i++) {
                    if (movieId == rows.get(i).getId()) {
                        headerContainer.setCurrentItem(i, false);
                    }
                }
            }
        });


        //滑动监听事件
        headerContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getTheatreTimes(rows.get(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    /**
     * @Create 2021/10/14 8:23
     * @Role 最近一周时间选择
     * @Param
     */
    private void initTimeTab() {

        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getTheatreTimes(movieId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < lastWeek.size(); i++) {
            tabMenu.addTab(tabMenu.newTab().setText(lastWeek.get(i).getWeek() + " " + lastWeek.get(i).getTime().substring(5, lastWeek.get(i).getTime().length())));
        }
    }


    /**
     * @Create 2021/10/13 22:33
     * @Role 解析影片信息布局
     * @Param
     */
    private View getHeaderCustomerView(MovieFilmListBean.RowsBean data) {
        ImageView imageIv;
        TextView nameTv;
        TextView scoreTv;
        TextView likeNumTv;
        TextView durationTv;
        TextView categoryTv;
        TextView playDate;

        View view = LayoutInflater.from(MovieChangeTheatreActivity.this).inflate(R.layout.item_movie_filme_card, null);

        imageIv = (ImageView) view.findViewById(R.id.image_iv);
        nameTv = (TextView) view.findViewById(R.id.name_tv);
        scoreTv = (TextView) view.findViewById(R.id.score_tv);
        likeNumTv = (TextView) view.findViewById(R.id.likeNum_tv);
        durationTv = (TextView) view.findViewById(R.id.duration_tv);
        categoryTv = (TextView) view.findViewById(R.id.category_tv);
        playDate = (TextView) view.findViewById(R.id.playDate);

        Glide.with(MovieChangeTheatreActivity.this).load(MyApplication.IP + data.getCover()).placeholder(R.drawable.default_img).into(imageIv);
        nameTv.setText(data.getName());
        scoreTv.setText(data.getScore() + ".0");

        if (data.getFavoriteNum() / 10000 > 1) {
            double v = (data.getFavoriteNum() + 0.5) / 10000;
            likeNumTv.setText(String.format("%.2f", v) + "万");
        } else {
            likeNumTv.setText(Integer.toString(data.getFavoriteNum()));
        }

        categoryTv.setText(MovieGetType.getMovieCategory(data.getCategory()));

        durationTv.setText(data.getDuration() + "分钟");
        playDate.setText(data.getPlayDate());

        return view;
    }


    /**
     * @Create 2021/10/14 10:37
     * @Role 由于API数据有限，这里自己设置时间，。必要时则改成自动获取
     * @Param API数据显示时间范围 2021-05-05~2021-05-15
     */
    private List<DayBean> getTestTime() {
        List<DayBean> dayBeans = new ArrayList<>();

        String[] num = new String[]{"今天","明天","后天","周六","周日","周一","周二","周三","周四","周五","周六",};
        String[] dates = new String[]{"2021-05-05","2021-05-06","2021-05-07","2021-05-08","2021-05-09","2021-05-10","2021-05-11","2021-05-12","2021-05-13","2021-05-14","2021-05-15"};

        for (int i=0;i<num.length;i++){
            DayBean dayBean = new DayBean();
            dayBean.setWeek(num[i]);
            dayBean.setTime(dates[i]);
            dayBeans.add(dayBean);
        }
        return dayBeans;
    }

    private void initObject() {
        movieId = getIntent().getIntExtra("movieId", 0);


        bodyContainer.setLayoutManager(new LinearLayoutManager(MovieChangeTheatreActivity.this));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 30;
                outRect.right = 30;
                outRect.top = 10;
                outRect.bottom = 10;
            }
        });


        /**
         * @Create 2021/10/14 10:37
         * @Role 由于API数据有限，这里自己设置时间.
         * @Param API数据显示时间范围 2021-05-05~2021-05-15
         */
//        lastWeek = SystemUtils.getLastWeek();
        lastWeek = getTestTime();

    }


    private void initView() {
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        headerContainer = (MyViewPager) findViewById(R.id.header_container);


    }

    /**
     * @Create 2021/10/14 7:56
     * @Role 头部viewPager适配器
     * @Param
     */
    class HeaderViewAdapter extends PagerAdapter {
        private List<View> views;

        public HeaderViewAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }
    }

}