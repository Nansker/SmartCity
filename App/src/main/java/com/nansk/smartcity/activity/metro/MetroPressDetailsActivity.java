package com.nansk.smartcity.activity.metro;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.adapter.metro.MetroPressCommentsAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.metro.MetroPressCommentsBean;
import com.nansk.smartcity.beans.metro.MetroPressDetailsBean;
import com.nansk.smartcity.beans.press.PressCommentsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 17:10
 * @description 新闻详情页
 */

public class MetroPressDetailsActivity extends BaseActivity {

    private TextView titleTv;
    private TextView publishDateTv;
    private ImageView coverIv;
    private WebView webView;
    private TextView readNumTv;
    private LinearLayout likeBox;
    private TextView likeNumTv;
    private TextView commentNumTv;
    private TextView tipsTv;
    private RecyclerView commentContainer;
    private EditText commentEt;
    private Button commentBtn;

    private int pressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro_press_details);
        setToolBarBackground("#ca062c");
        initView();
        initObject();
        fillData();
        getCommentList();
    }


    /**
     * @Create 2021/10/6 18:23
     * @Role 添加评论
     * @Param
     */
    private void comments() {
        if (MyApplication.isLogin(this)){
            String content = commentEt.getText().toString().trim();
            if (!content.isEmpty()){
                PressCommentsBean commentsBean = new PressCommentsBean();
                commentsBean.setNewsId(pressId);
                commentsBean.setContent(content);

                String url = ConnectInfo.getUrl(ConnectInfo.METRO_PRESS, "pressComment");
                OkHttpUtil.doPost(url, MyApplication.getToken(this), commentsBean, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (jsonObj.getCode() == 200){
                                    WindowMangerUtils.closeKeyboard(MetroPressDetailsActivity.this,commentEt);
                                    commentEt.clearFocus();
                                    commentEt.setText("");
                                    Toast.makeText(MetroPressDetailsActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
                                    getCommentList();
                                }else {
                                    Toast.makeText(MetroPressDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

            }else {
                Toast.makeText(this, "评论内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        }else {
            Intent intent = new Intent(MetroPressDetailsActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Create 2021/10/6 17:31
     * @Role 获取评论列表
     * @Param
     */
    private void getCommentList() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_PRESS, "comments/list?newsId=" + pressId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroPressCommentsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroPressCommentsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getTotal() > 0){
                            tipsTv.setVisibility(View.GONE);
                            List<MetroPressCommentsBean.RowsBean> rows = jsonObj.getRows();
                            MetroPressCommentsAdapter adapter = new MetroPressCommentsAdapter(MetroPressDetailsActivity.this, rows);
                            commentContainer.setAdapter(adapter);
                            commentNumTv.setText("评论 "+Integer.toString(rows.size()));
                        }
                    }
                });

            }
        });
    }

    /**
     * @Create 2021/10/6 17:31
     * @Role 获取数据
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_PRESS, "press/"+pressId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroPressDetailsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroPressDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200){
                            MetroPressDetailsBean.DataBean data = jsonObj.getData();
                            titleTv.setText(getValue(data.getTitle()));
                            setToolBarTitle(getValue(data.getTitle()));
                            publishDateTv.setText(getValue(data.getPublishDate()));
                            Glide.with(MetroPressDetailsActivity.this).load(MyApplication.IP+data.getCover()).placeholder(R.drawable.default_img).into(coverIv);

                            try {
                                webView.loadData(URLEncoder.encode(data.getContent(),"utf-8"),"text/html","utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            readNumTv.setText(Integer.toString(data.getReadNum()));
                            likeNumTv.setText(Integer.toString(data.getLikeNum()));


                        }else {
                            Toast.makeText(MetroPressDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/6 18:12
     * @Role 点赞文章
     * @Param
     */
    private void like() {
        //判断用户是否登录
        if (MyApplication.isLogin(this)){
            String url = ConnectInfo.getUrl(ConnectInfo.METRO_PRESS, "press/like/" + pressId);
            OkHttpUtil.doPut(url, MyApplication.getToken(this), "", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200){
                                Toast.makeText(MetroPressDetailsActivity.this, "点赞成功！", Toast.LENGTH_SHORT).show();
                                fillData();
                            }else {
                                Toast.makeText(MetroPressDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }else {
            Intent intent = new Intent(MetroPressDetailsActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void initObject() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setColor(Color.parseColor("#ca062c"));
        commentBtn.setBackground(gradientDrawable);

        Intent intent = getIntent();
        pressId = intent.getIntExtra("pressId", 0);

        commentContainer.setLayoutManager(new LinearLayoutManager(MetroPressDetailsActivity.this));

        likeBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like();
            }
        });

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comments();

            }
        });

    }

    private void initView() {

        titleTv = (TextView) findViewById(R.id.title_tv);
        publishDateTv = (TextView) findViewById(R.id.publishDate_tv);
        coverIv = (ImageView) findViewById(R.id.cover_iv);
        webView = (WebView) findViewById(R.id.webView);
        readNumTv = (TextView) findViewById(R.id.readNum_tv);
        likeBox = (LinearLayout) findViewById(R.id.like_box);
        likeNumTv = (TextView) findViewById(R.id.likeNum_tv);

        commentNumTv = (TextView) findViewById(R.id.commentNum_tv);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);

        commentEt = (EditText) findViewById(R.id.comment_et);
        commentBtn = (Button) findViewById(R.id.comment_btn);
    }

    private String getValue(String value){
        if (value != null && value != ""){
            return value;
        }
        return "";
    }
}