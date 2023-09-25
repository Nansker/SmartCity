package com.nansk.smartcity.activity.movie;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.movie.MovieFilmDetailsBean;
import com.nansk.smartcity.beans.movie.MovieRoomDetailsBean;
import com.nansk.smartcity.beans.movie.MovieTheatreDetailsBean;
import com.nansk.smartcity.beans.movie.MovieTicketBean;
import com.nansk.smartcity.dialog.PaymentTypeChangeDialog;
import com.nansk.smartcity.dialog.ToastDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SystemUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 16:00
 * @description 支付订单
 */

public class MoviePaymentOrderActivity extends BaseActivity {

    private ImageView imageIv;
    private TextView nameTv;
    private TextView timeTv;
    private TextView typeTv;
    private TextView roomTv;
    private TextView seatTv;
    private TextView theatreNameTv;
    private TextView addressTv;

    private CardView changePayTypeBox;
    private TextView paymentTypeTv;

    private CardView codeBox;
    private ImageView codeIv;

    private LinearLayout bottomBox;
    private TextView priceTv;
    private Button payBtn;

    private MovieTicketBean ticketObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_movie_payment_order);
        setToolBarTitle("支付订单");
        initView();
        initObject();

        getFilmDetails();
        getTheatreDetails();
        getTheatreRoomDetails();
    }

    /**
     * @Create 2021/10/14 17:22
     * @Role 获取影院详情
     * @Param
     */
    private void getTheatreDetails() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_THEATRE, ticketObj.getTheaterId());
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieTheatreDetailsBean json = MyApplication.gson.fromJson(response.body().string(), MovieTheatreDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200){
                            MovieTheatreDetailsBean.DataBean data = json.getData();
                            theatreNameTv.setText(SystemUtils.getValue(data.getName(),"未知"));
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/14 16:53
     * @Role 获取影厅信息
     * @Param
     */
    private void getTheatreRoomDetails() {
        seatTv.setText(ticketObj.getOrderItems().get(0).getSeatRow()+"排"+ticketObj.getOrderItems().get(0).getSeatCol()+"座");
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_THEATRE, "room/" + ticketObj.getRoomId());
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieRoomDetailsBean json = MyApplication.gson.fromJson(response.body().string(), MovieRoomDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200){
                            MovieRoomDetailsBean.DataBean data = json.getData();
                                    roomTv.setText(data.getName());
                        }
                    }
                });
            }
        });

    }

    /**
     * @Create 2021/10/14 16:51
     * @Role 获取订单信息
     * @Param
     */
    private void getFilmDetails() {

        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_FILM, "detail/" + ticketObj.getMovieId());
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieFilmDetailsBean json = MyApplication.gson.fromJson(response.body().string(), MovieFilmDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            MovieFilmDetailsBean.DataBean data = json.getData();
                            Glide.with(MoviePaymentOrderActivity.this).load(MyApplication.IP+data.getCover()).placeholder(R.drawable.default_img).into(imageIv);
                            nameTv.setText(data.getName());

                            typeTv.setText(data.getLanguage()+" "+ MovieGetType.getMovieCategory(data.getCategory()));
                        }
                    }
                });
            }
        });

    }

    /**
     * @Create 2021/10/14 17:35
     * @Role 支付方式选择弹窗
     * @Param
     */
    private void openPayTypeDialog() {
        final PaymentTypeChangeDialog dialog = new PaymentTypeChangeDialog();
        dialog.DialogCallBack(new PaymentTypeChangeDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(String paymentType) {
                paymentTypeTv.setText(paymentType);
                dialog.dismiss();
            }
        });
        dialog.show(getSupportFragmentManager(),"dialog");
    }


    /**
     * @Create 2021/10/14 17:37
     * @Role 支付订单
     * @Param API未提供订单ID，暂时模拟支付
     */
    private void payOrder() {
        final ToastDialog dialog = new ToastDialog(MoviePaymentOrderActivity.this);
        dialog.showReveal("支付中...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.setMsg("支付成功！");
            }
        }, 600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                codeBox.setVisibility(View.VISIBLE);
                changePayTypeBox.setVisibility(View.GONE);
                bottomBox.setVisibility(View.GONE);
                dialog.dismiss();
                setToolBarTitle("影票兑换码");
            }
        }, 1200);
//    ConnectInfo.getUrl(ConnectInfo.MOVIE_TICKET,"/order/pay"+payId);
    }
    /**
     * @Create 2021/10/14 16:43
     * @Role
     * @Param
     */
    private void initObject() {

        codeBox.setVisibility(View.GONE);

        GradientDrawable payBtnDrawable = new GradientDrawable();
        payBtnDrawable.setColor(Color.parseColor("#ff3e5d"));
        payBtnDrawable.setCornerRadius(100);
        payBtn.setBackground(payBtnDrawable);

        int windowSize = WindowMangerUtils.getWindowSize(MoviePaymentOrderActivity.this, 0);
        ViewGroup.LayoutParams layoutParams = codeIv.getLayoutParams();
        layoutParams.width = windowSize/2;
        layoutParams.height = windowSize/2;
        codeIv.setLayoutParams(layoutParams);

        ticketObj = (MovieTicketBean) getIntent().getSerializableExtra("orderObj");
        timeTv.setText(ticketObj.getPlayDate()+" " + ticketObj.getStartTime()+"-"+ticketObj.getEndTime());
        priceTv.setText("￥"+ticketObj.getPrice()+"元");

        changePayTypeBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPayTypeDialog();
            }
        });

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payOrder();
            }
        });
    }



    private void initView() {

        imageIv = (ImageView) findViewById(R.id.image_iv);
        nameTv = (TextView) findViewById(R.id.name_tv);
        timeTv = (TextView) findViewById(R.id.time_tv);
        typeTv = (TextView) findViewById(R.id.type_tv);
        roomTv = (TextView) findViewById(R.id.room_tv);
        seatTv = (TextView) findViewById(R.id.seat_tv);
        theatreNameTv = (TextView) findViewById(R.id.theatreName_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);

        changePayTypeBox = (CardView) findViewById(R.id.changePayType_box);
        paymentTypeTv = (TextView) findViewById(R.id.paymentType_tv);

        codeIv = (ImageView) findViewById(R.id.code_iv);

        bottomBox = (LinearLayout) findViewById(R.id.bottom_box);
        priceTv = (TextView) findViewById(R.id.price_tv);
        payBtn = (Button) findViewById(R.id.pay_btn);
        codeBox = (CardView) findViewById(R.id.code_box);
    }
}