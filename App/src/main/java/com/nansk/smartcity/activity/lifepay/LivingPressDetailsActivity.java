package com.nansk.smartcity.activity.lifepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.adapter.lifepay.LivingPressCommentListAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.lifepay.LivingPressCommentListBean;
import com.nansk.smartcity.beans.lifepay.LivingPressDetailsBean;
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
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 19:51
 * @Description 生活资讯详情页
 */

public class LivingPressDetailsActivity extends BaseActivity implements View.OnClickListener {
    private int pressId;

    private TextView publishDateTv;
    private WebView webView;
    private TextView likeNumTv;
    private TextView commentTv;
    private RecyclerView commentContainer;
    private EditText commentEt;
    private Button commentBtn;
    private ImageView coverIv;
    private TextView tipsTv;

    private LinearLayout likeBox;
    private TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_living_press_details);

        initView();

        fillData();
        getCommentList();
    }


    private void initView() {

        publishDateTv = (TextView) findViewById(R.id.publishDate_tv);
        webView = (WebView) findViewById(R.id.webView);
        likeNumTv = (TextView) findViewById(R.id.likeNum_tv);
        commentTv = (TextView) findViewById(R.id.comment_tv);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        commentEt = (EditText) findViewById(R.id.comment_et);
        commentBtn = (Button) findViewById(R.id.comment_btn);
        coverIv = (ImageView) findViewById(R.id.cover_iv);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        likeBox = (LinearLayout) findViewById(R.id.like_box);

        titleTv = (TextView) findViewById(R.id.title_tv);
        commentBtn.setOnClickListener(this);
        likeBox.setOnClickListener(this);

        Intent intent = getIntent();
        pressId = intent.getIntExtra("pressId", pressId);

        commentContainer.setLayoutManager(new LinearLayoutManager(LivingPressDetailsActivity.this));
        commentContainer.addItemDecoration(new DividerItemDecoration(LivingPressDetailsActivity.this, DividerItemDecoration.VERTICAL));


    }

    /**
     * @Create 2021/9/29 21:07
     * @Role 获取数据
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIVING_PRESS, "/press/" + pressId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LivingPressDetailsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), LivingPressDetailsBean.class);
                if (jsonObj.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LivingPressDetailsBean.DataBean data = jsonObj.getData();
                            Glide.with(LivingPressDetailsActivity.this).load(MyApplication.IP + data.getCover()).placeholder(R.drawable.default_img).into(coverIv);
                            setToolBarTitle(data.getTitle());
                            titleTv.setText(data.getTitle());
                            publishDateTv.setText("发布时间：" + data.getPublishDate());
                            try {
                                webView.loadData(URLEncoder.encode(data.getContent(), "utf-8"), "text/html", "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            likeNumTv.setText(Integer.toString(data.getLikeNum()));
                        }
                    });
                }
            }
        });

    }

    /**
     * @Create 2021/9/29 21:25
     * @Role 获取评论列表
     * @Param
     */
    private void getCommentList() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIVING_PRESS, "/comments/list?newsId=" + pressId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final LivingPressCommentListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), LivingPressCommentListBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<LivingPressCommentListBean.RowsBean> rows = jsonObj.getRows();
                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                commentTv.setText("评论 " + rows.size());
                                LivingPressCommentListAdapter adapter = new LivingPressCommentListAdapter(LivingPressDetailsActivity.this, rows);
                                commentContainer.setAdapter(adapter);
                            }

                        } else {
                            Toast.makeText(LivingPressDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/9/29 22:16
     * @Role 发表评论
     * @Param
     */
    private void addComment() {
        //判断用户是否登录
        if (MyApplication.isLogin(this)) {
            String content = commentEt.getText().toString().trim();
            if (!content.isEmpty()) {
                String url = ConnectInfo.getUrl(ConnectInfo.LIVING_PRESS, "pressComment?newsId=" + pressId);
                PressCommentsBean commentsBean = new PressCommentsBean();
                commentsBean.setNewsId(pressId);
                commentsBean.setContent(content);
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
                                if (jsonObj.getCode() == 200) {
                                    Toast.makeText(LivingPressDetailsActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
                                    WindowMangerUtils.closeKeyboard(LivingPressDetailsActivity.this, commentEt);
                                    commentEt.clearFocus();
                                    commentEt.setText("");
                                    //刷新评论列表
                                    getCommentList();
                                } else {
                                    Toast.makeText(LivingPressDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            } else {
                Toast.makeText(this, "评论内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent(LivingPressDetailsActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @Create 2021/9/29 22:47
     * @Role 新闻点赞
     * @Param
     */
    private void likePress() {
        if (MyApplication.isLogin(this)) {
            String url = ConnectInfo.getUrl(ConnectInfo.LIVING_PRESS, "press/like/" + pressId);
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
                            if (jsonObj.getCode() == 200) {
                                Toast.makeText(LivingPressDetailsActivity.this, "点赞成功！", Toast.LENGTH_SHORT).show();
                                fillData();
                            } else {
                                Toast.makeText(LivingPressDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        } else {
            Intent intent = new Intent(LivingPressDetailsActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bar:
                finish();
                break;
            case R.id.like_box:
                likePress();
                break;
            case R.id.comment_btn:
                addComment();
                break;
        }
    }
}