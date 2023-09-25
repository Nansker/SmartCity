package com.nansk.smartcity.activity.movie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.adapter.movie.MovieCommentListBean;
import com.nansk.smartcity.adapter.movie.MoviePressCommentListAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.movie.MoviePressDetailsBean;
import com.nansk.smartcity.beans.press.PressCommentsBean;
import com.nansk.smartcity.dialog.CommentDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

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
 * @create 2021/10/14 19:25
 * @description
 */

public class MoviePressDetailsActivity extends BaseActivity {
    private int pressId;
    private TextView titleTv;
    private TextView publishDateTv;
    private WebView webView;
    private LinearLayout likeBox;
    private TextView likeNumTv;
    private TextView commentNumTv;
    private RecyclerView commentContainer;
    private Button commentBtn;
    private ImageView imageIv;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_movie_press_details);
        setToolBarTitle("星闻详情");
        initView();
        initObject();
        getPressDetails();
        getCommentList();

    }

    private void openCommentDialog() {
        if (MyApplication.isLogin(MoviePressDetailsActivity.this)) {

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor("#ff3e5d"));
            gradientDrawable.setCornerRadius(100);
            final CommentDialog dialog = new CommentDialog("星闻评论", gradientDrawable, true);
            dialog.DialogCallBack(new CommentDialog.DialogCallBack() {
                @Override
                public void DialogCallBack(TextView dialogTitle, EditText bodyEt, RatingBar scoreBar, Button submitBtn) {
                    commentPress(bodyEt, scoreBar);
                    dialog.dismiss();
                }
            });
            dialog.show(getSupportFragmentManager(), "dialog");
        } else {
            Toast.makeText(this, "用户未登录，即将跳转登录...", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MoviePressDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }, 1000);
        }
    }

    private void commentPress(EditText bodyEt, RatingBar scoreBar) {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_PRESS, "pressComment");
        PressCommentsBean commentsBean = new PressCommentsBean();
        commentsBean.setContent(bodyEt.getText().toString().trim());
        commentsBean.setNewsId(pressId);
        OkHttpUtil.doPost(url, MyApplication.getToken(MoviePressDetailsActivity.this), commentsBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            Toast.makeText(MoviePressDetailsActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                            getCommentList();
                        }
                    }
                });
            }
        });
    }


    private void getCommentList() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_PRESS, "comments/list?newsId=" + pressId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieCommentListBean json = MyApplication.gson.fromJson(response.body().string(), MovieCommentListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            List<MovieCommentListBean.RowsBean> rows = json.getRows();
                            if (rows.size() > 0) {
                                commentNumTv.setText("评论（" + rows.size() + "）");
                                tipsTv.setVisibility(View.GONE);
                                MoviePressCommentListAdapter adapter = new MoviePressCommentListAdapter(MoviePressDetailsActivity.this, rows);
                                commentContainer.setAdapter(adapter);
                            } else {
                                commentNumTv.setText("评论（0）");
                                tipsTv.setVisibility(View.VISIBLE);
                                tipsTv.setText("暂无评论");
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/14 19:54
     * @Role
     * @Param
     */
    private void getPressDetails() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_PRESS, "press/" + pressId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MoviePressDetailsBean json = MyApplication.gson.fromJson(response.body().string(), MoviePressDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            MoviePressDetailsBean.DataBean data = json.getData();
                            titleTv.setText(data.getTitle());
                            publishDateTv.setText(data.getPublishDate());
                            Glide.with(MoviePressDetailsActivity.this).load(MyApplication.IP + data.getCover()).placeholder(R.drawable.default_img).into(imageIv);
                            try {
                                webView.loadData(URLEncoder.encode(data.getContent(), "utf-8"), "text/html", "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            likeNumTv.setText(Integer.toString(data.getLikeNum()));

                        }
                    }
                });
            }
        });
    }

    //点赞新闻
    private void likePress() {
        if (MyApplication.isLogin(MoviePressDetailsActivity.this)) {
            String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_PRESS, "press/like/" + pressId);
            OkHttpUtil.doPut(url, MyApplication.getToken(MoviePressDetailsActivity.this), "", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (json.getCode() == 200) {
                                Toast.makeText(MoviePressDetailsActivity.this, "点赞+1", Toast.LENGTH_SHORT).show();
                                getPressDetails();
                            }
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "用户未登录，即将跳转登录...", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MoviePressDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }, 1000);
        }
    }

    /**
     * @Create 2021/10/14 19:43
     * @Role
     * @Param
     */
    private void initObject() {

        pressId = getIntent().getIntExtra("pressId", 0);

        commentContainer.setLayoutManager(new LinearLayoutManager(MoviePressDetailsActivity.this));
        commentContainer.addItemDecoration(new DividerItemDecoration(MoviePressDetailsActivity.this, DividerItemDecoration.VERTICAL));

        likeBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likePress();
            }
        });

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommentDialog();
            }
        });

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#ff3e5d"));
        gradientDrawable.setCornerRadius(100);
        commentBtn.setBackground(gradientDrawable);
    }


    private void initView() {
        titleTv = (TextView) findViewById(R.id.title_tv);

        publishDateTv = (TextView) findViewById(R.id.publishDate_tv);
        webView = (WebView) findViewById(R.id.webView);
        likeBox = (LinearLayout) findViewById(R.id.like_box);
        likeNumTv = (TextView) findViewById(R.id.likeNum_tv);

        commentNumTv = (TextView) findViewById(R.id.commentNum_tv);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        commentBtn = (Button) findViewById(R.id.comment_btn);
        imageIv = (ImageView) findViewById(R.id.image_iv);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
    }
}