package com.nansk.smartcity.activity.traffic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.traffic.TrafficIllegalDetailsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 16:50
 * @description 违章详情
 */


public class TrafficIllegalDetailsActivity extends BaseActivity {
    //违章id
    private int id;

    private Gson gson;
    private String token;

    private TextView trafficOffenceTv;
    private TextView moneyTv;
    private TextView deductMarksTv;
    private TextView licencePlateTv;
    private TextView illegalSitesTv;
    private TextView badTimeTv;
    private TextView noticeNoTv;
    private ImageView imageIv;
    private TextView disposeStateTv;
    private Button disposeBtn;
    private RelativeLayout cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_details);
        setToolBarBackground("#2c65a8");
        setToolBarTitle("违章详情");

        initView();
        initObject();
        fillData();
    }


    private void initView() {

        trafficOffenceTv = (TextView) findViewById(R.id.trafficOffence_tv);
        moneyTv = (TextView) findViewById(R.id.money_tv);
        deductMarksTv = (TextView) findViewById(R.id.deductMarks_tv);
        licencePlateTv = (TextView) findViewById(R.id.licencePlate_tv);
        illegalSitesTv = (TextView) findViewById(R.id.illegalSites_tv);
        badTimeTv = (TextView) findViewById(R.id.badTime_tv);
        noticeNoTv = (TextView) findViewById(R.id.noticeNo_tv);
        imageIv = (ImageView) findViewById(R.id.image_iv);
        disposeStateTv = (TextView) findViewById(R.id.disposeState_tv);
        disposeBtn = (Button) findViewById(R.id.dispose_btn);
        cancelBtn = (RelativeLayout) findViewById(R.id.cancel_btn);
    }

    private void initObject() {


        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setColor(Color.parseColor("#2c65a8"));
        disposeBtn.setBackground(gradientDrawable);

        token = MyApplication.getToken(this);
        gson = MyApplication.gson;

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }


    /**
     * @Create 2021/10/4 17:24
     * @Role 获取数据源
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, id);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficIllegalDetailsBean detailsBean = gson.fromJson(response.body().string(), TrafficIllegalDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (detailsBean.getCode() == 200) {
                            TrafficIllegalDetailsBean.DataBean data = detailsBean.getData();
                            Glide.with(TrafficIllegalDetailsActivity.this).load(MyApplication.IP + data.getImgUrl()).placeholder(R.drawable.default_img).into(imageIv);
                            trafficOffenceTv.setText(data.getTrafficOffence());
                            moneyTv.setText("罚款" + data.getMoney() + "元");
                            deductMarksTv.setText("记" + data.getDeductMarks() + "分");
                            licencePlateTv.setText(getValue(data.getLicencePlate()));
                            illegalSitesTv.setText(getValue(data.getIllegalSites()));
                            noticeNoTv.setText(getValue(data.getNoticeNo()));
                            badTimeTv.setText(getValue(data.getBadTime()));
                            disposeStateTv.setText(data.getDisposeState());

                            setDisposeButton(data.getDisposeState(),data);
                        } else {
                            Toast.makeText(TrafficIllegalDetailsActivity.this, detailsBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/7 16:02
     * @Role 设置底部处理按钮
     * @Param
     */
    private void setDisposeButton(String disposeState, final TrafficIllegalDetailsBean.DataBean obj) {
        switch (disposeState) {
            case "未处理":
                disposeBtn.setText("去处理");
                disposeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        acceptIllegal();
                    }
                });

                //显示撤销申请按钮
                cancelBtn.setVisibility(View.VISIBLE);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TrafficIllegalDetailsActivity.this, TrafficIllegalCancel2Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("illegalId", obj.getId());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                break;
            case "已处理未缴款":
                disposeBtn.setText("缴款");
                disposeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payFine();
                    }
                });
                cancelBtn.setVisibility(View.GONE);
                break;
            case "已缴款":
                disposeBtn.setText("已缴款");
                cancelBtn.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * @Create 2021/10/7 16:18
     * @Role 缴款
     * @Param
     */
    private void payFine() {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "pay/" + id);
        OkHttpUtil.doPost(url, token, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            fillData();
                            Toast.makeText(TrafficIllegalDetailsActivity.this, "缴款成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TrafficIllegalDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }


    /**
     * @Create 2021/10/7 16:16
     * @Role 接受处理
     * @Param
     */
    private void acceptIllegal() {
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "accept/" + id);
        OkHttpUtil.doPost(url, token, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            fillData();
                            Toast.makeText(TrafficIllegalDetailsActivity.this, "已接受处理", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TrafficIllegalDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private String getValue(String value) {
        if (value != null && value != "") {
            return value;
        }
        return "暂无数据";
    }

}