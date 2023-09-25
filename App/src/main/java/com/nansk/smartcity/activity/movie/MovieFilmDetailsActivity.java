package com.nansk.smartcity.activity.movie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.CheckLogin;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.movie.MovieCommentListAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.movie.MovieFilmCommentBean;
import com.nansk.smartcity.beans.movie.MovieFilmCommentListBean;
import com.nansk.smartcity.beans.movie.MovieFilmDetailsBean;
import com.nansk.smartcity.dialog.CommentDialog;
import com.nansk.smartcity.model.impl.CheckLoginUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SystemUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 14:58
 * @description 影片详情页
 */

public class MovieFilmDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageIv;
    private TextView nameTv;
    private TextView durationTv;
    private TextView categoryTv;
    private TextView playDate;

    private TextView scoreTv;
    private RatingBar scoreBar;
    private TextView favoriteNumTv;
    private TextView likeNumTv;
    private TextView commentNumTv;

    private TextView introductionTv;

    private Button commentBtn;
    private RecyclerView commentContainer;

    private Button payBtn;

    private int filmId;
    private LinearLayout readBox;
    private LinearLayout showAllBox;

    private int flag = 0;
    private TextView commentsTipsTv;
    private TextView commentTitleNumTv;

    private GradientDrawable gradientDrawable = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_movie_film_details);
        setToolBarTitle("影片详情");
        setToolBarBackground("#ff3e5d");
        initView();
        initObject();
        fillData();
        getCommentList();
    }

    /**
     * @Create 2021/10/13 19:59
     * @Role 打开评论弹窗
     * @Param
     */
    private void openCommentDialog() {
        CheckLoginUtils.isLogin(MovieFilmDetailsActivity.this, true, new CheckLogin() {
            @Override
            public void onAlready() {
                final CommentDialog dialog = new CommentDialog("影片评论", gradientDrawable,true);
                dialog.DialogCallBack(new CommentDialog.DialogCallBack() {
                    @Override
                    public void DialogCallBack(TextView dialogTitle, EditText bodyEt, RatingBar scoreBar ,Button submitBtn) {
                        String content = bodyEt.getText().toString().trim();
                        if (!content.isEmpty()) {
                            int rating = (int) scoreBar.getRating();
                            if (rating > 0){
                                commentFilm(dialog,bodyEt,scoreBar);
                            }else {
                                showToast("请选择影片评分！");
                            }
                        } else {
                            showToast("评论的内容不能为空！");
                        }
                    }
                });
                dialog.show(getSupportFragmentManager(), "dialog");
            }

            @Override
            public void onNone() {

            }
        });
    }

    /**
     * @Create 2021/10/13 20:20
     * @Role 发表评论
     * @param dialog
     * @param bodyEt
     * @param scoreBar
     */
    private void commentFilm(final CommentDialog dialog, EditText bodyEt, RatingBar scoreBar) {
        MovieFilmCommentBean commentBean = new MovieFilmCommentBean();
        commentBean.setContent(bodyEt.getText().toString().trim());
        commentBean.setScore((int) scoreBar.getRating());
        commentBean.setMovieId(filmId);

        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_COMMENT, "");
        OkHttpUtil.doPost(url, MyApplication.getToken(MovieFilmDetailsActivity.this), commentBean, new Callback() {
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
                            dialog.dismiss();
                            getCommentList();
                            showToast("评论成功！");
                        }else {
                            showToast(json.getMsg());
                        }
                    }
                });
            }
        });

    }

    /**
     * @Create 2021/10/13 18:35
     * @Role 评论列表
     * @Param
     */
    private void getCommentList() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_COMMENT, "/list?movieId=" + filmId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieFilmCommentListBean json = MyApplication.gson.fromJson(response.body().string(), MovieFilmCommentListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            if (json.getTotal() > 0) {
                                commentTitleNumTv.setText("评论（" + json.getTotal() + "）");
                                commentsTipsTv.setVisibility(View.GONE);
                                List<MovieFilmCommentListBean.RowsBean> rows = json.getRows();
                                MovieCommentListAdapter adapter = new MovieCommentListAdapter(MovieFilmDetailsActivity.this, rows);
                                commentContainer.setAdapter(adapter);

                                //点赞评论回调接口
                                adapter.setLikeCommentCallBack(new MovieCommentListAdapter.LikeCommentCallBack() {
                                    @Override
                                    public void LikeCommentCallBack(int position, int id) {
                                        likeComment(id);
                                    }
                                });
                            } else {
                                commentTitleNumTv.setText("评论（0）");
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 20:54
     * @Role 点赞评论
     * @Param
     */
    public void likeComment(int id){
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_COMMENT, "/like/" + id);
        OkHttpUtil.doPost(url, MyApplication.getToken(MovieFilmDetailsActivity.this),"", new Callback() {
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
                            getCommentList();
                            showToast("点赞+1");
                        }
                    }
                });

            }
        });
    }

    /**
     * @Create 2021/10/13 16:16
     * @Role 设置详情
     * @Param
     */
    private void setFilmInfo(MovieFilmDetailsBean.DataBean data) {
        Glide.with(MovieFilmDetailsActivity.this).load(MyApplication.IP + data.getCover()).placeholder(R.drawable.default_img).into(imageIv);
        nameTv.setText(SystemUtils.getValue(data.getName(), "暂无数据"));
        durationTv.setText(SystemUtils.getValue(Integer.toString(data.getDuration()) + "分钟", "暂无数据"));
        categoryTv.setText(MovieGetType.getMovieCategory(data.getCategory()));
        playDate.setText(SystemUtils.getValue(data.getPlayDate(), "暂无数据"));
        scoreTv.setText(SystemUtils.getValue(Integer.toString(data.getScore()) + ".0", "暂无数据"));
        scoreBar.setRating(data.getScore());

        if (data.getFavoriteNum() / 10000 > 1) {
            double v = (data.getFavoriteNum() + 0.5) / 10000;
            favoriteNumTv.setText(String.format("%.2f", v) + "万");
        } else {
            favoriteNumTv.setText(Integer.toString(data.getFavoriteNum()));
        }
        if (data.getLikeNum() / 10000 > 1) {
            double v = (data.getLikeNum() + 0.5) / 10000;
            likeNumTv.setText(String.format("%.2f", v) + "万");
        } else {
            favoriteNumTv.setText(Integer.toString(data.getFavoriteNum()));
        }

        setIntroduction(data.getIntroduction());
    }

    /**
     * @Create 2021/10/13 16:13
     * @Role 获取影片详情
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_FILM, "detail/" + filmId);
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
                            setFilmInfo(data);
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/13 16:57
     * @Role 过滤简介中的HTML标签, 文本超过三行显示展开收起按钮
     * @Param
     */
    private void setIntroduction(String value) {
        final String s = Html.fromHtml(value).toString();
        introductionTv.setText(s);
        introductionTv.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = introductionTv.getLineCount();
                if (lineCount > 3) {
                    showAllBox.setVisibility(View.VISIBLE);
                    introductionTv.setMaxLines(3);
                    showAllBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView text = showAllBox.getChildAt(1).findViewById(R.id.flag_tv);
                            ImageView imageView = showAllBox.getChildAt(0).findViewById(R.id.flag_iv);

                            if (flag == 0) {
                                introductionTv.setMaxLines(999);
                                introductionTv.setText(s);
                                flag = 1;
                                text.setText("收起");
                                imageView.setRotationX(180);
                            } else if (flag == 1) {
                                introductionTv.setMaxLines(3);
                                introductionTv.setText(s);
                                flag = 0;
                                text.setText("查看全部");
                                imageView.setRotationX(360);
                            }

                        }
                    });
                }
            }
        });


    }

    /**
     * @Create 2021/10/13 15:58
     * @Role
     * @Param
     */
    private void initObject() {

        for (int i = 0; i < readBox.getChildCount(); i++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor("#f2f2f2"));
            gradientDrawable.setCornerRadius(5);
            readBox.getChildAt(i).setBackground(gradientDrawable);
        }

        GradientDrawable commentBtnDrawable = new GradientDrawable();
        commentBtnDrawable.setColor(Color.parseColor("#ff3e5d"));
        commentBtnDrawable.setCornerRadius(100);
        commentBtn.setBackground(commentBtnDrawable);

        GradientDrawable payBtnDrawable = new GradientDrawable();
        payBtnDrawable.setColor(Color.parseColor("#ff3e5d"));
        payBtnDrawable.setCornerRadius(100);
        payBtn.setBackground(payBtnDrawable);

        filmId = getIntent().getIntExtra("filmId", 0);

        commentContainer.setLayoutManager(new LinearLayoutManager(MovieFilmDetailsActivity.this));

        commentBtn.setOnClickListener(this);
        payBtn.setOnClickListener(this);

        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#ff3e5d"));
        gradientDrawable.setCornerRadius(100);


    }

    private void initView() {

        imageIv = (ImageView) findViewById(R.id.image_iv);
        nameTv = (TextView) findViewById(R.id.name_tv);
        durationTv = (TextView) findViewById(R.id.duration_tv);
        categoryTv = (TextView) findViewById(R.id.category_tv);
        playDate = (TextView) findViewById(R.id.playDate);

        scoreTv = (TextView) findViewById(R.id.score_tv);
        scoreBar = (RatingBar) findViewById(R.id.score_bar);
        favoriteNumTv = (TextView) findViewById(R.id.favoriteNum_tv);
        likeNumTv = (TextView) findViewById(R.id.likeNum_tv);
        commentNumTv = (TextView) findViewById(R.id.commentNum_tv);

        introductionTv = (TextView) findViewById(R.id.introduction_tv);

        commentBtn = (Button) findViewById(R.id.comment_btn);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);

        payBtn = (Button) findViewById(R.id.pay_btn);
        readBox = (LinearLayout) findViewById(R.id.read_box);
        showAllBox = (LinearLayout) findViewById(R.id.showAll_box);
        commentsTipsTv = (TextView) findViewById(R.id.comments_tips_tv);

        commentTitleNumTv = (TextView) findViewById(R.id.commentTitleNum_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bar:
                finish();
                break;
            case R.id.comment_btn:
                openCommentDialog();
                break;
            case R.id.pay_btn:
                CheckLoginUtils.isLogin(MovieFilmDetailsActivity.this, true, new CheckLogin() {
                    @Override
                    public void onAlready() {
                        Intent intent = new Intent(MovieFilmDetailsActivity.this, MovieChangeTheatreActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("movieId",filmId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onNone() {

                    }
                });
                break;
        }
    }

}