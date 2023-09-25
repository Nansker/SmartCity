package com.nansk.smartcity.activity.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.adapter.ViewPagerFragmentAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.takeout.TakeoutOrderObj;
import com.nansk.smartcity.beans.takeout.TakeoutSellerCollectCheckBean;
import com.nansk.smartcity.beans.takeout.TakeoutSellerDetailsBean;
import com.nansk.smartcity.beans.takeout.TakeoutSellerIdBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/21 23:27
 * @Description 店家详情页
 */

public class TakeoutSellerDetailsActivity extends BaseActivity {

    private ImageView imageIv;
    private TextView nameTv;
    private TextView introductionTv;
    private TextView scoreTv;
    private TextView saleQuantityTv;
    private TextView avgCostTv;
    private TextView deliveryTimeTv;
    private TextView distanceTv;
    private TextView collectTv;

    private TabLayout tabMenu;
    private ViewPager bodyContainer;

    private String sellerId;

    private List<Fragment> fragments;
    private TextView priceTv;
    private TextView numberTv;
    private Button settlementBtn;

    private TakeoutOrderObj orderObj;

    private Gson gson;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_takeout_seller_details);
        setToolBarBackground("#FFC107", 0);
        setToolBarTitle("店家详情");
        initView();
        initSellerDetails();
        initCollect("get");
    }

    private void initView() {
        imageIv = (ImageView) findViewById(R.id.image_iv);
        nameTv = (TextView) findViewById(R.id.name_tv);
        introductionTv = (TextView) findViewById(R.id.introduction_tv);
        scoreTv = (TextView) findViewById(R.id.score_tv);
        saleQuantityTv = (TextView) findViewById(R.id.saleQuantity_tv);
        avgCostTv = (TextView) findViewById(R.id.avgCost_tv);
        deliveryTimeTv = (TextView) findViewById(R.id.deliveryTime_tv);
        distanceTv = (TextView) findViewById(R.id.distance_tv);
        collectTv = (TextView) findViewById(R.id.collect_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);
        numberTv = (TextView) findViewById(R.id.number_tv);
        settlementBtn = (Button) findViewById(R.id.settlement_btn);

        tabMenu = findViewById(R.id.tab_menu);
        bodyContainer = (ViewPager) findViewById(R.id.body_container);

        Intent intent = getIntent();
        sellerId = intent.getStringExtra("sellerId");

        orderObj = new TakeoutOrderObj();
        token = MyApplication.getToken(this);
        gson = MyApplication.gson;


        collectTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCollect("action");
            }
        });
    }


    private void initSellerDetails() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_DETAILS, sellerId);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TakeoutSellerDetailsBean jsonObj = gson.fromJson(response.body().string(), TakeoutSellerDetailsBean.class);
                if (jsonObj.getCode() == 200) {
                    final TakeoutSellerDetailsBean.DataBean sellerObj = jsonObj.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sellerDetails(sellerObj);
                            initTabMenu(sellerObj);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(TakeoutSellerDetailsActivity.this, jsonObj.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /**
     * @Create 2021/9/26 11:14
     * @Role 店家收藏信息
     * @Param
     */
    private void initCollect(final String requestType) {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_COLLECT, "/check?sellerId=" + sellerId);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TakeoutSellerCollectCheckBean jsonObj = gson.fromJson(response.body().string(), TakeoutSellerCollectCheckBean.class);
                if (jsonObj.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final String msg = jsonObj.getMsg();
                            //获取信息
                            if (requestType.equals("get")) {
                                if (msg.equals("已收藏")) {
                                    collectTv.setText(jsonObj.getMsg());
                                } else if (msg.equals("未收藏")) {
                                    collectTv.setText("加入收藏");
                                }
                                //点击操作
                            } else if (requestType.equals("action")) {
                                if (msg.equals("已收藏")) {
                                    cancelCollect();
                                } else if (msg.equals("未收藏")) {
                                    addCollect();
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * @Create 2021/9/26 11:51
     * @Role 添加收藏
     * @Param
     */
    private void addCollect() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_COLLECT, "");
        TakeoutSellerIdBean sellerIdBean = new TakeoutSellerIdBean();
        sellerIdBean.setSellerId(Integer.valueOf(sellerId));

        OkHttpUtil.doPost(url, token, sellerIdBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean resultBean = gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resultBean.getCode() == 200) {
                            initCollect("get");
                        } else {
                            Toast.makeText(TakeoutSellerDetailsActivity.this, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/9/26 11:51
     * @Role 取消收藏
     * @Param
     */
    private void cancelCollect() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_COLLECT, "/" + sellerId);
        OkHttpUtil.delete(url, token, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean resultBean = gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resultBean.getCode() == 200) {
                            initCollect("get");
                        } else {
                            Toast.makeText(TakeoutSellerDetailsActivity.this, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    //设置店家基本信息
    private void sellerDetails(final TakeoutSellerDetailsBean.DataBean sellerObj) {
        Glide.with(TakeoutSellerDetailsActivity.this).load(MyApplication.IP + sellerObj.getImgUrl()).into(imageIv);
        nameTv.setText(sellerObj.getName());
        introductionTv.setText("简介：" + sellerObj.getIntroduction());
        scoreTv.setText(sellerObj.getScore() + "分");
        saleQuantityTv.setText("月销量 " + sellerObj.getSaleQuantity());
        avgCostTv.setText("人均 " + sellerObj.getAvgCost());
        deliveryTimeTv.setText("配送时间 " + sellerObj.getDeliveryTime());
        distanceTv.setText("配送距离 " + sellerObj.getDistance());

        settlementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.isLogin(TakeoutSellerDetailsActivity.this)) {
                    settlement(sellerObj);
                } else {
                    Intent intent = new Intent(TakeoutSellerDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    /**
     * @Create 2021/9/22 10:30
     * @Role 设置tab
     * @Param
     */
    private void initTabMenu(TakeoutSellerDetailsBean.DataBean sellerObj) {
        String[] tabNames = new String[]{"点菜", "评价", "商家"};

        fragments = new ArrayList<>();
        fragments.add(new TakeoutSellerOrderActivity(sellerObj, orderObj, priceTv, numberTv));
        fragments.add(new TakeoutSellerCommentActivity(sellerObj));

        //适配器
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments, tabNames);
        bodyContainer.setAdapter(adapter);

        //监听事件
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bodyContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabMenu.setupWithViewPager(bodyContainer);

    }


    /**
     * @Create 2021/9/23 10:48
     * @Role 结算
     * @Param
     */
    private void settlement(TakeoutSellerDetailsBean.DataBean sellerObj) {
        if (orderObj.getOrderItemList() != null && orderObj.getOrderItemList().size() > 0) {
            orderObj.setSellerId(sellerObj.getId());
            orderObj.setSellerName(sellerObj.getName());
            Intent intent = new Intent(this, TakeoutOrderConfirmActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("orderObj", orderObj);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Toast.makeText(this, "请添加菜品！", Toast.LENGTH_SHORT).show();
        }
    }

}