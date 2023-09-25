package com.nansk.smartcity.activity.movie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.movie.MovieTheatreTimeListBean;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.movie.MovieSeatListBean;
import com.nansk.smartcity.beans.movie.MovieTicketBean;
import com.nansk.smartcity.dialog.ToastDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 11:43
 * @description 选坐
 */

public class MovieChangeSeatActivity extends BaseActivity {
    //场次对象
    private MovieTheatreTimeListBean.RowsBean timeObj;

    private GridLayout seatContainer;
    private Button confirmBtn;
    private View view1;
    private View view2;
    private TextView selectedSeatTv;
    private TextView movieNameTv;
    private TextView playDateTv;

    //是否选择了座位
    private boolean isSelected = false;
    private MovieTicketBean ticketBean;
    //座位对象集合
    private List<MovieTicketBean.OrderItemsBean> orderItemsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_movie_change_seat);
        setToolBarTitle("座位选择");
        initView();
        initObject();
        getSeatList();
    }

    /**
     * @Create 2021/10/14 15:24
     * @Role 确认选座,提交订单
     * @Param
     */
    private void submitOrder() {
        final ToastDialog dialog = new ToastDialog(MovieChangeSeatActivity.this);

        if (isSelected) {
            //创建订单对象
            if (orderItemsBean.size()>0){
                dialog.showReveal("生成订单中...");
                ticketBean.setOrderItems(orderItemsBean);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_TICKET, "");
                OkHttpUtil.doPost(url, MyApplication.getToken(MovieChangeSeatActivity.this), ticketBean, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (json.getCode() == 200){
                                    dialog.setMsg("即将跳转支付...");
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(MovieChangeSeatActivity.this, MoviePaymentOrderActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("orderObj",ticketBean);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    }, 800);
                                }else {
                                    showToast("订单提交失败，请稍后再试！",1200);
                                }
                            }
                        });
                    }
                });
//
                    }
                }, 600);
            }else {
                Toast.makeText(this, "需要重新选择座位", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请先选择座位！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @Create 2021/10/14 12:04
     * @Role 获取座位列表
     * @Param
     */
    private void getSeatList() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_THEATRE, "list4times?roomId=" + timeObj.getRoomId() + "&id=" + timeObj.getId());
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieSeatListBean json = MyApplication.gson.fromJson(response.body().string(), MovieSeatListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            setSeatView(json.getData());
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/14 12:09
     * @Role 设置座位布局视图, 点击改变颜色
     * @Param
     */
    private void setSeatView(final List<MovieSeatListBean.DataBean> data) {

        //座位对象
        final List<MovieTicketBean.OrderItemsBean> seatObjList = new ArrayList<>();

        int windowSize = WindowMangerUtils.getWindowSize(MovieChangeSeatActivity.this, 0);
        //遍历座位
        for (int i = 0; i < data.size(); i++) {
            final View seatView = new View(MovieChangeSeatActivity.this);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.bottomMargin = 10;
            layoutParams.topMargin = 10;

            //设置中间过道
            if (i % 16 == 4) {
                layoutParams.leftMargin = 10;
                layoutParams.rightMargin = 40;
            } else if (i % 16 == 5) {
                layoutParams.leftMargin = 40;
                layoutParams.rightMargin = 10;
            } else if (i % 16 == 10) {
                layoutParams.leftMargin = 10;
                layoutParams.rightMargin = 40;
            } else if (i % 16 == 11) {
                layoutParams.leftMargin = 40;
                layoutParams.rightMargin = 10;
            } else {
                layoutParams.leftMargin = 10;
                layoutParams.rightMargin = 10;
            }


            layoutParams.height = windowSize / 11;
            layoutParams.width = windowSize / 11;
            seatView.setLayoutParams(layoutParams);

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor("#ffffff"));
            gradientDrawable.setCornerRadius(15);
            gradientDrawable.setStroke(2, Color.parseColor("#999999"));
            seatView.setBackground(gradientDrawable);
            seatContainer.addView(seatView);

            //创建每个座位的对象并添加到集合中
            MovieTicketBean.OrderItemsBean itemsBean = new MovieTicketBean.OrderItemsBean();
            itemsBean.setSeatCol(data.get(i).getCol());
            itemsBean.setSeatRow(data.get(i).getRow());
            itemsBean.setSeatId(data.get(i).getId());
            itemsBean.setPrice(timeObj.getPrice());
            seatObjList.add(itemsBean);
        }

        for (int i = 0; i < seatContainer.getChildCount(); i++) {
            final int finalI = i;
            seatContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置样式
                    for (int j = 0; j < seatContainer.getChildCount(); j++) {
                        GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setCornerRadius(15);
                        if (finalI == j) {
                            gradientDrawable.setStroke(2, Color.parseColor("#ffffff"));
                            gradientDrawable.setColor(Color.parseColor("#ff3e5d"));
                        } else {
                            gradientDrawable.setStroke(2, Color.parseColor("#999999"));
                            gradientDrawable.setColor(Color.parseColor("#ffffff"));
                        }
                        seatContainer.getChildAt(j).setBackground(gradientDrawable);
                    }

                    //设置订单对象属性
                    selectedSeatTv.setText("已选择：" + timeObj.getRoomName() + data.get(finalI).getRow() + "排" + data.get(finalI).getCol() + "座");
                    isSelected = true;

                    /**
                     * @Create 2021/10/14 15:45
                     * @Role 座位暂时只支持单选，每次选择座位后都会移除之前已经添加了的对象
                     * @Param
                     */
                    if (orderItemsBean.size() > 0) {
                        for (int k = 0; k < orderItemsBean.size(); k++) {
                            orderItemsBean.remove(k);
                        }
                    }
                    orderItemsBean.add(seatObjList.get(finalI));
                }
            });
        }

    }

    /**
     * @Create 2021/10/14 11:59
     * @Role
     * @Param
     */
    private void initObject() {


        timeObj = (MovieTheatreTimeListBean.RowsBean) getIntent().getSerializableExtra("timeObj");

        //图例样式

        int windowSize = WindowMangerUtils.getWindowSize(MovieChangeSeatActivity.this, 0);
        for (int i = 0; i < 3; i++) {
            GradientDrawable style = new GradientDrawable();

            style.setCornerRadius(15);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = windowSize / 16;
            layoutParams.width = windowSize / 16;
            if (i == 0) {
                style.setColor(Color.parseColor("#ff3e5d"));
                style.setStroke(2, Color.parseColor("#ffffff"));
                view1.setBackground(style);
                view1.setLayoutParams(layoutParams);
            } else if (i == 1) {
                style.setStroke(2, Color.parseColor("#999999"));
                style.setColor(Color.parseColor("#ffffff"));
                view2.setBackground(style);
                view2.setLayoutParams(layoutParams);
            }

        }

        GradientDrawable confirmBtnDrawable = new GradientDrawable();
        confirmBtnDrawable.setColor(Color.parseColor("#ff3e5d"));
        confirmBtnDrawable.setCornerRadius(100);
        confirmBtn.setBackground(confirmBtnDrawable);

        movieNameTv.setText(timeObj.getMovieName());
        playDateTv.setText(timeObj.getPlayDate() + " " + timeObj.getStartTime() + "-" + timeObj.getEndTime());

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();
            }
        });

        //订单对象
        ticketBean = new MovieTicketBean();
        orderItemsBean = new ArrayList<MovieTicketBean.OrderItemsBean>();
        ticketBean.setEndTime(timeObj.getEndTime());
        ticketBean.setMovieId(timeObj.getMovieId());
        ticketBean.setTheaterId(timeObj.getTheaterId());
        ticketBean.setPlayDate(timeObj.getPlayDate());
        ticketBean.setPrice(timeObj.getPrice());
        ticketBean.setRoomId(timeObj.getRoomId());
        ticketBean.setTheaterId(ticketBean.getTheaterId());
        ticketBean.setStartTime(timeObj.getStartTime());
        ticketBean.setTimesId(timeObj.getId());

    }

    private void initView() {
        seatContainer = (GridLayout) findViewById(R.id.seat_container);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);

        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        selectedSeatTv = (TextView) findViewById(R.id.selected_seat_tv);
        movieNameTv = (TextView) findViewById(R.id.movieName_tv);
        playDateTv = (TextView) findViewById(R.id.playDate_tv);
    }
}